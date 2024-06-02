import java.util.Random;

public class Gamestate {
    private Room[][] map;
    public int difficulty = 0;
    public int currentX;
    public int currentY;


    public Gamestate(){
        Random rand = new Random();
        if((difficulty>0) && (difficulty<4)){
            //map = new char[20][20];
            map = dungeonGen(20,20);
        }
        else{
            //map = new char[rand.nextInt(37)][rand.nextInt(31)];

            map = dungeonGen(rand.nextInt(37), rand.nextInt(31));
        }

    }

    public Room[][] dungeonGen(int width, int height){
        boolean[][] visited = new boolean[width][height];
        map = new Room[width][height];


        difficulty++;
        return map;
    }

    public Room[][] explore(int width, int height, Room[][] map, boolean[][]visited) {
        Random rand = new Random();
        int x,y;
        if (difficulty == 1) {
            x = y = 0;
        } else {
            y = rand.nextInt(height);
            x = rand.nextInt(width);
        }
        visited[x][y] = true;
        map[x][y] = new entryRoom();

        return map;
    }
}
