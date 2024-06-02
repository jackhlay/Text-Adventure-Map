public class commandHandler {
    Gamestate gamestate;
    public commandHandler(Gamestate gs){
        this.gamestate = gs;
    }

    public void handleCommand(String command){//Eventually turn this to return string to put into the console.
        switch (command.toLowerCase()){
            case "left":
                System.out.println("GO LEFT");
                //new COMM().execute();
            case "right":
                System.out.println("GO RIGHT");
                //new COMM().execute();
            case "up":
                System.out.println("GO UP");
                //new COMM().execute();
            case "down":
                System.out.println("GO DOWN");
                //new COMM().execute();
            case "attack":
                System.out.println("ATTACK THE ENEMY");
                //new COMM().execute();
            case "inv":
            case "i":
            case "inventory":
                System.out.println("SHOW ME THE INVENTORY");
                //new COMM().execute();
            case "q":
            case "quit":
                System.out.println("QUIT THE GAME");
                //new COMM().execute();;
            case "easy":
            case "ez":
                if (gamestate.difficulty == 0) {
                    gamestate.difficulty = 1;
                    System.out.println("EASY DIFFICULTY");
                    //new COMM().execute();
                }
                else{
                    //new InvalidCommand().execute();
                }
            case "med":
            case "medium":
                if (gamestate.difficulty == 0) {
                    gamestate.difficulty = 2;
                    System.out.println("MEDIUM DIFFICULTY");
                    //new COMM().execute();
                }
                else{
                    //new InvalidCommand().execute();
                }
            case"hard":
                if (gamestate.difficulty == 0) {
                    gamestate.difficulty = 3;
                System.out.println("HARD");
                    //new COMM().execute();
                }
                else{
                    //return "Invalid Command!"
                    //new InvalidCommand().execute();
                }

        }
    }
}
