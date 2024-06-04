public class Room {
    protected char symbol;
    protected String type;
    protected boolean hostile;
    protected boolean loot;
    protected Adversary[] Enemies;
//    public Room[] choices = {new entryRoom(), new treasureRoom(), new treasureTrap(), new emptyRoom()};

    public Room(char symbol, String type, boolean hostile, boolean loot, Adversary[] enemies) {
        this.symbol = symbol;
        this.type = type;
        this.hostile = hostile;
        this.loot = loot;
        this.Enemies = enemies;
    }
}

class entryRoom extends Room{
    public entryRoom() {
        super('E', "Entry", false, false, new Adversary[0]);
    }
}

class treasureRoom extends Room{
    public treasureRoom() {
        super('T', "Treasure", false, true, new Adversary[0]);
    }
}

class treasureTrap extends Room{
    public treasureTrap() {
        super('t', "Treasure*", true, false, new Adversary[]{new mimic()});
    }
}

class encounter extends Room{
    public encounter(){
        super('N',"Encounter",true,false,new Adversary[]{new goblin()});
    }
}

class bossRoom extends Room{
    public bossRoom(){
        super('B',"Boss Room",true,false,new Adversary[]{new manticore()});
    }

}

class emptyRoom extends Room{
    public emptyRoom() {
        super('e', "Empty", false, false, new Adversary[0]);
    }
}

class wall extends Room{
    public wall(){
        super('|', "wall",false, false, new Adversary[0]);
    }
}