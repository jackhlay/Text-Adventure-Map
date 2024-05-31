import java.util.Random;

public class Gamestate {
    char[][] map;
    int difficulty = -1;

    public Gamestate(){
        if((difficulty>0) && (difficulty<4)){
            map = new char[10*difficulty][10*difficulty];
        }
        else{
            Random rand = new Random();
            map = new char[rand.nextInt(45)][rand.nextInt(35)];
        }

    }
}
