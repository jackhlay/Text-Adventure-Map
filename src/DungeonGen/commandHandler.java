public class commandHandler {
    public commandHandler(){};

    public Command handleCommand(String command){
        switch (command.toLowerCase()){
            case "left":
                System.out.println("GO LEFT");
                return new COMM();
            case "right":
                System.out.println("GO RIGHT");
                return new COMM();
            case "up":
                System.out.println("GO UP");
                return new COMM();
            case "down":
                System.out.println("GO DOWN");
                return new COMM();
            case "attack":
                System.out.println("ATTACK THE ENEMY");
                return new COMM();
            case "inv":
            case "i":
            case "inventory":
                System.out.println("SHOW ME THE INVENTORY");
                return new COMM();
            case "q":
            case "quit":
                System.out.println("QUIT THE GAME");
                return new COMM();

        }
        return new InvalidCommand();
    }
}
