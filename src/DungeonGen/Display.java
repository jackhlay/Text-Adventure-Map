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
    public JTextPane mapInv;
    public JButton enterButton;

    public void run(){
        Player p = gamestate.Player;

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
                   if(text.equalsIgnoreCase("map") && !mode.equals("ENCOUNTER")){
                       if(mode.equals("MAP") || gamestate.map==null){}
                       else{
                           mode = "MAP";
                           updateMap();
                       }
                   } else if (text.equalsIgnoreCase("inv") && !mode.equals("ENCOUNTER")){
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
                           if(gamestate.map[gamestate.currentX][gamestate.currentY].symbol == 'N'){
                               mode = "ENCOUNTER";
                               updateEnemyImage();
                           }
                           if (mode.equals("MAP")) {
                               updateMap();
                           } else if (mode.equals("INV")){
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

       mapInv = new JTextPane();
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

    private void updateMap() {
        StringBuilder content = new StringBuilder("<html><body style='text-align: center; color: yellow; font-size: 29px; font-family: monospace;'>");
        content.append("<br><br><br>");
        for (int j = gamestate.map[0].length - 1; j >= 0; j--) {
            content.append("<div>");
            for (int i = 0; i < gamestate.map.length; i++) {
                if (gamestate.discovered[i][j]) {
                    content.append(gamestate.map[i][j].symbol).append(" ");
                } else {
                    content.append("? ");
                }
            }
            content.append("</div>");
        }

        content.append("</body></html>");
        mapInv.setContentType("text/html");
        mapInv.setText(content.toString());
    }


    private void updateInv(){
        mapInv.setText("Ok this is the inventory screen brother");
    }

    private void updateEnemyImage() {
        String imagePath = "enemies/manticore.png"; // Update this path to your image file

        // Construct the HTML content with CSS styles for image scaling
        String content = "<html><body style='text-align: center;'><br><br>";
        content += "<img src='file:" + imagePath + "' style='max-width: 100%; max-height: 100%;'>";
        content += "</body></html>";

        mapInv.setContentType("text/html");
        mapInv.setText(content);
    }

    public static void main(String[] args) {
        Display d = new Display();
        d.run();
    }

}