
public class StatBlock {
    private int strength; //Brute force damage
    private int banter; //Sneaky sarcastic damage
    private int litheness; //Lock picking and dodging
    private int braininess; //Magic Damage
    private int physique; //Affects Health


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

    public void adventureClassGrowth(int adventureClassID) {
        switch (adventureClassID) {
            case 0:
                this.setStrength(this.strength + 3);
                this.setBanter(this.banter + 1);
                this.setLitheness(this.litheness + 2);
                this.setBraininess(this.braininess + 1);
                this.setPhysique(this.physique + 3);
                break;
            case 1:
                this.setStrength(this.strength + 1);
                this.setBanter(this.banter + 3);
                this.setLitheness(this.litheness + 3);
                this.setBraininess(this.braininess + 2);
                this.setPhysique(this.physique + 1);
                break;
            case 2://Trainee Wizard
                break;
            case 3://Mild-Mannered Accountant
                break;
            case 4: //Sarcastic so-and-so
                break;
        }
    }

    public void generateStats(int adventureClassID, Player.PlayerBuilder playerBuilder){
        switch (adventureClassID){
            case 0://Squire's Assistant
                this.setStrength(10);
                this.setBanter(5);
                this.setLitheness(6);
                this.setBraininess(3);
                this.setPhysique(10);
                break;
            case 1: //Clumsy Thief
                this.setStrength(7);
                this.setBanter(11);
                this.setLitheness(10);
                this.setBraininess(5);
                this.setPhysique(4);
                break;
            case 2://Trainee Wizard
                break;
            case 3://Mild-Mannered Accountant
                break;
            case 4://Sarcastic so-and-so
                break;
        }
    }
}
