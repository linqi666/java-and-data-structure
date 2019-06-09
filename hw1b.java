/*
Author: Lin Qi
*/
import java.util.*;

public class hw1b {
    static int n;
    static long[][] arr;
    public static void pascal() {    //write a function to generate the pascal triangle, which is the result of choose()
        n = 501;
        arr = new long[n][n];
        for (int l = 0; l < n; l++) {
            for (int i = 0; i <= l; i++) {
                if (l == i || i == 0)
                    arr[l][i] = 1;
                else
                    arr[l][i] = arr[l - 1][i - 1] + arr[l - 1][i];
            }
        }
    }

    public static double choose(int n, int r) {

        long result=arr[n][r];   //final result is the n line, r row of the pascal triangle
        return result;}


    public static void main(String[] args) {
        int numTrials = 100000000;
        Random rnd = new Random(0);
        pascal();
        for (int i = 0; i < numTrials; i++) {
            int n = rnd.nextInt(501);
            int r = rnd.nextInt(n + 1);

            choose(n, r);
        }
    }
}

