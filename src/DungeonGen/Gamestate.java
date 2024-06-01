import java.util.Random;

public class Gamestate {
    char[][] map;
    int difficulty = -1;

    public Gamestate(){
        Random rand = new Random();
        if((difficulty>0) && (difficulty<4)){
            map = new char[20][20];
            dungeonGen(20,20,difficulty);
        }
        else{
            map = new char[rand.nextInt(43)][rand.nextInt(37)];
            dungeonGen(map.length, map[0].length, difficulty);
        }

    }

    public void dungeonGen(int height, int width, int level){
        if(level==1){int startRow, startCol = 0;}
        else {
            Random rand = new Random();
            int startRow = rand.nextInt(height);
            int startCol = rand.nextInt(width);
        }



    }
}
