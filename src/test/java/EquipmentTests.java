import Characters.Player;
import Items.Equipment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EquipmentTests {


    @Test
    void doesPlayerHaveDefaultEquipment() {
        Player testPlayer = new Player.PlayerBuilder(
                "test",
                1).build();
        Equipment equipment = Equipment.EquipmentGen(3);
        assertNotNull(testPlayer.getEquipment().findEquipment(equipment, testPlayer.getEquipment().getCurrentlyEquipped()));
    }

    @Test
    void canPlayerEquipNewEquipment() {
        Player testPlayer = new Player.PlayerBuilder(
                "test",
                1).build();
        Equipment equipment = Equipment.EquipmentGen(12);
        testPlayer.getEquipment().setCurrentlyEquipped(equipment);
        assertTrue(testPlayer.getEquipment().getCurrentlyEquipped().contains(equipment));
    }

    @Test
    void removingEquipmentMovesItToEquipmentInventory() {
        Player testPlayer = new Player.PlayerBuilder(
                "test",
                1).build();
        Equipment equipment = Equipment.EquipmentGen(4);
        testPlayer.getEquipment().removeCurrentEquipment(4);
        assertNull(testPlayer.getEquipment().findEquipment(equipment, testPlayer.getEquipment().getCurrentlyEquipped()));
        assertNotNull(testPlayer.getEquipment().findEquipment(equipment, testPlayer.getEquipment().getEquipmentInventory()));
    }


    @Test
    void playerCannotEquipTwoOfTheSameBodySlot() {
        Player testPlayer = new Player.PlayerBuilder(
                "test",
                1).build();
        Equipment sameSlot = Equipment.EquipmentGen(0);
        testPlayer.getEquipment().setCurrentlyEquipped(sameSlot);
        assertFalse(testPlayer.getEquipment().getCurrentlyEquipped().contains(sameSlot));
    }

    @Test
    void playerCanAddEquipmentToInventory() {
        Player testPlayer = new Player.PlayerBuilder(
                "test",
                1).build();
        Equipment canAdd = Equipment.EquipmentGen(11);
        testPlayer.getEquipment().addToEquipmentInventory(canAdd);
        assertTrue(testPlayer.getEquipment().getEquipmentInventory().contains(canAdd));
    }

    @Test
    void cannotAddDuplicateEquipment() {
        Player testPlayer = new Player.PlayerBuilder(
                "test",
                1).build();
        Equipment cannotAdd = Equipment.EquipmentGen(3);
        testPlayer.getEquipment().addToEquipmentInventory(cannotAdd);
        assertFalse(testPlayer.getEquipment().getEquipmentInventory().contains(cannotAdd));
    }
}
