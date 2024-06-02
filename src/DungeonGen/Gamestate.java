import java.util.Random;

public class Gamestate {
    private Room[][] map;
    public int difficulty = 0;
    public int currentX;
    public int currentY;

    public Gamestate(){
//        Random rand = new Random();
//        if((difficulty>0) && (difficulty<4)){
//            //map = new char[20][20];
//            map = dungeonGen(20,20);
//        }
//        else{
//            //map = new char[rand.nextInt(37)][rand.nextInt(31)];
//
//            map = dungeonGen(rand.nextInt(37), rand.nextInt(31));
//        }

    }

    public void printMap(Room[][] r){
        for(int j = r[0].length - 1; j >= 0; j--){
            for(int i = 0; i < r.length; i++){
                if(r[i][j] != null) {
                    System.out.printf(r[i][j].symbol + " ");
                } else {
                    System.out.printf("X ");
                }
            }
            System.out.println();
        }
    }



    public Room[][] explore(int width, int height, Room[][] map, boolean[][]visited) {
        Random rand = new Random();
        int origX,origY,x,y;
        if (difficulty == 1) {
            currentX = currentY = origX = origY = x = y = 0;
        } else {
            origX = x = currentX = rand.nextInt(width);
            origY = y = currentY = rand.nextInt(height);
        }
        visited[x][y] = true;
        map[x][y] = new entryRoom();

        int amountRooms = rand.nextInt(7,(int)Math.floor((width*height)/4)>7? (int)Math.floor((width*height)/4): (width*height)/2);

        while(amountRooms > 0){
            switch (rand.nextInt(4)+1){
                case 1:
                    y++; break;//up
                case 2:
                    y--; break;//down
                case 3:
                    x++; break;//left
                case 4:
                    x--; break;//right
            }
            if (x<0|| x>= width || y<0 || y>=height || map[x][y]!=null){
                x = origX;
                y = origY;
                continue;
            }
            map[x][y] = new emptyRoom();
            visited[x][y] = true;
            origX = x;
            origY = y;

            amountRooms--;
        }
        for(int i=0; i<map.length; i++){
            for(int j=0; j<map[0].length; j++){
                if(!visited[i][j]){
                    map[i][j] = new wall();
                }
            }

        }
        return map;
    }

    public Room[][] dungeonGen(int width, int height){
        // procedural gen stuff:
        // https://gamedev.stackexchange.com/questions/47917/procedural-house-with-rooms-generator
        // https://pastebin.com/Nr8PjmKF
        boolean[][] visited = new boolean[width][height];
        Room[][] mapStart = new Room[width][height];
        map = explore(width, height, mapStart, visited);
        printMap(map);

        if (difficulty > 3){difficulty++;}
            return map;
        }
}
