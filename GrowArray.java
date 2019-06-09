

import java.io.*;

import java.util.Scanner;

/**
 *
 * @author LIN QI
 */
public class GrowArray {
    private int capacity; // the size of the block of memory
    private int used;     // how many are used
    private double[] data;       // pointer to the block
    
    public GrowArray() {
        used = 0;
        capacity = 1;
        data = new double[capacity];
    }
    public GrowArray(int initialSize) {
        capacity=initialSize;
        data = new double[capacity];
        used = 0;
        //capacity = 1;
    }
    private void grow() {        
	capacity = 2*capacity;	// DOUBLE EACH TIME!!!
        double[] tem = new double[capacity];
        for(int i = 0;i<data.length; i++){
            tem[i] = data[i];            
        }
        data = tem;
    }
    private void checkGrow() {
	if (used == capacity)
            grow();
    }
    public void insertEnd(double v) { 
    	checkGrow();
	data[used] = v;   
        used++;
    }
    public void insertStart(double v) { 
	checkGrow();
	double[] old = data;
        data = new double[capacity];
	data[0] = v;
	for (int i = 0; i < used; i++)
            data[i+1] = old[i];
	used++;	// more work....
    }
    public void insert(int pos, double v) { 
        double[] old = data;
	data = new double[old.length+1];
	for (int j = 0; j < pos; j++)
	    data[j] = old[j];
	data[pos] = v;
	for (int j = pos+1; j < old.length; j++)
	    data[j+1] = old[j];
        used++;
    }
    public void removeStart() {
        double[] old = data;
        data = new double[capacity];
        used--;

	for (int i = 0; i < used; i++)
            data[i] = old[i+1];
    }

    public void removeEnd() {
         
        used--;
    }

    public String toString() {
        String s="" ;
        for(int i=0;i<used;i++)
            s=s +" "+String.valueOf(data[i]);
	return s;
    }
    public static double[] find(GrowArray arr){
        double ymax = -9999;
        double ymin = 9999;
        double xmax = -9999;
        double xmin = 9999;
        //double n=0.0;
        double[] a=new double[4];
        for(int i=0;i<arr.used/2;i++){
            if(xmax < arr.data[2*i])
                xmax = arr.data[2*i];
            if(xmin > arr.data[2*i])
                xmin = arr.data[2*i];
           
           
            if(ymax < arr.data[2*i+1])
                ymax = arr.data[2*i+1];
            if(ymin > arr.data[2*i+1])
                ymin = arr.data[2*i+1];
        }
        a[0]=xmax;
        a[1]=xmin;
        a[2]=ymax;
        a[3]=ymin;
        return a;
    }
    public static void main(String[] args) throws IOException{

//        GrowArray g = new GrowArray();
//        for (int i = 0; i < 10; i++)
//            g.insertStart(i);
//	for (int i = 0; i < 10; i++)
//            g.insertEnd(i);
//    	System.out.println(g);
//	for (int i = 0; i < 10; i++)
//            g.removeStart();
//    	g.removeEnd();
//	System.out.println(g);  
//      the output of the test above is correct
        GrowArray[] grid = new GrowArray[16*16];
        for(int m=0;m<256;m++)
            grid[m] = new GrowArray();
        Scanner scanner = new Scanner(new File("convexhullpoints.dat"));  
        double ymax = -99999;
        double ymin = 99999;
        double xmax = -99999;
        double xmin = 99999;
        double n=0.0;
        GrowArray array = new GrowArray();
        while (scanner.hasNextDouble()) {
            n = scanner.nextDouble();
            array.insertEnd(n);
            if(xmax <= n)
                xmax = n;
            if(xmin >= n)
                xmin = n;
            n = scanner.nextDouble();
            array.insertEnd(n);
            if(ymax <= n)
                ymax = n;
            if(ymin >= n)
                ymin = n;
        }
        scanner.close();
        System.out.println("xmax of the whole data is "+xmax);
        System.out.println("xmin of the whole data is "+xmin);
        System.out.println("ymax of the whole data is "+ymax);
        System.out.println("ymin of the whole data is "+ymin);
        System.out.println(array.used/2+" pairs/points");
        //System.out.println(array);
/*

     -----------------------
     | p0 | p1  | ..  | p15|
     |    |     |     |    |
     +----+-----+-----+----+
     | p16| p17 | ..  | .. |
     |    |     |     |    |
     +----+-----+-----+----+
     | .. | ..  | ..  | ...|
     |    |     |     |    |
     +----+-----+-----+----+
     |p240| ... | .. .|p255|
     |    |     |     |    |
     +----+-----+-----+----+
   */
        
        double x;
        double y;
        int i;
        int j;
        int p;  // p0, p1, p2,,,,p255
        for(int pair=0;pair<array.used/2;pair++){
            x = array.data[2*pair];
            y = array.data[2*pair+1];
            i = (int)((y - ymin) /(ymax-ymin)* 15);
            j = (int)((x - xmin)/(xmax-xmin) * 15);
            
            p = 16*i+j;
            if(j==15){
                System.out.println("the point with maxX is stored in p"+p);
            }
            if(i==15){
                System.out.println("the point with maxY is stored in p"+p);
            }
//            System.out.println(i);
//            System.out.println(j);
//            System.out.println(x);
//            System.out.println(y);
//            System.out.println(p);
            grid[p].insertEnd(x);
            grid[p].insertEnd(y);
        }
        System.out.println("size of each box");
        for(int c=0;c<255;c+=16){
            System.out.println(grid[c].used/2+"  "+grid[c+1].used/2+"  "+grid[c+2].used/2+"  "+grid[c+3].used/2+"  "+grid[c+4].used/2+"  "+grid[c+5].used/2+"  "+grid[c+6].used/2+"  "+grid[c+7].used/2+"  "+grid[c+8].used/2+"  "+grid[c+9].used/2+"  "+grid[c+10].used/2+"  "+grid[c+11].used/2+"  "+grid[c+12].used/2+"  "+grid[c+13].used/2+"  "+grid[c+14].used/2+"  "+grid[c+15].used/2+"  ");
            // only the points with maxX/Y will be saved in the last row/column
        }
        System.out.println("only the points with maxX/Y will be stored in the last row/column");
        for(int g=0;g<256;g++){
            double[] maxmin = find(grid[g]);
            if(grid[g].used<=1)
                System.out.println(grid[g].used/2+" points in the P"+g+" growarray");
            if(grid[g].used>1)
                System.out.println(grid[g].used/2+" points in the P"+g+" growarray, xmax is: "+maxmin[0]+" xmin is: "+maxmin[1]+" ymax is: "+maxmin[2]+" ymin is: "+maxmin[3]);
        }
    }
    
}
