import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Gamestate {
    public int difficulty = 0;
    public int currentX;
    public int currentY;
    public Room[][] map;
    public boolean[][] discovered;

    public Gamestate(){
    }

    public void printMap(Room[][] r){
        for(int j = r[0].length - 1; j >= 0; j--){
            for (Room[] rooms : r) {
                if (rooms[j] != null) {
                    System.out.printf(rooms[j].symbol + " ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }



    public Room[][] explore(int width, int height, Room[][] map, boolean[][]visited) {
        Random rand = new Random();
        int amountRooms;
        int traps=0;

        List<Room> Choices= new ArrayList<>();
        Choices.add(new treasureRoom());
        Choices.add(new encounter());
        Choices.add(new treasureTrap());
        Choices.add(new emptyRoom());

        int origX,origY,x,y;
        if (difficulty == 1) {
            currentX = currentY = origX = origY = x = y = 0;
            amountRooms = rand.nextInt(9,17);
        } else {
            origX = x = currentX = rand.nextInt(width);
            origY = y = currentY = rand.nextInt(height);
            amountRooms = rand.nextInt(13,27);
        }
        visited[x][y] = true;
        map[x][y] = new entryRoom();

        while(amountRooms > 0){
            int dir = rand.nextInt(4)+1;
            int roomChoice = rand.nextInt(Choices.size());
            switch (dir){
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
            if (amountRooms == 1){
                map[x][y] = new bossRoom();
                visited[x][y]=true;
                amountRooms--;
                continue;
            }
            if (map[origX][origY].symbol == Choices.get(roomChoice).symbol){
                map[x][y] = new emptyRoom();
            }else {
                map[x][y] = Choices.get(roomChoice);
            }
            if(Choices.get(roomChoice).symbol == 'T'){
                Choices.remove(0);
            }
            if(Choices.get(roomChoice).symbol == 't'){
                traps++;
                if(traps>2) {
                   for(int i=0; i<Choices.size(); i++){
                       if(Choices.get(i).getClass() == treasureTrap.class){
                           Choices.remove(i);
                       }
                   }
                }
            }
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
        this.map=map;
        return map;
    }

    public Room[][] dungeonGen(int width, int height){
        // procedural gen stuff:
        // https://gamedev.stackexchange.com/questions/47917/procedural-house-with-rooms-generator
        // https://pastebin.com/Nr8PjmKF
        boolean[][] visited = new boolean[width][height];
        discovered = new boolean[width][height];
        Room[][] mapStart = new Room[width][height];
        Room[][] map = explore(width, height, mapStart, visited);
        printMap(map);

        if (difficulty > 3){difficulty++;}
            return map;
        }
}
