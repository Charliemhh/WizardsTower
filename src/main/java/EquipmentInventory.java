import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

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
        for (int i = 0; i < currentlyEquipped.size(); i++) {
            Equipment e = currentlyEquipped.get(i);
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

    public String getWeaponName(){
        for (Equipment e: currentlyEquipped){
            if (e.getBodySlot()== Equipment.BodySlot.MAINHAND){
                return e.getName();
            }
        }
        return "Bare Hand";
    }


    public AttackType attackTypeCheck(int i) {//likely to be used later.
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

    public void presentForExploration(Player player) {
        System.out.println("You are currently wearing: \n");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < this.currentlyEquipped.size(); i++) {
            Equipment equipment = this.currentlyEquipped.get(i);
            if (equipment.getBodySlot() == Equipment.BodySlot.MAINHAND || equipment.getBodySlot() == Equipment.BodySlot.OFFHAND) {
                System.out.println("In your " + equipment.getBodySlot() + " you are holding your " + equipment.getName());
            } else {
                System.out.println("On your " + equipment.getBodySlot() + " you are wearing your " + equipment.getName());
            }
        }
        System.out.println("\nIn your inventory you have: ");
        if (!this.equipmentInventory.isEmpty()) {
            for (int i = 0; i < this.equipmentInventory.size(); i++) {
                Equipment equipment = this.equipmentInventory.get(i);
                System.out.println((i + 1) + ":" + " Slot - " + equipment.getBodySlot() + " " + equipment.getName());
            }
            System.out.println();

            while (true) {
                System.out.println("Select a piece of Equipment to use, or enter -1 to exit");
                try {
                    int option = scanner.nextInt();
                    if (option == -1) {
                        break;
                    }
                    if (option - 1 <= this.equipmentInventory.size() && this.equipmentInventory.get(option - 1) != null && option > 0) {
                        System.out.println("You equip your " + (this.equipmentInventory.get(option - 1).getName()));
                        equipItem(this.equipmentInventory.get(option - 1));
                        break;
                    } else {
                        System.out.println("Please enter a valid option");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid option");
                    scanner.next();
                }

            }
        }
    }

    private void equipItem(Equipment equipment) {
        for (int i = 0; i < currentlyEquipped.size(); i++) {
            if (currentlyEquipped.get(i).getBodySlot() == equipment.getBodySlot()) {
                System.out.println("and place your "+currentlyEquipped.get(i).getName()+" back into your inventory.");
                removeCurrentEquipment(currentlyEquipped.get(i).getEquipmentID());
                setCurrentlyEquipped(equipment);
            }
        }
    }
}
