public class Player implements Character {

    private int currentLocation;

    private final String name;

    private final int adventureClassID;

    private int currentHP;

    private int playerLevel = 1;

    private int maxHP;
    private final ItemInventory inventory;
    private final EquipmentInventory equipment;
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
        if (currentHP <= this.maxHP) {
            this.currentHP = currentHP;
            if (currentHP <= 0) {
                setDead(true);
            }
        } else {
            this.currentHP = maxHP;
        }

    }

    public void setMaxHP() {
        this.maxHP = ((this.statBlock.getPhysique() * 2) + 7);
    }

    @Override
    public int getMaxHP() {
        return maxHP;
    }

    @Override
    public int[] getAttackDam() {
        int[] weapons = this.getEquipment().getEquippedWeapons();
        int totalDamage = 0;
        AttackType attackType = AttackType.PHYSICAL;
        for (int i : weapons) {
            if (i != 99) {
                totalDamage += this.getEquipment().effectCheck(i);
                attackType = this.getEquipment().attackTypeCheck(i);
                totalDamage += genAttackDam(attackType);
            }
        }
        return new int[]{totalDamage,attackType.getValue(),weapons[0]};
    }

    public int genAttackDam(AttackType type) {
        switch (type) {
            case PHYSICAL:
                return (this.getStatBlock().getStrength()/2);
            case SARCASTIC:
                return (this.getStatBlock().getBanter()/2);
            case BRAINY:
                return (this.getStatBlock().getBraininess()/2);
            case SNEAKY:
                return (this.getStatBlock().getLitheness()/2);
        }
        return 0;
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

    public ItemInventory getInventory() {
        return inventory;
    }

    public EquipmentInventory getEquipment() {
        return equipment;
    }

    public void addXP(int increaseAmount) {
        this.XP += increaseAmount;
        while (this.XP >= 100) {
            this.XP = (this.XP - 100);
            levelUp();
            this.playerLevel += 1;
            System.out.println("Your level has increased! you are now level " + this.playerLevel);
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
        private final ItemInventory inventory;
        private final EquipmentInventory equipment;
        private final int XP;

        private final StatBlock statBlock;

        public PlayerBuilder(String name, int adventureClassID) {
            this.name = name;
            this.adventureClassID = adventureClassID;
            this.XP = 0;
            this.equipment = new EquipmentInventory();
            this.inventory = new ItemInventory();
            this.statBlock = new StatBlock();
        }

        public void addDefaultInventoryAndEquipment(int adventureClassID) {
            switch (adventureClassID) {
                case 0://Squire's Assistant
                    this.equipment.setCurrentlyEquipped(0, Equipment.BodySlot.HEAD);
                    this.equipment.setCurrentlyEquipped(1, Equipment.BodySlot.CHEST);
                    this.equipment.setCurrentlyEquipped(2, Equipment.BodySlot.MAINHAND);
                    this.inventory.addToInventory(0, 2);
                    this.inventory.addToInventory(1, 5);
                    break;
                case 1://Clumsy Thief
                    this.equipment.setCurrentlyEquipped(3, Equipment.BodySlot.HEAD);
                    this.equipment.setCurrentlyEquipped(4, Equipment.BodySlot.CHEST);
                    this.equipment.setCurrentlyEquipped(5, Equipment.BodySlot.MAINHAND);
                    this.inventory.addToInventory(0, 2);
                    this.inventory.addToInventory(1, 5);
                    break;
                case 2://Trainee Wizard
                    this.equipment.setCurrentlyEquipped(6, Equipment.BodySlot.MAINHAND);
                    this.equipment.setCurrentlyEquipped(7, Equipment.BodySlot.HEAD);
                    this.equipment.setCurrentlyEquipped(13, Equipment.BodySlot.CHEST);
                    this.inventory.addToInventory(0,3);
                    break;
                case 3://Mild-Mannered Accountant
                    this.equipment.setCurrentlyEquipped(8, Equipment.BodySlot.CHEST);
                    this.equipment.setCurrentlyEquipped(9, Equipment.BodySlot.MAINHAND);
                    this.equipment.setCurrentlyEquipped(12, Equipment.BodySlot.SHOES);
                    this.inventory.addToInventory(0,3);
                    this.inventory.addToInventory(2,1);
                    break;
                case 4: //Sarcastic so-and-so
                    this.equipment.setCurrentlyEquipped(10, Equipment.BodySlot.MAINHAND);
                    this.equipment.setCurrentlyEquipped(11, Equipment.BodySlot.CHEST);
                    this.inventory.addToInventory(0,3);
                    this.inventory.addToInventory(3,2);
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
