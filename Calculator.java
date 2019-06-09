import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
/**
 *
 * @author lin qi
 */
public class Calculator extends JFrame implements ActionListener{
    private String[] content = {"7","8","9","/","4","5","6","*","1","2","3","-",".","0","=","+"};
    private String[] command = {"Backspace","c"};
    private JTextField result = new JTextField("0");
    private JButton contentButton[] = new JButton[content.length];
    private JButton commandButton[] = new JButton[command.length];
    private double output = 0.0;
    private boolean isFirst = true;
    private String operator = "=";
    public Calculator(){
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Calculator");
        setSize(500,500);
        Container c = getContentPane();
        result.setHorizontalAlignment(JTextField.LEFT);
        result.setFont(new Font("time",Font.PLAIN,60));
        result.setEditable(false);
        result.setBackground(Color.WHITE);
        result.setForeground(Color.BLACK);
        // create button and add to panels
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(4,4,3,3));
        contentPanel.setFont(new Font("time",Font.PLAIN,60));
        for(int i=0;i<content.length;i++){
            contentButton[i] = new JButton(content[i]);
            contentButton[i].setFont(new Font("time",Font.PLAIN,60));
            contentPanel.add(contentButton[i]);
        }
        JPanel commandPanel = new JPanel();
        commandPanel.setLayout(new GridLayout(1,2,3,3));
        
        for(int i=0;i<command.length;i++){
            commandButton[i] = new JButton(command[i]);
            commandButton[i].setFont(new Font("time",Font.PLAIN,40));
            commandPanel.add(commandButton[i]);            
        }
        for (int i = 0; i < content.length; i++) {
            contentButton[i].addActionListener(this);
        }
        for (int i = 0; i < command.length; i++) {
            commandButton[i].addActionListener(this);
        }
        commandPanel.setPreferredSize(new Dimension(500,50));
        contentPanel.setPreferredSize(new Dimension(500,360));
        result.setPreferredSize(new Dimension(500, 90));
        
        c.add(result,BorderLayout.NORTH);
        c.add(BorderLayout.CENTER,contentPanel);
        c.add(BorderLayout.SOUTH,commandPanel);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        String str = e.getActionCommand();
        if(str == "Backspace"){
            Backspace();
        } else if(str == "c"){
            Clean();
        } else if("0123456789.".indexOf(str) >= 0){
            Number(str);
        } else {
            Calculate(str);
        }
    }

    private void Backspace() {
        String text = result.getText(); 
        int i = text.length();
        if(i > 0){
            text = text.substring(0,i-1);
            if(text.length()==0){
                result.setText("0");
                isFirst = true;
                operator = "=";
            } else{
                result.setText(text);
            }
        }
    }

    private void Clean() {
        result.setText("0");
        isFirst = true;
        operator = "=";
    }

    private void Number(String num) {
        if(isFirst){
            result.setText(num);
        } else if(num == "." && result.getText().indexOf(".") < 0) {
            result.setText(result.getText() + ".");
        } else if(num != "."){
            result.setText(result.getText() + num);
        }
        isFirst = false;
    }

    private void Calculate(String num) {
        if(operator == "/"){
            output /= getNum();
        }else if(operator == "*"){
            output *= getNum();
        }else if(operator == "-"){
            output -= getNum();
        }else if(operator == "+"){
            output += getNum();
        }else if(operator == "="){
            output = getNum();
        }
        if (true) {            
            result.setText(String.valueOf(output));
        }
        operator = num;
        isFirst = true;
    }
    
    private double getNum(){
        double res = 0.0;
        res = Double.valueOf(result.getText()).doubleValue();
        return res;
    }
    
    public static void main(String[] args){
        new Calculator();
    }
}
