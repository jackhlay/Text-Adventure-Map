import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JPanel implements Runnable{
    public String mode = "INV";

    public void run(){
       JFrame window = new JFrame();
       window.setLayout(new GridLayout(0,2));
       window.setSize(1280,720);

       JPanel TextInterface = new JPanel();
       TextInterface.setLayout(new BorderLayout());
       JPanel InputPanel = new JPanel(new FlowLayout());

       JTextField input = new JTextField(37);
       JTextArea History = new JTextArea("TESTESTESTESTEST");
       JButton EnterButton = new JButton("ENTER");
       EnterButton.setBackground(Color.gray);
       EnterButton.setForeground(Color.black);
       EnterButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String text = input.getText();
               if(text.equals("")){
//                   ;//do nothing
               }
               else {
                   History.append("\n"+text);
                   input.setText("");
               }
           }
       });

       InputPanel.add(input);
       InputPanel.add(EnterButton);
       InputPanel.setBackground(Color.black);
       InputPanel.setForeground(Color.white);

       History.setBackground(Color.black);
       History.setForeground(Color.white);
       TextInterface.add(InputPanel, BorderLayout.SOUTH);
       TextInterface.add(History, BorderLayout.CENTER);

       JTextArea MapInv = new JTextArea("THIS IS THE MAP / INVENTORY SCREEN");
       MapInv.setBackground(Color.decode("#4a1d01"));
       MapInv.setForeground(Color.yellow);
       if (this.mode.equals("MAP")){
           MapInv.setText("WORKING ON MAP");
        } else if (this.mode.equals("INV")) {
           MapInv.setText("WORKING ON INVENTORY SCREEN");

       }

        window.add(TextInterface);
       window.add(MapInv);
       window.setVisible(true);
       System.out.println("IN PROGRESS");
    }

    public static void main(String[] args) {
        Display d = new Display();
        d.run();
    }

}