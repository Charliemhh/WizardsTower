import java.util.ArrayList;

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
}
