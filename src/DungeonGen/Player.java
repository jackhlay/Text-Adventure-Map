public class Player {
    public String Name;
    public int[] Location = {999,999};
    public int Level;
    public int Strength;
    public int Dexterity;
    public int Gold;
    public int armorLevel;

    public String[] Inventory;

    public Player(){
        this.Name = "";
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

//    public static void main(String[] args) {
//        int ex = 0x0;
//        System.out.println(ex);
//        System.out.println(~ex & 0b11);
        //bit flipping test logic
//    }

}
