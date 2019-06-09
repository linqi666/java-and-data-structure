import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

/**
 *
 * @author lin qi  
 */
public class TextEditor extends JFrame{
    private JScrollBar r,g,b;
    private JPanel p;
    private JTextArea read;
    private JTextArea write;
    public String color;
    public TextEditor(int w, int h,String title){
        super();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(title);
        setSize(w,h);
        Container c = getContentPane();
        // two textarea: read(show the content of file) and write
        read = new JTextArea(80,40);
        write = new JTextArea(80,40);
        Font f = new Font("time",Font.PLAIN,30);
        GridLayout gridLayout = new GridLayout(1,2,5,5);
        c.setLayout(gridLayout);
        c.add(read);
        c.add(write);
        read.setFont(f);
        write.setFont(f);
        // create menu
        JMenuBar mb = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        mb.add(fileMenu);
        JMenuItem lf = new JMenuItem("Load File");
        JMenuItem sf = new JMenuItem("Save File");       
        mb.add(fileMenu);
        fileMenu.add(lf);        
        fileMenu.add(sf);
        JMenu colorMenu = new JMenu("Color"); 
        mb.add(colorMenu);
        JMenuItem bc = new JMenuItem("BackGround Color");
        JMenuItem fc = new JMenuItem("Text Color");
        colorMenu.add(bc);        
        colorMenu.add(fc);
        // load file 
        lf.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                try{
                    JFileChooser flc = new JFileChooser();
                    int option = flc.showOpenDialog(null);
                    File file = new File(flc.getSelectedFile().getPath());
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    String line;
                    while ((line=br.readLine()) != null){
                        read.setText(line + "\n");
                        line = br.readLine();
                    }
                    br.close();
                } catch (Exception e){
                    e.printStackTrace();
                }

            }           
        });
        // save file
        sf.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                final JFileChooser SaveAs = new JFileChooser();               
                int option = SaveAs.showOpenDialog(null);
                File fileName = new File(SaveAs.getSelectedFile() + "");
                try{
                    JFileChooser saveFile = new JFileChooser();
                    saveFile.showSaveDialog(null);
                    BufferedWriter outFile = new BufferedWriter(new FileWriter(fileName));
                    outFile.write(write.getText()); //put in textfile

                    outFile.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }           
        });
        // background color
        bc.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                color = e.getActionCommand();
                new ColorChooser();
                
            }           
        });
        // foreground color
        fc.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                color = e.getActionCommand();
                new ColorChooser();
            }           
        });
        
        setJMenuBar(mb);
        
        setVisible(true);
    }

    public class ColorChooser extends JFrame{
        

        public ColorChooser(){
            super();
            Container c = getContentPane();
            c.setLayout(new GridLayout(1,4));
            setSize(600,400);
            r = new JScrollBar(JScrollBar.VERTICAL,0,15,0,255);
            g = new JScrollBar(JScrollBar.VERTICAL,0,15,0,255);
            b = new JScrollBar(JScrollBar.VERTICAL,0,15,0,255);
            ColorListener cl =new ColorListener();
            p = new JPanel();
            r.addAdjustmentListener(cl);
            g.addAdjustmentListener(cl);
            b.addAdjustmentListener(cl);
            c.add(r);
            c.add(g);
            c.add(b);
            c.add(p);
            setVisible(true);
        }
    }

    
    public static void main(String[] args){
        TextEditor t = new TextEditor(1000,900,"TextEditor");

    }
    class ColorListener implements AdjustmentListener {
        @Override
        public void adjustmentValueChanged(AdjustmentEvent e){
            p.setBackground(new Color(r.getValue(),g.getValue(),b.getValue()));
            if(color == "BackGround Color"){
                System.out.print("111");
                read.setBackground(new Color(r.getValue(),g.getValue(),b.getValue()));
                write.setBackground(new Color(r.getValue(),g.getValue(),b.getValue()));
            }
            if(color == "Text Color"){
                read.setForeground(new Color(r.getValue(),g.getValue(),b.getValue()));
                write.setForeground(new Color(r.getValue(),g.getValue(),b.getValue()));
            }
        }
    }}

