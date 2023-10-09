
public class StatBlock {
    private int strength; //Brute force damage
    private int banter; //Sneaky sarcastic damage
    private int litheness; //Lock picking and dodging
    private int brainy; //Magic Damage
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

    public int getBrainy() {
        return brainy;
    }

    public void setBrainy(int brainy) {
        this.brainy = brainy;
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
                this.setBrainy(this.brainy + 1);
                this.setBanter(this.banter + 1);
                this.setPhysique(this.physique + 3);
                this.setLitheness(this.litheness + 2);
                this.setStrength(this.strength + 3);
                break;
            case 1:
                this.setBrainy(this.brainy + 2);
                this.setBanter(this.banter + 3);
                this.setPhysique(this.physique + 1);
                this.setLitheness(this.litheness + 3);
                this.setStrength(this.strength + 1);
                break;
            case 2://Trainee Wizard
                break;
            case 3://Mild-Mannered Accountant
                break;
            case 4: //Sarcastic so-and-so
                break;
        }
    }
}
