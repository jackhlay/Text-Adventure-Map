package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler kh;

    public Player(GamePanel gp, KeyHandler kh){
        this.gp = gp;
        this.kh = kh;
        setDefVals();
    }
    public void setDefVals(){
        x=350;
        y=350;
        speed=5;

    }

    public void update(){
        if(kh.Up){
            y -= speed;
        }
        else if(kh.Down){
            y += speed;
        }
        else if(kh.Right){
            x += speed;
        }
        else if(kh.Left){
            x -= speed;

        }
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.fillRect(x,y, gp.tileSize, gp.tileSize);
    }
}
