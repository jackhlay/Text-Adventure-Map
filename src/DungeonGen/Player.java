public class Player {
    public int Level;
    public int Strength;
    public int Dexterity;
    public int Gold;
    public int armorLevel;

    public String[] Inventory;

    public Player(){
        this.Level = 0;
        this.Strength = 0;
        this.Dexterity = 0;
        this.Gold=0;
        this.armorLevel=0;
        this.Inventory = new String[0];
    }

    public void addGold(int n){
        this.Gold += n;
    }

}
