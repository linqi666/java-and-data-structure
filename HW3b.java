
/**
 *
 * @author lin qi
 */
import java.io.*;
import java.util.*; 
public class HW3b {
    public static void quicksort(int[] array,int L,int R){
        int i = L;
        int j = R;
        Random rand = new Random();
        int len = array.length;
        int x = L + rand.nextInt(R - L);
        int pivot = array[x];

        while (i <= j){
            while(array[i]<pivot)
                i++;
            while(array[j]>pivot)
                j--;
            if(i<=j){
                int tmp = array[j];
                array[j] = array[i];
                array[i] = tmp;
                i++;
                j--;
            }
        }
        
        if(i-L>1)
            quicksort(array,L,i-1);
        if(i<R)
            quicksort(array,i,R);
        
    }
    
    
    public static void main(String[] args)  throws IOException {
        Scanner scanner = new Scanner(new File("hw32.dat"));
        int n = scanner.nextInt();
        int[] data = new int[n];
        int i=0;
        while (scanner.hasNextInt()) {
            int m = scanner.nextInt();
            data[i++] = m;           
        }
        scanner.close();
        quicksort(data,0,n-1);
        for(int j=0;j<=n-1;j++){
            System.out.print(data[j]+" ");
        }
        
    }

}
