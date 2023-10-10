import java.util.HashMap;
import java.util.Map;

public class Player implements Character {

    private int currentLocation;

    private final String name;

    private final int adventureClassID;

    private int currentHP;

    private int maxHP;
    private final Inventory inventory;
    private final Equipment equipment;
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
        this.currentHP = this.maxHP;
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

    public Inventory getInventory() {
        return inventory;
    }

    public Equipment getEquipment() {
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
        private final Inventory inventory;
        private final Equipment equipment;
        private final int XP;

        private final StatBlock statBlock;

        public PlayerBuilder(String name, int adventureClassID) {
            this.name = name;
            this.adventureClassID = adventureClassID;
            this.XP = 0;
            this.equipment = new Equipment();
            this.inventory = new Inventory();
            this.statBlock = new StatBlock();
        }

        public void addDefaultInventoryAndEquipment(int adventureClassID) {
            switch (adventureClassID) {
                case 0://Squire's Assistant
                    this.equipment.setCurrentlyEquipped(0,BodySlot.HEAD);
                    this.equipment.setCurrentlyEquipped(1,BodySlot.CHEST);
                    this.equipment.setCurrentlyEquipped(2,BodySlot.MAINHAND);
                    this.inventory.addToInventory(1, 5);
                    this.inventory.addToInventory(0, 2);
                    break;
                case 1://Clumsy Thief
                    this.equipment.setCurrentlyEquipped(3,BodySlot.HEAD);
                    this.equipment.setCurrentlyEquipped(4,BodySlot.CHEST);
                    this.equipment.setCurrentlyEquipped(5,BodySlot.MAINHAND);
                    this.inventory.addToInventory(1, 5);
                    this.inventory.addToInventory(0, 2);
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
