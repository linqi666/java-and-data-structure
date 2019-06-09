
package matrix;

import java.io.*;


/**
 *
 * @author lin qi
 */
public class Matrix {
    private double[][] matr;
    private int row,col;
    public Matrix(int m,int n,double v){
        row=m;
        col=n;
        matr = new double[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++)
                matr[i][j]=v;
        }        
    }
    public Matrix(int m,int n){
        row=m;
        col=n;
        matr = new double[row][col];
    }
    public Matrix add(Matrix a){
        if(this.row!=a.row || this.col!=a.col){
            throw new RuntimeException("Bad Size");
        }
        Matrix ans=new Matrix(a.row,a.col);
        for(int i=0;i<a.row;i++){
            for(int j=0;j<a.col;j++)
                ans.matr[i][j]=a.matr[i][j]+this.matr[i][j];
        }
        return ans;
    }

    public Matrix mul(Matrix a){
        if(this.col!=a.row){
            throw new RuntimeException("Bad Size");
        }
        Matrix ans=new Matrix(this.row,a.col);
        double res;
        for(int i=0;i<this.row;i++){
            for(int j=0;j<a.row;j++){
                for(int k=0;k<a.col;k++){
                    ans.matr[i][k] += this.matr[i][j] * a.matr[j][k];
                }
            }
        }
        return ans;
    }
    public Matrix copy(){
        Matrix ans=new Matrix(this.row,this.col);
        for(int i=0;i<this.row;i++){
            for(int j=0;j<this.col;j++){
                ans.matr[i][j] = this.matr[i][j];
            }
        }
        return ans;
    }

    public void set(int a,int b,double v){
        this.matr[a-1][b-1]=v; 
    }
    public double get(int a,int b){
        double tem=this.matr[a-1][b-1];
        return tem;
    }
    

    public Matrix addCol(double[] a){
        int m = this.row;
        int n = this.col;
        Matrix temp = new Matrix(m,n+1);
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
    public static void solve(Matrix a,double[] b){
        a = a.addCol(b);
        double[][] temp = a.matr;
        double ratio,pivot,ans;
        
        double[] res = new double[a.row];
        double[] max,tempRow;
        
        for(int k=0;k<a.row-1;k++){
            // select row with maximum absolute value
            int maxIndex=-1;
            double tem= -1;
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
//            if(temp[k][k] != 1){
//                for(int i = a.row;i>=k;i--)
//                    temp[k][i] = temp[k][i]/temp[k][k];
//            }
//            System.out.println(k+" pass");
//            for(int i=0;i<a.col;i++){
//                System.out.print(temp[k][i]+" ");
//                
//            }System.out.println();
            for(int i=k;i<a.row-1;i++){
                ratio = temp[i+1][k]/temp[k][k];
                
                //System.out.println(i+1+" row");
                //System.out.println("ratio: "+ratio);
                for(int j=k;j<a.col;j++){
                    temp[i+1][j]=temp[i+1][j]-ratio*temp[k][j];
                    
                    //System.out.print(temp[i+1][j]+" ");
                }
                //System.out.println();
            }            
        }
        if(temp[a.row-1][a.row-1]==0)
            throw new RuntimeException("No unique solution exists");
//        for(int i=0;i<temp.length;i++)
//            System.out.println(temp[i][0]+" "+temp[i][1]+" "+temp[i][2]+" "+temp[i][3]+" "+temp[i][4]+" "+temp[i][5]);
        ans=temp[a.row-1][a.row]/temp[a.row-1][a.row-1];
        res[a.row-1]=ans;
        // back-substitution
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
            System.out.println(res[i]);  //String.format("%.6f", res[i])
    }
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Matrix a=new Matrix(3,4,0.0);
        Matrix b=new Matrix(4,2,1.0);
        //Matrix c=a.add(b);
        Matrix c = a.add(a);
        a.set(1, 1, 3.2);
        a.get(1, 1);
        Matrix e = a.mul(b);
        Matrix d=new Matrix(3,3);
        Matrix f = a.copy();
        d.set(1, 1, 1);
        d.set(1, 2, 2);
        d.set(1, 3, 1);
        d.set(2, 1, 2);
        d.set(2, 2, -1);
        d.set(2, 3, 1);
        d.set(3, 1, 3);
        d.set(3, 2, 0);
        d.set(3, 3, 2);
        double[] r=new double[]{8,3,9};
        //solve(d,r);
        // the code above is the test of functions
        
        BufferedReader br = new BufferedReader(new FileReader("hw5.dat"));
        String line;
        String[] split;
        line=br.readLine();
        int l = 0;
        int p = 0;
        double num;
        
        int n = Integer.parseInt(line);	
        double[] y = new double[n];
	Matrix A = new Matrix(n,n);
        while((line = br.readLine())!=null){
            //System.out.println(line);
            if(line.length() > 0){
                split = line.split("\\s+");
                if(split.length==n){
                    if(l < n){
                        for(int i=0;i<split.length;i++){
                            num = Double.parseDouble(split[i]);
                            A.set(l+1, i+1, num);
                            //System.out.print(A.get(l+1, i+1)+" ");
                            
                        }
                        l++;
                    }else{
                        for(int i=0;i<split.length;i++)
                            y[i] = Double.parseDouble(split[i]);
                    }
                    
                }
                if(split.length==1){
                    y[p] = Double.parseDouble(split[0]);
                    p++;
                }
            }
        }
        br.close();
        solve(A,y);
        //System.out.println("The answer is not accurate");
    }
}
