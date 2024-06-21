import java.util.ArrayList;

public class Room {
    protected char symbol;
    protected String type;
    protected boolean hostile;
    protected boolean loot;
    protected ArrayList<Adversary> Enemies;
//    public Room[] choices = {new entryRoom(), new treasureRoom(), new treasureTrap(), new emptyRoom()};

    public Room(char symbol, String type, boolean hostile, boolean loot, ArrayList<Adversary> enemies) {
        this.symbol = symbol;
        this.type = type;
        this.hostile = hostile;
        this.loot = loot;
        this.Enemies = enemies;
    }
}

class entryRoom extends Room{
    public entryRoom() {
        super('E', "Entry", false, false, new ArrayList<Adversary>());
    }
}

class treasureRoom extends Room{
    public treasureRoom() {
        super('T', "Treasure", false, true, new ArrayList<Adversary>());
    }
}

class treasureTrap extends Room{
    public treasureTrap() {
        super('T', "Treasure*", true, false,new ArrayList<Adversary>());
        Enemies.add(new mimic());
    }
}

class encounter extends Room{
    public encounter(){
        super('N',"Encounter",true,false,new ArrayList<Adversary>());
    }
}

class bossRoom extends Room{
    public bossRoom(){
        super('B',"Boss Room",true,false,new ArrayList<Adversary>());
    }

}

class emptyRoom extends Room{
    public emptyRoom() {
        super('e', "Empty", false, false, new ArrayList<Adversary>());
    }
}

class wall extends Room{
    public wall(){
        super('X', "wall",false, false, new ArrayList<Adversary>());
    }
}