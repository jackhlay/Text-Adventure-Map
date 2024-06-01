public class Room {
    char symbol;
    String type;
    boolean hostile;
    boolean loot;
    Adversary[] Enemies;
}

class TreasureRoom extends Room{
    char symbol = 'T';
    String Type = "Treasure";
    boolean hostile;
    boolean loot = true;
    Adversary[] Enemies = {};
}

class TreasureTrap extends Room{
    char symbol = 't';
    String type = "Treasure*";
    boolean hostile = true;
    boolean loot = false;
    Adversary[] Enemies = {new Mimic()};
}

class emptyRoom extends Room{
    char symbol = ' ';
    String type = "Empty";
    boolean hostile = false;
    boolean loot = false;
    Adversary[] Enemies = {};
}