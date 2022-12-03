package Main;

import Entity.Player;

import javax.swing.*;

import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 4; //scale factor

    public final int tileSize = originalTileSize*scale;
    final int maxScreenCol = 12;
    final int maxScreenRow = 14;
    final int screenHeight = maxScreenCol*tileSize;
    final int screenWidth = maxScreenRow*tileSize;

    int FPS = 60;

    KeyHandler kh = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, kh);

    //setting position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 6;


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        setFocusable(true);
    }

    public void startgThread(){
        gameThread= new Thread(this);
        gameThread.run();
    }

    @Override
    public void run() {
        double drawint = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawcount = 0 ;

        while (gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawint;
            timer += (currentTime-lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawcount++;

            }
            if(timer > 1000000000){
                System.out.printf("FPS: " + drawcount + '\n');
                drawcount=0;
                timer=0;
            }
        }
    }
    public void update(){
        player.update();


    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        g2.dispose();
    }

}
