import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JPanel implements Runnable{
    public String mode = "INV";
    Gamestate gamestate = new Gamestate();
    commandHandler handler = new commandHandler(gamestate);
    public JFrame window;
    public JPanel textInterface;
    public JPanel inputPanel;
    public JTextField input;
    public JTextArea history;
    public JScrollPane scroller;
    public JTextArea mapInv;
    public JButton enterButton;

    public void run(){
       window = new JFrame();
       window.setLayout(new GridLayout(0,2));
       window.setSize(1280,720);
       window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

       textInterface = new JPanel();
       textInterface.setLayout(new BorderLayout());
       inputPanel = new JPanel(new FlowLayout());

       input = new JTextField(37);
       history = new JTextArea("TESTESTESTESTEST");
       scroller = new JScrollPane(history);
       scroller.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
       enterButton = new JButton("ENTER");
       enterButton.setBackground(Color.gray);
       enterButton.setForeground(Color.black);
       enterButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String text = input.getText();
               if(text.equals("")){
//                   ;//do nothing
               }
               else {
                   history.append("\n"+text);
                   input.setText("");
                   history.setCaretPosition(history.getDocument().getLength());
               }
           }
       });

       inputPanel.add(input);
       inputPanel.add(enterButton);
       inputPanel.setBackground(Color.black);
       inputPanel.setForeground(Color.white);

       history.setBackground(Color.black);
       history.setForeground(Color.white);
       textInterface.add(inputPanel, BorderLayout.SOUTH);
       textInterface.add(scroller, BorderLayout.CENTER);

       mapInv = new JTextArea("THIS IS THE MAP / INVENTORY SCREEN");
       mapInv.setBackground(Color.decode("#4a1d01"));
       mapInv.setForeground(Color.yellow);
       if (this.mode.equals("MAP")){
           mapInv.setText("WORKING ON MAP");
        } else if (this.mode.equals("INV")) {
           mapInv.setText("WORKING ON INVENTORY SCREEN");

       }

       window.add(textInterface);
       window.add(mapInv);
       window.setVisible(true);
       input.requestFocus();
       window.getRootPane().setDefaultButton(enterButton);
       System.out.println("IN PROGRESS");
    }

    public static void main(String[] args) {
        Display d = new Display();
        d.run();
    }

}