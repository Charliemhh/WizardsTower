import java.util.HashMap;

public class Equipment {

    private final HashMap<Integer, BodySlot> currentlyEquipped;

    private final HashMap<Integer, BodySlot> equipmentInventory;

    public Equipment() {
        this.currentlyEquipped = new HashMap<>();
        this.equipmentInventory = new HashMap<>();
    }

    public void setCurrentlyEquipped(int equipmentID, BodySlot bodySlot) {
        if (!this.currentlyEquipped.containsValue(bodySlot)) {
            this.currentlyEquipped.put(equipmentID, bodySlot);
            this.equipmentInventory.remove(equipmentID);
        } else {
            System.out.println("That slot is already occupied!");
        }
    }

    public HashMap<Integer, BodySlot> getCurrentlyEquipped() {
        return currentlyEquipped;
    }

    public HashMap<Integer, BodySlot> getEquipmentInventory() {
        return equipmentInventory;
    }

    public void removeCurrentEquipment(int equipmentID) {
        this.equipmentInventory.put(equipmentID, this.currentlyEquipped.remove(equipmentID));
    }

    public void addToEquipmentInventory(int equipmentID, BodySlot bodySlot) {
        if (!this.currentlyEquipped.containsKey(equipmentID) && !this.equipmentInventory.containsKey(equipmentID)) {
            this.equipmentInventory.put(equipmentID, bodySlot);
        } else {
            System.out.println("You already have this piece of equipment!");
        }
    }
    public void getEquippedItems(){
        for (int id : this.currentlyEquipped.keySet()){
            System.out.println("Slot: "+this.currentlyEquipped.get(id) + "  Name: "+getEquipmentName(id));
        }
    }

    public String getEquipmentName(int id) {
        switch (id) {
            case 0:
                return "Iron Helmet";
            case 1:
                return "Chain-Mail Shirt";
            case 2:
                return "Rusty Sword";
            case 3:
                return "Dusty Beret";
            case 4:
                return "Leather Shirt";
            case 5:
                return "Wee Dagger";
            case 6:
                return "Hand-me-Down Staff";
            case 7:
                return "Off-brand Wizard's hat";
            case 8:
                return "Normal Suit";
            case 9:
                return "Briefcase";
            case 10:
                return "Sarcasm Bat";
            case 11:
                return "Lord of the Things(tm) T-shirt";
            case 12:
                return "Posh shoes";
        }
        return null;
    }

    public int effectCheck(int equipmentID) {
        //The logic of where the numerical effects go will be handled in combat handler.
        //As well as loop through current equipment
        switch (equipmentID) {
            case 0: //Iron Helmet
                return -1;
            case 1: //Chain-Mail Shirt
                return -2;
            case 2: //Rusty Sword
                return +0;
            case 3://Dusty Beret
                return +1;
            case 4://Leather Shirt
                return -1;
            case 5://Wee Dagger
                return +2;
            case 6://Hand-me-Down Staff
                return +1;
            case 7://Off-brand Wizard's hat
                return +1;
            case 8://Normal Suit
                return 0;
            case 9://Briefcase
                return +1;
            case 10://Sarcasm Bat
                return +2;
            case 11: //Lord of the Things T-shirt
                return +1;
            case 12: //Posh shoes
                return 0;
        }
        return 0;
    }
}
