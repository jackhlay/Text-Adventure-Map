package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean Up, Left, Right, Down;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP){
            Up=true;
        }
        if(code == KeyEvent.VK_LEFT){
            Left = true;
        }
        if(code == KeyEvent.VK_DOWN){
            Down = true;
        }
        if(code==KeyEvent.VK_RIGHT){
            Right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {
            Up = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            Left = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            Down = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            Right = false;
        }
    }
}
