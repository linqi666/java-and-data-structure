

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author lin qi
 */
public class Trie {
    class Node {
        Node[] next;
        boolean isEnd;

        public Node() {
            this.next = new Node[26];
        } 
    }

    private Node root;
 
    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node p = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            int index = c-'a';
            if(p.next[index]==null){
                Node temp = new Node();
                p.next[index]=temp;
                p = temp;
            }else{
                p=p.next[index];
            }
        }
        p.isEnd=true;
    }

    public boolean search(String word) {
        Node p = searchNode(word);
        if(p==null){
            return false;
        }else{
            if(p.isEnd)
                return true;
        }
 
        return false;
    }

    public boolean prefix(String prefix) {
        Node p = searchNode(prefix);
        if(p==null){
            return false;
        }else{
            return true;
        }
    }
 
    public Node searchNode(String s){
        Node p = root;
        for(int i=0; i<s.length(); i++){
            char c= s.charAt(i);
            int index = c-'a';
            if(p.next[index]!=null){
                p = p.next[index];
            }else{
                return null;
            }
        } 
        if(p==root)
            return null; 
        return p;
    }
    public static void main(String[] args) throws FileNotFoundException{
        Trie dict = new Trie();
        Scanner keyboard = new Scanner(new File("dict.txt"));
        String line;        
        while (keyboard.hasNextLine()) {
            line = keyboard.nextLine();            
            if (line.isEmpty()) {
                break;
            }
            dict.insert(line);
        }
        Scanner hw = new Scanner(new File("hw5.txt"));
        String input;
        while(hw.hasNextLine()){
            input = hw.nextLine();
            if (input.isEmpty()) {
                break;
            }
            System.out.println(input+" : "+dict.search(input));
        }
        //dict.insert("lalala");
    }
}
