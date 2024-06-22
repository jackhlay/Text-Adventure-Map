import java.util.*;

public class Gamestate {
    public int difficulty = 0;
    public int currentX;
    public int currentY;
    public Room[][] map;
    public boolean[][] discovered;
    private Stack<Integer> Moves = new Stack();
    public Player Player;
    private Map <String, Integer> damageTable =  new HashMap<>();
    public Map<String,String> getEnemy = new HashMap<>();
    public ArrayList<Adversary> t1Enemies = new ArrayList<>();
    public ArrayList<Adversary> t2Enemies = new ArrayList<>();
    public ArrayList<Adversary> t3Enemies = new ArrayList<>();
    public ArrayList<Adversary> bosses = new ArrayList<>();

    public Gamestate(){
        Player= new Player();
        getEnemy.put("Goblin", "enemies/goblin.png");
        getEnemy.put("Orc", "enemies/goblin.png");
        getEnemy.put("Zombie","enemies/zombie.png");
        getEnemy.put("Skeleton", "enemies/skeleton.png");
        getEnemy.put("Spider", "enemies/medspider.png");
        getEnemy.put("Wizard", "enemies/wizard.png");
        getEnemy.put("Necromancer", "enemies/wizard.png");
        getEnemy.put("Fire Elemental", "icon.png");
        getEnemy.put("Shape Shifter", "icon.png");
        getEnemy.put("Void Walker", "icon.png");
        getEnemy.put("Sorcerer", "enemies/sorcerer.png");
        getEnemy.put("Lich", "enemies/sorcerer.png");
        getEnemy.put("Dragon", "enemies/dragon.png");
        getEnemy.put("Golem", "icon.png");
        getEnemy.put("Manticore", "enemies/manticore.png");
        getEnemy.put("Basilisk", "enemies/Basilisk.png");
        getEnemy.put("Mimic", "enemies/chest.png");

        damageTable.put("Sword", 7);
        damageTable.put("Fists", 3);

        Collections.addAll(t1Enemies, new goblin(), new orc(), new zombie(), new skeleton());
        Collections.addAll(t2Enemies, new medSpider(), new wizard(), new necromancer());
        Collections.addAll(t3Enemies, new fireElemental(), new shapeshifter(), new voidwalker(), new sorcerer());
        Collections.addAll(bosses, new dragon(), new golem(), new manticore(), new basilisk());
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
            if(Choices.get(roomChoice).symbol=='N'){
                if(difficulty==1){
                    int enemyNum = rand.nextInt(t1Enemies.size());
                    map[origX][origY].Enemies.add(t1Enemies.get(enemyNum));

                } else if (difficulty==2) {
                    int enemyNum = rand.nextInt(t2Enemies.size());
                    map[origX][origY].Enemies.add(t2Enemies.get(enemyNum));

                } else if (difficulty >=3) {
                    int enemyNum = rand.nextInt(t3Enemies.size());
                    map[origX][origY].Enemies.add(t3Enemies.get(enemyNum));

                }
            }
            if(Choices.get(roomChoice).symbol=='B'){
                int enemyNum = rand.nextInt(bosses.size());
                map[origX][origY].Enemies.add(bosses.get(enemyNum));
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

    public String attack(int x, int y){
        Random rand = new Random();
        if(map==null){return "Choose a difficulty first please)";}
        if(Player.Health<=0){return "You are dead!";}
        if(map[x][y].Enemies.isEmpty()){
            if(Player.equippedWeapon.equals("Sword")){
                if(rand.nextInt(8192)+1 == 8192){
                    Player.equippedWeapon = "Fists";
                    return"You swing your sword, rather recklessly, and it slips from your grip! It clangs against the wall, and falls apart upon impact, you now don't have a sword.";
                }
                return"You swing your sword recklessly at the empty room in front of you.";
            }else {return "You swing your fists aimlessly in the empty room.";}
        }else{
            int baseDamage = damageTable.get(Player.equippedWeapon);
            float critChance = rand.nextFloat();
            float finalDamage=0;
            if(Player.equippedWeapon.equals("Sword")){
                if(critChance<=.1){
                    finalDamage=baseDamage*2;
                    map[x][y].Enemies.get(0).health-=finalDamage;
                    if(map[x][y].Enemies.get(0).health<=0){
                        String message = "You have slain " + map[x][y].Enemies.get(0).type;
                        map[x][y].Enemies.remove(0);
                        return message;
                    }else {
                        return "You slice the " + map[x][y].Enemies.get(0).type + " and deal " + finalDamage + " damage\n"+ advAttack(x,y);
                    }
                }
            }else{
                if (Player.equippedWeapon.equals("Fists")) {
                    if(critChance <= .05){
                        finalDamage = (float) (baseDamage * 1.2);
                    }
                    map[x][y].Enemies.get(0).health-=finalDamage;
                    if(map[x][y].Enemies.get(0).health<=0){
                        String message = "You have slain " + map[x][y].Enemies.get(0).type;
                        map[x][y].Enemies.remove(0);
                        return message;
                    }
                    else {
                        return "You punch the " + map[x][y].Enemies.get(0).type + " and deal " + finalDamage + " damage\n" + advAttack(x,y);
                    }
                }
            }

            map[x][y].Enemies.get(0).health-=baseDamage;
            if(map[x][y].Enemies.get(0).health<=0){
                String message = "You have slain " + map[x][y].Enemies.get(0).type;
                map[x][y].Enemies.remove(0);
                return message;

                }
            else {
                return "you deal " + baseDamage + " to the " + map[x][y].Enemies.get(0).type +"\n" + advAttack(x,y);
            }
        }
    }

    public String advAttack(int x, int y){
        Random rand = new Random();
        float roll = rand.nextFloat();
        Adversary enemy = map[x][y].Enemies.get(0);
        if (roll >= .6){
            Player.Health -= enemy.attackDamage;
            if(Player.Health<=0){return "You have been slain!";}
            else {
                return enemy.type + " dealt " + enemy.attackDamage + " to you!";
            }
        }else if(roll >= .3){
            Player.Health -= enemy.attackDamage*.6;
            if(Player.Health<=0){return "You have been slain!";}
            else {
                return enemy.type + " dealt " + enemy.attackDamage * .6 + " to you!";
            }
        }
        else {
            return enemy.type + " missed their attack";
        }

    }
}
