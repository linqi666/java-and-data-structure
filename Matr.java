
package matrix;

/**
 *
 * @author cjssq
 */

import java.util.Random;

public class Matr {
    private double[][] matr;
    private int row,col;
    public Matr(int m,int n,double v){
        row=m;
        col=n;
        matr = new double[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++)
                matr[i][j]=v;
        }        
    }
    public Matr(int m,int n){
        row=m;
        col=n;
        matr = new double[row][col];
    }
    

    public void set(int a,int b,double v){
        this.matr[a-1][b-1]=v; 
    }
    public double get(int a,int b){
        double tem=this.matr[a-1][b-1];
        return tem;
    }
    

    public Matr addCol(double[] a){
        int m = this.row;
        int n = this.col;
        Matr temp = new Matr(m,n+1);
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                temp.matr[i][j]=this.matr[i][j];
                temp.matr[i][j+1]=a[i];
            }
        }
        temp.row=m;
        temp.col=n+1;
        return temp;
    }
    public static void partialPivot(Matr a,double[] b){
        a = a.addCol(b);
        double[][] temp = a.matr;
        double ratio,pivot,ans;
        
        double[] res = new double[a.row];
        double[] max,tempRow;
        
        for(int k=0;k<a.row-1;k++){
            // select row with maximum absolute value
            int maxIndex=-1;
            double tem= -1000;
            max = new double[a.row-k];
            for(int m=k;m<a.row;m++){
                //System.out.println(Math.abs(temp[m][k]));
                max[m-k] = Math.abs(temp[m][k]);
            }
            for(int n=0;n<max.length;n++){
                if(tem<max[n]){
                    tem = max[n];
                    maxIndex = n+k;
                }
            }
            if(tem==0)
                throw new RuntimeException("No unique solution exists");
            // interchange the row with the k row
            if(maxIndex!=k){
                tempRow = temp[maxIndex];
                temp[maxIndex] = temp[k];
                temp[k] = tempRow;
            }
            if(temp[k][k] != 1){
                for(int i = a.row;i>=k;i--)
                    temp[k][i] = temp[k][i]/temp[k][k];
            }
            for(int i=k;i<a.row-1;i++){
                ratio = temp[i+1][k]/temp[k][k];
                //System.out.println(ratio);
                for(int j=k;j<a.col;j++){
                    temp[i+1][j]=temp[i+1][j]-ratio*temp[k][j];                    
                }
//                for(int q=0;q<a.row;q++)
//                    System.out.println(temp[q][0]+"  "+temp[q][1]+"  "+temp[q][2]+"  "+temp[q][3]);
            }            
        }
        if(temp[a.row-1][a.row-1]==0)
            throw new RuntimeException("No unique solution exists");
        
        ans=temp[a.row-1][a.row]/temp[a.row-1][a.row-1];
        res[a.row-1]=ans;
        
        for(int i=a.row-1;i>0;i--){            
            for(int j=0;j<i ;j++){
                if(temp[i][i]==0)
                    throw new RuntimeException("No unique solution exists"); // if 1 diagonal value equals to zero, we will get infinite answers
                ratio = temp[j][i]/temp[i][i];
                temp[j][i]=temp[j][i]-ratio*temp[i][i];
                temp[j][a.row]=temp[j][a.row]-ratio*temp[i][a.row];
            }
            ans=temp[i-1][a.row]/temp[i-1][i-1];
            res[i-1]=ans;                        
        }
        
        for(int i=0;i<res.length;i++)
            System.out.println(res[i]);  
    }
    public static void fullPivot(Matr a,double[] b){
        a = a.addCol(b);
        double[][] temp = a.matr;
        double ratio,pivot,ans,p;
        
        double[] res = new double[a.row];
        double[] tempRow;
        double[][]max;
        
        for(int k=0;k<a.row-1;k++){
            // select row and col with maximum absolute value
            double tem= -1;
            int maxIndexR=-1;
            int maxIndexC=-1;
            max = new double[a.row-k][a.row-k];
            //System.out.println(max.length+" row");
            for(int m=k;m<a.row;m++){
                //System.out.println(Math.abs(temp[m][k]));
                for(int l=k;l<a.row;l++){
                    max[m-k][l-k] = Math.abs(temp[m][l]);
                    //System.out.println(max[m-k][l-k]);
                }                
            }
            for(int n=0;n<max.length;n++){
                for(int l=0;l<max.length;l++){
                    //System.out.println(k+" pass");
                    if(tem<max[n][l]){
                        tem = max[n][l];
                        maxIndexR = n+k;
                        maxIndexC = l+k;  
                        //System.out.println(l);
                        
                        //System.out.println(maxIndexR);
                        //System.out.println(maxIndexC);
                         
                        //System.out.println(k+l);
                    }
                }                
            }
            //System.out.println("final");
            //System.out.println(maxIndexR);
            //System.out.println(maxIndexC);
            if(tem==0)
                throw new RuntimeException("No unique solution exists");
            // interchange the row with the k row
            if(maxIndexR !=k ){
                tempRow = temp[maxIndexR];
                temp[maxIndexR] = temp[k];
                temp[k] = tempRow;
            }
            // interchange the col with the k col
            if(maxIndexC !=k ){
                for(int i=0;i<a.row;i++){
                    p = temp[i][maxIndexC];
                    temp[i][maxIndexC] = temp[i][k];
                    temp[i][k] = p;
                }
            }
            if(temp[k][k] != 1){
                for(int i = a.row;i>=k;i--)
                    temp[k][i] = temp[k][i]/temp[k][k];
            }
            for(int i=k;i<a.row-1;i++){
                ratio = temp[i+1][k]/temp[k][k];
                //System.out.println(ratio);
                for(int j=k;j<a.col;j++){
                    temp[i+1][j]=temp[i+1][j]-ratio*temp[k][j];
                }
            }            
        }
        if(temp[a.row-1][a.row-1]==0)
            throw new RuntimeException("No unique solution exists");
        ans=temp[a.row-1][a.row]/temp[a.row-1][a.row-1];
        res[a.row-1]=ans;
        
        for(int i=a.row-1;i>0;i--){            
            for(int j=0;j<i ;j++){
                if(temp[i][i]==0)
                    throw new RuntimeException("No unique solution exists"); // if 1 diagonal value equals to zero, we will get infinite answers
                ratio = temp[j][i]/temp[i][i];
                temp[j][i]=temp[j][i]-ratio*temp[i][i];
                temp[j][a.row]=temp[j][a.row]-ratio*temp[i][a.row];
            }
            ans=temp[i-1][a.row]/temp[i-1][i-1];
            res[i-1]=ans;                        
        }
        
        for(int i=0;i<res.length;i++)
            System.out.println(res[i]); 
    }
    public static void main(String[] args){
        
        Matr d=new Matr(3,3);
        d.set(1, 1, 5);
        d.set(1, 2, 12);
        d.set(1, 3, 9);
        d.set(2, 1, 8);
        d.set(2, 2, 11);
        d.set(2, 3, 20);
        d.set(3, 1, 16);
        d.set(3, 2, 5);
        d.set(3, 3, 7);
        double[] r=new double[]{5,35,29};
        //partialPivot(d,r);
        //fullPivot(d,r);
        
        
        Random r1 = new Random(11);
        Random r2 = new Random(20);
        Matr A=new Matr(10,10);
        Matr B=new Matr(20,20);
        double[] a = new double[10];
        double[] b = new double[20];
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                double v1 = r1.nextDouble()*200-10;
                A.set(i+1, j+1, v1);
            }
            a[i] = r1.nextDouble()*20-10;
            //System.out.println(v1);
        }
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                double v2 = r2.nextDouble()*200-10;
                B.set(i+1, j+1, v2);
            }
            b[i] = r2.nextDouble()*20-10;
            //System.out.println(v1);
        }
        long begin,end,cost;
        System.out.println("Partial Pivot 10*10 :");
        partialPivot(A,a);
        System.out.println();
        System.out.println("Full Pivot 10*10 :");
        fullPivot(A,a);
        System.out.println();
        System.out.println("Partial Pivot 20*20 :");
        partialPivot(B,b);
        System.out.println();
        System.out.println("Full Pivot 20*20 :");
        fullPivot(B,b);
        System.out.println();
        System.out.println("The full pivoting is more accurate. ");
    }
}

