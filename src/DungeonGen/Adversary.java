public class Adversary {
    //int intensity;
    public Adversary[] lowLevelMobs = {new goblin(), new orc(), new zombie(), new skeleton()};
    public Adversary[] midLevelMobs = {new medSpider(), new wizard(), new necromancer()};
    public Adversary[] advancedMobs = {new fireElemental(), new shapeshifter(), new voidwalker()};
    public Adversary[] bossMobs = {new lichKing(), new dragon(), new sorcerer(), new golem(), new manticore(), new basilisk()};
    public Adversary[] traps = {new mimic()};
}
//Easy mobs
class goblin extends Adversary{}
class orc extends Adversary{}
class zombie extends Adversary{}
class skeleton extends Adversary{}

//intermediate mobs
class medSpider extends Adversary{}
class wizard extends Adversary{}
class necromancer extends Adversary{}

//Advanced mobs
class fireElemental extends Adversary{}
class shapeshifter extends Adversary{}
class voidwalker extends Adversary{}

//Boss mobs
class lichKing extends Adversary{}
class dragon extends Adversary{}
class sorcerer extends Adversary{}
class golem extends Adversary{}
class manticore extends Adversary{}
class basilisk extends Adversary{}

//Traps
class mimic extends Adversary{};