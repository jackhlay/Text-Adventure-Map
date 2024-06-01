public class Adversary {
    //int intensity;
    Adversary[] lowLevelMobs = {new goblin(), new orc(), new zombie(), new skeleton()};
    Adversary[] midLevelMobs = {new medSpider(), new wizard(), new necromancer()};
    Adversary[] advancedMobs = {new fireElemental(), new shapeshifter(), new voidwalker()};
    Adversary[] bossMobs = {new lichKing(), new dragon(), new sorcerer(), new golem(), new manticore(), new basilisk()};
    Adversary[] traps = {new mimic()};
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