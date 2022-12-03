import javax.swing.*;

import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 4; //scale factor

    final int tileSize = originalTileSize*scale;
    final int maxScreenCol = 12;
    final int maxScreenRow = 14;
    final int screenHeight = maxScreenCol*tileSize;
    final int screenWidth = maxScreenRow*tileSize;

    Thread gameThread;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startgThread(){
        gameThread= new Thread(this);
        gameThread.run();
    }

    @Override
    public void run() {
        while(gameThread != null){
            // UPDATE
            update();
            // REDRAW
            repaint();
        }
    }
    public void update(){

    }
    public void draw(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.lightGray);
        g2.fillRect(100,100, tileSize, tileSize);
        g2.dispose();
    }

}
