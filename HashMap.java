

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
/**
 *
 * @author lin qi
 */
public class HashMap {
    public static class Object{
        public String key;
        public Integer value;
    }
    public static final int ARR_size=128;
    private LinkedList<Object>[] arr = new LinkedList[ARR_size];
    public HashMap(){
        for(int i=0;i<ARR_size;i++){
            arr[i]=null;
        }
    }
    private Object getObj(String key){
        if(key==null)
            return null;
        int index = (key.hashCode() & 0x7fffffff)% ARR_size;
        LinkedList<Object> items = arr[index];
        if(items==null)
            return null;
        for(Object item : items){
            if(item.key.equals(key))
                return item;
        }
        return null;
    }
    
    public void put(String key, Integer value){
        int index=(key.hashCode() & 0x7fffffff)%ARR_size;
        LinkedList<Object> items=arr[index];
        if(items==null){
            items = new LinkedList<Object>();
 
            Object item = new Object();
            item.key = key;
            item.value = value;
 
            items.add(item);
 
            arr[index] = items;
        }
        else {
            for(Object item : items) {
                if(item.key.equals(key)) {
                    item.value = value;
                    return;
                }
            }
 
            Object item = new Object();
            item.key = key;
            item.value = value;
 
            items.add(item);
        }
    }
    public static void main(String[] args) throws FileNotFoundException{
        HashMap dict = new HashMap();
        Scanner keyboard = new Scanner(new File("dict.txt"));
        String line;        
        while (keyboard.hasNextLine()) {
            line = keyboard.nextLine();            
            if (line.isEmpty()) {
                break;
            }
            dict.put(line,1);
        }
        Scanner hw = new Scanner(new File("hw8.dat"));
        String input;
        
        while(hw.hasNextLine()){
            input = hw.nextLine();            
            if(dict.getObj(input)!=null){
                System.out.println(input+" : true");
            }
            else{
                System.out.println(input+" : false");
            }
        }
        for(int i =0;i<128;i++){
            System.out.println(i+" "+dict.arr[i].size());
        }
//        //dict.put("la",1);
//        String s = "lalala";
//        int t = (s.hashCode() & 0x7fffffff)% ARR_size;
//        System.out.print(t);
//        //dict.put(s,1);
    }
}
