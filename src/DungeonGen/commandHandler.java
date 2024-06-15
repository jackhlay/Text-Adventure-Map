import java.util.Random;

import static java.lang.System.exit;

public class commandHandler {
    Gamestate gamestate;
    public commandHandler(Gamestate gs){
        this.gamestate = gs;
    }

    public String handleCommand(String command){//Eventually turn this to return string to put into the console.
        switch (command.toLowerCase()){
            case "left":
//                System.out.println("GO LEFT");
                if(canLeave(gamestate.currentX-1, gamestate.currentY)) {
                    gamestate.currentX--;
                    return "GOING LEFT";
                }
                return "CAN NOT ESCAPE";
            case "right":
//                System.out.println("GO RIGHT");
                if(canLeave(gamestate.currentX+1, gamestate.currentY)) {
                    gamestate.currentX++;
                    return "GOING RIGHT";
                }
                return "CAN NOT ESCAPE";
            case "up":
//                System.out.println("GO UP");
                if(canLeave(gamestate.currentX, gamestate.currentY+1)) {
                    gamestate.currentY++;
                    return "GOING UP";
                }
                return "CAN NOT ESCAPE";
            case "down":
//                System.out.println("GO DOWN");
                if(canLeave(gamestate.currentX, gamestate.currentY-1)) {
                    gamestate.currentY--;
                    return "GOING down";
                }
                return "CAN NOT ESCAPE";
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
                    System.out.println();
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
            case "map":
                if(gamestate.map == null){
                    return "Choose a difficulty first";
                }else {
                    return "Bringing up map";
                }
            default:
                return "Invalid Command!";
        }
    }

    private boolean canLeave(int x, int y){
        int height = gamestate.map.length;
        int width = gamestate.map[0].length;
        if(x>=0 && y>=0 && x<width && y<height) {
            if (gamestate.map[x][y].symbol == 'X') {
                return false;
            }
            if (gamestate.map[x][y].hostile) {
                return gamestate.map[gamestate.currentX][gamestate.currentY].Enemies.isEmpty();
            }
        }
        return true;
    }
}
