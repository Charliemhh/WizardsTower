
public class StatBlock {
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
        switch (adventureClassID) {
            case 0://Squire's Assistant
                int[] stats0 = {10, 5, 6, 3, 10};
                this.setStats(stats0);
                break;
            case 1: //Clumsy Thief
                int[] stats1 = {7, 11, 10, 5, 4};
                this.setStats(stats1);
                break;
            case 2://Trainee Wizard
                int[] stats2 = {4, 6, 6, 12, 5};
                this.setStats(stats2);
                break;
            case 3://Mild-Mannered Accountant
                int[] stats3 = {8, 2, 6, 9, 8};
                this.setStats(stats3);
                break;
            case 4://Sarcastic so-and-so
                int[] stats4 = {2, 10, 10, 6, 8};
                this.setStats(stats4);
                break;
        }
    }

    public void adventureClassGrowth(int adventureClassID) {
        switch (adventureClassID) {
            case 0: //Squire's Assistant
                int[] growthRate0 = {3, 1, 2, 1, 3};
                statGrowth(growthRate0);
                break;
            case 1: //Clumsy Thief
                int[] growthRate1 = {1, 2, 3, 2, 2};
                statGrowth(growthRate1);
                break;
            case 2://Trainee Wizard
                int[] growthRate2 = {1, 2, 1, 3, 2};
                statGrowth(growthRate2);
                break;
            case 3://Mild-Mannered Accountant
                int[] growthRate3 = {2, 1, 2, 1, 2};
                statGrowth(growthRate3);
                break;
            case 4: //Sarcastic so-and-so
                int[] growthRate4 = {1, 3, 2, 1, 2};
                statGrowth(growthRate4);
                break;
        }
    }

    public void generateEnemyStats(int enemyTypeID) {
        switch (enemyTypeID) {
            case 0://Bag O'Bones
                int[] statsBone = {6, 2, 2, 1, 4};
                this.setStats(statsBone);
                break;
            case 1://Were-chicken
                int[] statsWere = {7, 1, 6, 2, 7};
                this.setStats(statsWere);
                break;
            case 2: //Smart-Mouthed Slime
                int[] statsSlime = {3, 7, 6, 5, 8};
                this.setStats(statsSlime);
                break;
            case 3: //Under-Cooked Calamari
                int[] statsCalamari = {4, 2, 2, 10, 6};
                this.setStats(statsCalamari);
                break;
            //Enemy types and stats not final.
        }
    }
}
