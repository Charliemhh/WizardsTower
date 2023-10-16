package Characters;

public class StatBlock implements Comparable<StatBlock> {
    private int strength; //Brute force damage
    private int banter; //Sneaky sarcastic damage
    private int litheness; //Lock picking and dodging
    private int braininess; //Magic Damage
    private int physique; //Affects Health

    private void setStats(int[] stats) {
        this.strength = stats[0];
        this.banter = stats[1];
        this.litheness = stats[2];
        this.braininess = stats[3];
        this.physique = stats[4];
    }

    private void statGrowth(int[] growth) {
        this.strength += growth[0];
        this.banter += growth[1];
        this.litheness += growth[2];
        this.braininess += growth[3];
        this.physique += growth[4];
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getBanter() {
        return banter;
    }

    public void setBanter(int banter) {
        this.banter = banter;
    }

    public int getLitheness() {
        return litheness;
    }

    public void setLitheness(int litheness) {
        this.litheness = litheness;
    }

    public int getBraininess() {
        return braininess;
    }

    public void setBraininess(int braininess) {
        this.braininess = braininess;
    }

    public int getPhysique() {
        return physique;
    }

    public void setPhysique(int physique) {
        this.physique = physique;
    }

    public void generateClassStats(int adventureClassID, Player.PlayerBuilder playerBuilder) {
        int[] stats;
        switch (adventureClassID) {
            case 0://Squire's Assistant
                stats = new int[]{10, 5, 6, 3, 10};
                this.setStats(stats);
                break;
            case 1: //Clumsy Thief
                stats = new int[]{7, 11, 10, 5, 4};
                this.setStats(stats);
                break;
            case 2://Trainee Wizard
                stats = new int[]{4, 6, 6, 12, 5};
                this.setStats(stats);
                break;
            case 3://Mild-Mannered Accountant
                stats = new int[]{8, 2, 6, 9, 8};
                this.setStats(stats);
                break;
            case 4://Sarcastic so-and-so
                stats = new int[]{2, 10, 10, 6, 8};
                this.setStats(stats);
                break;
        }
    }

    public void adventureClassGrowth(int adventureClassID) {
        int[] growthRate;
        switch (adventureClassID) {
            case 0: //Squire's Assistant
                growthRate = new int[]{3, 1, 2, 1, 3};
                statGrowth(growthRate);
                break;
            case 1: //Clumsy Thief
                growthRate = new int[]{1, 2, 3, 2, 2};
                statGrowth(growthRate);
                break;
            case 2://Trainee Wizard
                growthRate = new int[]{1, 2, 1, 3, 2};
                statGrowth(growthRate);
                break;
            case 3://Mild-Mannered Accountant
                growthRate = new int[]{2, 1, 2, 1, 2};
                statGrowth(growthRate);
                break;
            case 4: //Sarcastic so-and-so
                growthRate = new int[]{1, 3, 2, 1, 2};
                statGrowth(growthRate);
                break;
        }
    }

    public void generateEnemyStats(int enemyTypeID) {
        int[] monsterStats;
        switch (enemyTypeID) {
            case 0://Bag O'Bones
                monsterStats = new int[]{6, 2, 2, 1, 4};
                this.setStats(monsterStats);
                break;
            case 1://Were-chicken
                monsterStats = new int[]{7, 1, 6, 2, 7};
                this.setStats(monsterStats);
                break;
            case 2://Smart-Mouthed Slime
                monsterStats = new int[]{3, 7, 6, 5, 8};
                this.setStats(monsterStats);
                break;
            case 3://Under-Cooked Calamari
                monsterStats = new int[]{4, 2, 2, 10, 6};
                this.setStats(monsterStats);
                break;
            case 4://Shank-wielding Pigeon
                monsterStats = new int[]{3, 1, 4, 1, 5};
                this.setStats(monsterStats);
                break;
            case 5://Pathetic Kobold
                monsterStats = new int[]{4, 2, 4, 2, 4};
                this.setStats(monsterStats);
                break;
        }
    }

    @Override
    public int compareTo(StatBlock o) {
        return Integer.compare(o.getLitheness(), this.getLitheness());
    }
}
