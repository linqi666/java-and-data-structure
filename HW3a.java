

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author lin qi
 */
public class HW3a {
    public static void insertsort(int[] array){
        int len = array.length;
        for(int i=0;i<len;i++){
            for(int j = i; j>0 && array[j-1]>array[j];j--){
                int tmp = array[j-1];
                array[j-1] = array[j];
                array[j] = tmp;
            }
        }
    }
    
    
    public static void main(String[] args)   throws IOException{
        Scanner scanner = new Scanner(new File("hw3.dat"));
        int n = scanner.nextInt();
        int[] data = new int[n];
        int i=0;
        while (scanner.hasNextInt()) {
            int m = scanner.nextInt();
            data[i++] = m;           
        }
        scanner.close();
        insertsort(data);
        for(int j=0;j<=n-1;j++){
            System.out.print(data[j]+" ");
        }
    }
}
