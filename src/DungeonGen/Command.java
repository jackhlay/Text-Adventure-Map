public interface Command {
    void execute();
}

class COMM implements Command{
    public void execute(){
        System.out.println("EXAMPLE COMMAND");
    }
}

class InvalidCommand implements Command{
    public void execute(){
        System.out.println("INVALID COMMAND");
    }
}