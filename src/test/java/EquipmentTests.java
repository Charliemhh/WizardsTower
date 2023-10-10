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
    void canPlayerEquipNewEquipment(){
        testPlayer.getEquipment().setCurrentlyEquipped(12,BodySlot.SHOES);
        assertTrue(testPlayer.getEquipment().getCurrentlyEquipped().containsKey(12));
    }
}
