import java.util.ArrayList;

public class EquipmentInventory {

    private final ArrayList<Equipment> currentlyEquipped;

    private final ArrayList<Equipment> equipmentInventory;

    public EquipmentInventory() {
        this.currentlyEquipped = new ArrayList<>();
        this.equipmentInventory = new ArrayList<>();
    }

    public void setCurrentlyEquipped(Equipment equipment) {
        boolean alreadyEquipped = false;
        if (this.currentlyEquipped.contains(equipment)) {
            System.out.println("That slot is already occupied!");
        } else {
            if (this.currentlyEquipped.size() == 0) {
                this.currentlyEquipped.add(equipment);
            } else {
                for (int i = 0; i < this.currentlyEquipped.size(); i++) {
                    if ((currentlyEquipped.get(i).getBodySlot() == equipment.getBodySlot())) {
                        alreadyEquipped = true;
                        break;
                    }
                }
                if (!alreadyEquipped) {
                    this.currentlyEquipped.add(equipment);
                    if (equipmentInventory.contains(equipment)) {
                        this.equipmentInventory.remove(equipment);
                    }
                }
            }
        }
    }

    public ArrayList<Equipment> getCurrentlyEquipped() {
        return currentlyEquipped;
    }

    public ArrayList<Equipment> getEquipmentInventory() {
        return equipmentInventory;
    }

    public void removeCurrentEquipment(int equipmentID) {
        for (Equipment e : currentlyEquipped) {
            if (e.getEquipmentID() == equipmentID) {
                currentlyEquipped.remove(e);
                if (!equipmentInventory.contains(e)) {
                    equipmentInventory.add(e);
                }
            }
        }
    }

    public void addToEquipmentInventory(Equipment equipment) {
        if (!this.currentlyEquipped.contains(findEquipment(equipment, currentlyEquipped))
                && !this.equipmentInventory.contains(findEquipment(equipment, equipmentInventory))) {
            this.equipmentInventory.add(equipment);
        } else {
            System.out.println("You already have this piece of equipment!");
        }
    }

    public Equipment findEquipment(Equipment equipment, ArrayList<Equipment> equipmentInventory) {
        for (Equipment e : equipmentInventory) {
            if (e.getEquipmentID() == equipment.getEquipmentID()) {
                return e;
            }
        }
        return null;
    }

    public void getEquippedItems() {
        for (Equipment e : this.currentlyEquipped) {
            System.out.println("Slot: " + e.getBodySlot() + "  Name: " + e.getName());
        }
    }


    public static String getEquipmentName(int id) {
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
            case 13:
                return "Robe with holes in";
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
            case 13:
                return 0;
        }
        return 0;
    }

    public AttackType attackTypeCheck(int i) {
        switch (i) {
            case 2:
            case 9:
                return AttackType.PHYSICAL;
            case 5:
                return AttackType.SNEAKY;
            case 6:
                return AttackType.BRAINY;
            case 10:
                return AttackType.SARCASTIC;

        }
        return null;
    }
}
