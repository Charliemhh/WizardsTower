import java.util.HashMap;
import java.util.Map;

public class Player implements Character {

    private int currentLocation;

    private final String name;

    private final int adventureClassID;

    private int currentHP;

    private int maxHP;
    private final HashMap<Integer, Integer> inventory;
    private final HashMap<Integer, Boolean> equipment;
    private final StatBlock statBlock;
    private int XP;

    private boolean isDead;

    private Player(PlayerBuilder builder) {
        this.name = builder.name;
        this.isDead = false;
        this.equipment = builder.equipment;
        this.inventory = builder.inventory;
        this.XP = builder.XP;
        this.currentLocation = 0;
        this.adventureClassID = builder.adventureClassID;
        this.statBlock = builder.statBlock;
        this.maxHP = ((this.statBlock.getPhysique() * 2) + 7);
    }

    @Override
    public int getCurrentHP() {
        return currentHP;
    }

    @Override
    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
        if (currentHP <= 0) {
            setDead(true);
        }
    }

    public void setMaxHP() {
        this.maxHP = ((this.statBlock.getPhysique() * 2) + 7);
    }

    @Override
    public int getMaxHP() {
        return maxHP;
    }

    public String getName() {
        return name;
    }

    public int getXP() {
        return XP;
    }

    @Override
    public StatBlock getStatBlock() {
        return statBlock;
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

    public void addXP(int increaseAmount) {
        this.XP += increaseAmount;
        while (this.XP >= 100) {
            this.XP = (this.XP - 100);
            levelUp();
        }
    }

    private void levelUp() {
        this.statBlock.adventureClassGrowth(this.adventureClassID);
        this.setMaxHP();
    }

    public boolean getIsDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public static class PlayerBuilder {

        private final String name;
        private final int adventureClassID;
        private final HashMap<Integer, Integer> inventory;
        private final HashMap<Integer, Boolean> equipment;
        private final int XP;

        private final StatBlock statBlock;

        public PlayerBuilder(String name, int adventureClassID) {
            this.name = name;
            this.adventureClassID = adventureClassID;
            this.XP = 0;
            this.equipment = new HashMap<>();
            this.inventory = new HashMap<>();
            this.statBlock = new StatBlock();
        }

        public void addDefaultInventoryAndEquipment(int adventureClassID) {
            switch (adventureClassID) {
                case 0://Squire's Assistant
                    this.equipment.put(15, true); //Iron Helmet (Equipped)
                    this.equipment.put(30, true); //Chain-mail shirt (Equipped)
                    this.equipment.put(7, true); //Rusty Sword
                    this.inventory.put(2, 5); //5 Throwing knifes
                    this.inventory.put(0, 2); //2 healing potions
                    //IDs are placeholder also could be refactored later
                    break;
                case 1://Clumsy Thief
                    this.equipment.put(70, true); //Dusty Beret (Equipped)
                    this.equipment.put(9, true); //Leather shirt (Equipped)
                    this.equipment.put(5, true); //Wee Dagger (Equipped)
                    this.inventory.put(2, 5); //5 Throwing knifes
                    this.inventory.put(0, 2); //2 healing potions
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
            this.statBlock.generateClassStats(this.adventureClassID, this);
            return new Player(this);
        }
    }
}
