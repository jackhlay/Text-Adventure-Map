import java.util.Map;
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
                if(canLeave(gamestate.currentX, gamestate.currentY, "left")) {
                    gamestate.currentX--;
                    return roomAnnouncement(gamestate.map[gamestate.currentX][gamestate.currentY]);
                }
                return "CAN NOT ESCAPE";
            case "right":
//                System.out.println("GO RIGHT");
                if(canLeave(gamestate.currentX, gamestate.currentY, "right")) {
                    gamestate.currentX++;
                    return roomAnnouncement(gamestate.map[gamestate.currentX][gamestate.currentY]);
                }
                return "CAN NOT ESCAPE";
            case "up":
//                System.out.println("GO UP");
                if(canLeave(gamestate.currentX, gamestate.currentY, "up")) {
                    gamestate.currentY++;
                    return roomAnnouncement(gamestate.map[gamestate.currentX][gamestate.currentY]);
                }
                return "CAN NOT ESCAPE";
            case "down":
//                System.out.println("GO DOWN");
                if(canLeave(gamestate.currentX, gamestate.currentY, "down")) {
                    gamestate.currentY--;
                    return roomAnnouncement(gamestate.map[gamestate.currentX][gamestate.currentY]);
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
            case "yes":
                if(gamestate.map[gamestate.currentX][gamestate.currentY].type.equalsIgnoreCase("Treasure")){
                return "You open the chest";
                }else if(gamestate.map[gamestate.currentX][gamestate.currentY].type.equalsIgnoreCase("Treasure*")){
                    if(!gamestate.map[gamestate.currentX][gamestate.currentY].loot){
                        return "The Chest is empty";
                    }
                return "It was a trap! The Chest was a mimic! Prepare to fight!";
                }
                return"wym?";
            case "no":
                if(gamestate.map[gamestate.currentX][gamestate.currentY].type.equals("Treasure")){
                    return "You ignore the chest";
                }else if(gamestate.map[gamestate.currentX][gamestate.currentY].type.equals("Treasure*")){
                    gamestate.map[gamestate.currentX][gamestate.currentY].Enemies.clear();
                    gamestate.map[gamestate.currentX][gamestate.currentY].loot = false;
                    return "You ignore the chest";
                }
                return"wym?";
            case "q":
            case "quit":
                System.out.println("QUIT THE GAME");
                exit(1);
            case "easy":
            case "ez":
                if (gamestate.difficulty == 0) {
                    gamestate.difficulty = 1;
                    gamestate.dungeonGen(5,5);
                    gamestate.Player.Inventory.add("Sword");
                    gamestate.Player.Inventory.add("Helmet");
                    gamestate.Player.Inventory.add("Chestplate");
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
                    gamestate.Player.Inventory.add("Sword");
                    gamestate.Player.Inventory.add("Helmet");
                    return "Medium Difficulty Selected";
                }
                else{
                    return "Invalid Command!";
                }
            case"hard":
                if (gamestate.difficulty == 0) {
                    gamestate.difficulty = 3;
                    gamestate.dungeonGen(7,7);
                    gamestate.Player.Inventory.add("Sword");
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

    private boolean canLeave(int x, int y, String dir){
        int height = gamestate.map.length;
        int width = gamestate.map[0].length;
        int newx = (dir.equals("left")) ? x-1 : (dir.equals("right")) ? x+1 : x;
        int newy = (dir.equals("down")) ? y-1 : (dir.equals("up")) ? y+1 : y;
        if (gamestate.map[x][y].hostile) {
            return gamestate.map[x][y].Enemies.isEmpty();
        }

        if(newx>=0 && newy>=0 && newx<width && newy<height) {

            if (gamestate.map[newx][newy].symbol == 'X') {
                return false;
            }
        }
        return true;
    }

    private String roomAnnouncement(Room r){
        switch (r.type){
            case "Entry": return "You have entered the Entry room of the Dungeon";
            case "Treasure": return "You cross the threshold, and see a Chest! Do you raid it?";
            case "Treasure*": return "You cross the threshold, and see a Chest, Do you raid it?";
            case "Encounter": return "You have encountered a(n) " + r.Enemies.get(0).type + "! You must face them hand to hand!";
            case "Boss Room": return "You have stumbled into a boss room, A " + r.Enemies.get(0).type + " stands before you, seething, with bad intentions";
            case "Empty": return "You've entered a hallway, nothing in sight";
            default: return "N/A";
        }
    }
}
