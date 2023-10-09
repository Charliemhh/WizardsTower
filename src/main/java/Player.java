import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player {

    private int currentLocation;

    private final String name;

    private int adventureClassID;

    private int currentHP;

    private int maxHP;
    private HashMap<Integer, Integer> inventory;

    private HashMap<Integer, Boolean> equipment;

    private int XP;

    private Player(PlayerBuilder builder) {
        this.name = builder.name;
        this.equipment = builder.equipment;
        this.inventory = builder.inventory;
        this.XP = builder.XP;
        this.currentLocation = 0;
        this.adventureClassID = builder.adventureClassID;
    }

    public String getName() {
        return name;
    }

    public int getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(int currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Map<Integer, Integer> getInventory() {
        return inventory;
    }

    public Map<Integer, Boolean> getEquipment() {
        return equipment;
    }
    public static class PlayerBuilder {

        private final String name;
        private final int adventureClassID;
        private int currentHP;
        private int maxHP;
        private HashMap<Integer, Integer> inventory;
        private HashMap<Integer, Boolean> equipment;
        private final int XP;

        public PlayerBuilder(String name, int adventureClassID) {
            this.name = name;
            this.adventureClassID = adventureClassID;
            this.XP = 0;
            this.equipment = new HashMap<>();
            this.inventory = new HashMap<>();
        }

        public void addDefaultInventoryAndEquipment(int adventureClassID){
            switch (adventureClassID){
                case 0://Squire's Assistant
                    this.equipment.put(15,true); //Iron Helmet (Equipped)
                    this.equipment.put(30,true); //Chain-mail shirt (Equipped)
                    this.equipment.put(7,true); //Rusty Sword
                    this.inventory.put(2,5); //5 Throwing knifes
                    this.inventory.put(0,2); //2 healing potions
                    //IDs are placeholder
                    break;
                case 1://Clumsy Thief
                    this.equipment.put(70,true); //Dusty Beret (Equipped)
                    this.equipment.put(9,true); //Leather shirt (Equipped)
                    this.equipment.put(5,true); //Wee Dagger (Equipped)
                    this.inventory.put(2,5); //5 Throwing knifes
                    this.inventory.put(0,2); //2 healing potions
                    break;
                case 2://Trainee Wizard
                    break;
                case 3://Mild-Mannered Accountant
                    break;
                case 4: //Sarcastic so-and-so
                    break;
            }
        }

        public Player build() {
            this.addDefaultInventoryAndEquipment(this.adventureClassID);
            return new Player(this);
        }
    }
}
