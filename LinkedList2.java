
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



/**
 *
 * @author lin qi
 */
public class LinkedList2 {
    private static class Node { //  LinkedList2.Node 
	private int data;
        
	private Node next;
    }
    Node head, tail;
    private int size;
    //private int a,b,c;
    public LinkedList2(){
	head=null;
	tail=null;
        size=0;
    }
    public void addFirst(int v){
        Node temp=new Node();
        temp.data=v;
        if(head==null){
            head=temp;
            tail=temp;
            head.next=null;
            tail.next=null;
            size++;
            return;
        }
        temp.next=head;
        head=temp;
        size++;
    }
    public void addEnd(int v){
        Node temp=new Node();
        temp.data=v;
        if(head==null){
            head=temp;
            tail=temp;
            head.next=null;
            tail.next=null;
            size++;
            return;
        }
        tail.next=temp;
        tail=temp;
        size++;
    }
    public int removeEnd(){
        if(isEmpty())
            return -1;
        if(head.next==null){
            int v=head.data;
            head=null;
            tail=null;
            size--;
            return v;
            
        }
            
        Node p;
        for(p=head;p.next.next!=null;p=p.next)
            ;
        p.next=null;
        int v=tail.data;
        tail=p;
        size--;
        return v;
    }
    public int removeFirst(){
        if(isEmpty())
            return -1;
        if(head.next==null){
            int v=head.data;
            head=null;
            tail=null;
            size--;
            return v;
        }
        Node temp = head;
        head = head.next;
        size--;
        return temp.data;
        
    }
    boolean isEmpty(){
        if(head==null)
            return true;
        return false;
    }
    public void ADD_FRONT(int a,int b,int c){        
        for(int i=a;i<=c;i+=b){
            addFirst(i);
        }
    }
    public void ADD_BACK(int a,int b,int c){
        for(int i=a;i<=c;i+=b){
            addEnd(i);
        }
    }
    public void REMOVE_FRONT(int n){
        for(int i=0;i<n;i++)
            removeFirst();
    }
    public void REMOVE_BACK(int n){
        for(int i=0;i<n;i++)
            removeEnd();
    }

    public static class Iterator {
        private LinkedList2 list;
        private int pos;

        public Iterator(LinkedList2 list) {
            this.list = list;
            pos = 0;
        }

        public boolean hasNext() {
            if(pos < list.size){
                return true;
            }
            return false;
        }

        public int next() {
            
            Node p = list.head;
            for(int i=0;i<pos;i++)
                p=p.next;
            int v = p.data;
            pos++;
            return v;
        }
    }
    public static void main(String[] args) throws FileNotFoundException{
        String input = "";
        Scanner keyboard = new Scanner(new File("HW4b.txt"));
        String line;        
        while (keyboard.hasNextLine()) {
            line = keyboard.nextLine();            
            if (line.isEmpty()) {
                break;
            }
            input += line + "\n";
        }
        LinkedList2 list = new LinkedList2();
        String[] split = input.split("\n");
        String[] temp;
        String[] num;
        int a,b,c,n;
        
        for(int i=0;i<split.length;i++){
            temp = split[i].split("\\s+");
            if("ADD_FRONT".equals(temp[0])){
                num = temp[1].split(":");
                a = Integer.parseInt(num[0]);
                b = Integer.parseInt(num[1]);
                c = Integer.parseInt(num[2]);
                list.ADD_FRONT(a, b, c);
            }
            if("ADD_BACK".equals(temp[0])){
                num = temp[1].split(":");
                a = Integer.parseInt(num[0]);
                b = Integer.parseInt(num[1]);
                c = Integer.parseInt(num[2]);
                list.ADD_BACK(a, b, c);
            }
            if("REMOVE_FRONT".equals(temp[0])){
                n = Integer.parseInt(temp[1]);
                list.REMOVE_FRONT(n);
            }
            if("REMOVE_BACK".equals(temp[0])){
                n = Integer.parseInt(temp[1]);
                list.REMOVE_BACK(n);
            }
            if("OUTPUT".equals(temp[0])){
                Iterator ite = new Iterator(list);
                while(ite.hasNext()){ 
                    System.out.print(ite.next()+" "); 
                } 
                System.out.println();
                ite.pos=0;
            }
        }

//        LinkedList2 list = new LinkedList2();
//        list.ADD_FRONT(2, 2, 10);
        //list.ADD_BACK(1, 5, 11);
        //list.REMOVE_FRONT(3);
        //list.REMOVE_BACK(1);
//        list.removeEnd();
//        System.out.println(list.head.data);
//        System.out.println(list.head.next.data);
//       System.out.println(list.head.next.next.data);
//       System.out.println(list.head.next.next.next.data);
//       System.out.println(list.head.next.next.next.next.data);
    }
}

