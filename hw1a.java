/*
Author: Lin Qi
*/
import java.util.*;

public class hw1a {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);  //input is n
        int n = scan.nextInt();
        n = 1000000000;
        boolean isprime[] = new boolean[n+1];  //  generate an array
        for(int i = 0; i < n; i++)
            isprime[i] = true;
        for(int m = 3; m*m <=n; m+=2)   //the even number is not considered, and the first prime number is "2"
        {
            if(isprime[m] == true)
            {
                for(int j = m*m; j <= n; j += m)   // delete composite number from m*m
                    isprime[j] = false;
            }
        }
        int count =1;   //add "2" because the loop starts from "3"
        for(int k = 3; k <= n; k+=2)
        {
            if(isprime[k] == true)
                count++;
        }
        System.out.print(count);
    }
}