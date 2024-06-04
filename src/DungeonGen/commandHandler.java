import static java.lang.System.exit;

public class commandHandler {
    Gamestate gamestate;
    public commandHandler(Gamestate gs){
        this.gamestate = gs;
    }

    public String handleCommand(String command){//Eventually turn this to return string to put into the console.
        switch (command.toLowerCase()){
            case "left":
                System.out.println("GO LEFT");
                return "GO LEFT";
            case "right":
                System.out.println("GO RIGHT");
                return "GO RIGHT";
            case "up":
                System.out.println("GO UP");
                return "GO UP";
            case "down":
                System.out.println("GO DOWN");
                return "GO DOWN";
            case "attack":
                System.out.println("ATTACK THE ENEMY");
                return "ATTACK";
            case "inv":
            case "i":
            case "inventory":
                System.out.println("SHOW ME THE INVENTORY");
                return "INVENTORY";
            case "q":
            case "quit":
                System.out.println("QUIT THE GAME");
                exit(1);
            case "easy":
            case "ez":
                if (gamestate.difficulty == 0) {
                    gamestate.difficulty = 1;
                    gamestate.dungeonGen(5,5);
                    return "Easy Difficulty Selected";
                }
                else{
                    return "Invalid Command!";                }
            case "med":
            case "medium":
                if (gamestate.difficulty == 0) {
                    gamestate.difficulty = 2;
                    gamestate.dungeonGen(7,7);
                    return "Medium Difficulty Selected";
                }
                else{
                    return "Invalid Command!";
                }
            case"hard":
                if (gamestate.difficulty == 0) {
                    gamestate.difficulty = 3;
                    gamestate.dungeonGen(7,7);
                    return "Hard Difficulty Selected";
                }
                else{
                    return "Invalid Command!";
                }
            default:
                return "Invalid Command!";
        }
    }
}
