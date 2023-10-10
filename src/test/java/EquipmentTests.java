import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class EquipmentTests {
    Player testPlayer = new Player.PlayerBuilder(
            "Boggins Jones",
            1).build();

    @Test
    void doesPlayerHaveDefaultEquipment() {
        assertTrue(testPlayer.getEquipment().getCurrentlyEquipped().containsKey(3));
    }

    @Test
    void canPlayerEquipNewEquipment() {
        testPlayer.getEquipment().setCurrentlyEquipped(12, BodySlot.SHOES);
        assertTrue(testPlayer.getEquipment().getCurrentlyEquipped().containsKey(12));
    }

    @Test
    void removingEquipmentMovesItToEquipmentInventory() {
        testPlayer.getEquipment().removeCurrentEquipment(4);
        assertFalse(testPlayer.getEquipment().getCurrentlyEquipped().containsKey(4));
        assertTrue(testPlayer.getEquipment().getEquipmentInventory().containsKey(4));
    }

    @Test
    void effectCheckReturnsCorrectValue() {
        int total = 0;
        total += testPlayer.getEquipment().effectCheck(3);
        total += testPlayer.getEquipment().effectCheck(5);
        assertEquals(3, total);
    }

    @Test
    void canCheckEquipmentName() {
        assertEquals("Wee Dagger", testPlayer.getEquipment().getEquipmentName(5));
    }

    @Test
    void playerCannotEquipTwoOfTheSameBodySlot() {
        testPlayer.getEquipment().setCurrentlyEquipped(0, BodySlot.HEAD);
        assertFalse(testPlayer.getEquipment().getCurrentlyEquipped().containsKey(0));
    }

    @Test
    void playerCanAddEquipmentToInventory() {
        testPlayer.getEquipment().addToEquipmentInventory(11, BodySlot.CHEST);
        assertTrue(testPlayer.getEquipment().getEquipmentInventory().containsKey(11));
    }

    @Test
    void cannotAddDuplicateEquipment() {
        testPlayer.getEquipment().addToEquipmentInventory(3, BodySlot.HEAD);
        assertFalse(testPlayer.getEquipment().getEquipmentInventory().containsKey(3));
    }
}
