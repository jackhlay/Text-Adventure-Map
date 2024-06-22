public class Adversary {
    String type;
    int baseHealth = 7;
    double health;
    int tier;
    int attackDamage;

    public Adversary(String t, int tier){
        this.type=t;
        this.tier=tier;
    }
}
//Easy mobs (tier 1)
class goblin extends Adversary{
    public goblin(){
        super("Goblin",1);
        this.health = baseHealth*this.tier*1;
        this.attackDamage = 4;
    }
}
class orc extends Adversary{
    public orc(){
        super("Orc",1);
        this.health = baseHealth*this.tier*1;
        this.attackDamage = 4;
    }
}
class zombie extends Adversary{
    public zombie(){
        super("Zombie",1);
        this.health = baseHealth*this.tier*1;
        this.attackDamage = 3;

    }
}
class skeleton extends Adversary{
    public skeleton(){
        super("Skeleton",1);
        this.health = baseHealth*this.tier*1;
        this.attackDamage = 7;

    }
}

//intermediate mobs (tier 2)
class medSpider extends Adversary{
    public medSpider(){
        super("Spider",2);
        this.health = baseHealth*this.tier*1.5;
        this.attackDamage = 4;

    }
}
class wizard extends Adversary{
    public wizard(){
        super("Wizard",2);
        this.health = baseHealth*this.tier*1.5;
        this.attackDamage = 7;
    }
}
class necromancer extends Adversary{
    public necromancer(){
        super("Necromancer",2);
        this.health = baseHealth*this.tier*1.5;
        this.attackDamage = 6;
    }
}

//Advanced mobs (tier 3)
class fireElemental extends Adversary{
    public fireElemental(){
        super("Fire Elemental",3);
        this.health = baseHealth*this.tier*2;
        this.attackDamage = 9;

    }
}
class shapeshifter extends Adversary{
    public shapeshifter(){
        super("Shape Shifter",3);
        this.health = baseHealth*this.tier*2;
        this.attackDamage = 7;

    }
}
class voidwalker extends Adversary{
    public voidwalker(){
        super("Void Walker",3);
        this.health = baseHealth*this.tier*2;
        this.attackDamage = 10;
    }
}
class sorcerer extends Adversary{
    public sorcerer(){
        super("Sorcerer",3);
        this.health = baseHealth*this.tier*2;
        this.attackDamage = 11;
    }
}

//Boss mobs (tier 5)
class lichKing extends Adversary{
    public lichKing(){
        super("Lich",5);
        this.health = baseHealth*this.tier*2;
        this.attackDamage = 13;
    }
}
class dragon extends Adversary{
    public dragon(){
        super("Dragon",5);
        this.health = baseHealth*this.tier*2;
        this.attackDamage = 13;
    }
}
class golem extends Adversary{
    public golem(){
        super("Golem",4);
        this.health = baseHealth*this.tier*2;
        this.attackDamage = 9;
    }

}
class manticore extends Adversary{
    public manticore(){
        super("Manticore",4);
        this.health = baseHealth*this.tier*2;
        this.attackDamage = 11;
    }
}
class basilisk extends Adversary{
    public basilisk(){
        super("Basilisk",6);
        this.health = baseHealth*this.tier*2;
        this.attackDamage = 12;

    }
}

//Traps
class mimic extends Adversary{
    public mimic(){
        super("Mimic",2);
        this.health = 12;
        this.attackDamage = 13;
    }
}