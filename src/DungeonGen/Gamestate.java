import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Gamestate {
    public int difficulty = 0;
    public int currentX;
    public int currentY;
    public Room[][] map;
    public boolean[][] discovered;
    private Stack<Integer> Moves = new Stack();
    public Player Player;

    public Gamestate(){
        Player= new Player();
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
        int dir;
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
            List<Integer> directions = getDirections(x, y, width, height, visited, map);
            if(directions.isEmpty()){
                dir = (~Moves.pop() & 0b11);
                switch (dir){
                    case 0 -> y++;//up
                    case 1-> x++;//left
                    case 2-> x--;//right
                    case 3-> y--;//down
                }
                continue;
            }
            else {
                dir = rand.nextInt(directions.size());
            }
            switch (directions.get(dir)){
                case 0 -> {y++; Moves.push(0);}//up
                case 1-> {x++; Moves.push(1);}//left
                case 2-> {x--; Moves.push(2);}//right
                case 3-> {y--; Moves.push(3);}//down
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
            int roomChoice = rand.nextInt(Choices.size());
            if (map[origX][origY].symbol == Choices.get(roomChoice).symbol){
                map[x][y] = new emptyRoom();
            }else {
                map[x][y] = Choices.get(roomChoice);
            }
            if(Choices.get(roomChoice).symbol == 'T'){
                Choices.remove(roomChoice);
            }
            if(Choices.get(roomChoice).symbol == 't'){
                traps++;
                if(traps>2) {
                   Choices.removeIf(room -> room instanceof treasureTrap);
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
        Moves.clear();
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
        this.map=mapStart;
        Room[][] map = explore(width, height, this.map, visited);
        printMap(map);
        System.out.println("Player is currently at: " + (currentX+1) + "," + (currentY+1) + ". IT IS A(N) " + map[currentX][currentY].type + " ROOM");

        if (difficulty > 3){difficulty++;}
            return map;
    }

    public List<Integer> getDirections(int x, int y, int width, int height, boolean[][] visited, Room[][] Map){
        List<Integer> Directions = new ArrayList<>();
        if(isValidSpot(x, y+1, width, height, visited, Map)){
            Directions.add(0);
        }//up
        if(isValidSpot(x+1, y, width, height, visited, Map)){
            Directions.add(1);
        }//left
        if(isValidSpot(x-1, y, width, height, visited, Map)){
            Directions.add(2);
        }//right
        if(isValidSpot(x, y-1, width, height, visited, Map)){
            Directions.add(3);
        }//down

        return Directions;
    }

    public boolean isValidSpot(int x, int y, int width, int height, boolean[][] visited, Room[][] Map){
        return x >= 0 && x< width && y>= 0 && y<height && !visited[x][y] && map[x][y]==null;
        }

}
