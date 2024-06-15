import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

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
       Player p = new Player();

       Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");

       window = new JFrame("Dungeon Crawler");
       window.setLayout(new GridLayout(0,2));
       window.setSize(1280,720);
       window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       window.setIconImage(icon);

       textInterface = new JPanel();
       textInterface.setLayout(new BorderLayout());
       inputPanel = new JPanel(new FlowLayout());

       input = new JTextField(43);

       history = new JTextArea("Enter a name for your adventurer");
       history.setEditable(false);
       history.setLineWrap(true);
       history.setWrapStyleWord(true);
       history.setFont(history.getFont().deriveFont(19f));

       scroller = new JScrollPane(history);
       scroller.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));

       enterButton = new JButton("ENTER");
       enterButton.setBackground(Color.gray);
       enterButton.setForeground(Color.black);
       enterButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String text = input.getText();
               if(text.isEmpty()){

               }
               if(!p.Name.isEmpty() && !text.isEmpty()){
                   history.append("\n"+p.Name+" > "+text);
                   input.setText("");
               }
               if(p.Name.isEmpty() && !text.isEmpty()){
                   p.Name = input.getText();
                   history.setText("Hello " + p.Name + "! Welcome to the Dungeon!");
                   history.append("\nPlease choose a difficulty Easy, Medium, or Hard");
                   input.setText("");
               }
               else if(!text.isEmpty()){
                   if(text.equalsIgnoreCase("map")){
                       if(mode.equals("MAP") || gamestate.map==null){}
                       else{
                           mode = "MAP";
                           updateMap();
                       }
                   } else if (text.equalsIgnoreCase("inv")){
                       if(mode.equals("INV")){}
                       else {
                           mode = "INV";
                           updateInv();
                       }
                   }else if (text.equalsIgnoreCase("clear")) {
                       history.setText(null);
                   }else {
                       //System.out.println("GOING TO THE HANDLER");
                       try {
                           String out = handler.handleCommand(text);
                           history.append("\n" + out);
                           p.Location[0] = gamestate.currentX;
                           p.Location[1] = gamestate.currentY;
                           gamestate.discovered[gamestate.currentX][gamestate.currentY] = true;
                           if (mode.equals("MAP")) {
                               updateMap();
                           } else {
                               updateInv();
                           }
                       }
                       catch (Exception exception){
                           System.out.println(exception);
                       }

                   }
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

       mapInv = new JTextArea();
       mapInv.setEditable(false);
       mapInv.setBackground(Color.decode("#4a1d01"));
       mapInv.setForeground(Color.yellow);
       mapInv.setFont(mapInv.getFont().deriveFont(29f));

       window.add(textInterface);
       window.add(mapInv);
       window.setVisible(true);
       input.requestFocus();
       window.getRootPane().setDefaultButton(enterButton);
       System.out.println("IN PROGRESS");
    }

    private void updateMap(){
        mapInv.setText("");
        for (int j = gamestate.map[0].length-1; j >= 0; j--) {
            for (int i = 0; i < gamestate.map.length; i++) {
                if (gamestate.discovered[i][j]) {
                    mapInv.append(gamestate.map[i][j].symbol + " ");
                } else {
                    mapInv.append("? ");
                }
            }
            mapInv.append("\n");
        }
    }

    private void updateInv(){
        mapInv.setText("Ok this is the inventory screen brother");
    }

    public static void main(String[] args) {
        Display d = new Display();
        d.run();
    }

}