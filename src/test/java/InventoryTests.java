import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class InventoryTests {
    @Test
    void canAddToInventory(){
        Player testPlayer = new Player.PlayerBuilder(
                "Doritos Slims", 0
        ).build();
        assertTrue(testPlayer.getInventory().getPlayerInventory().containsKey(0));
    }
    @Test
    void canSeeInventory(){
        Player testPlayer = new Player.PlayerBuilder(
                "Doritos Slims", 0
        ).build();
//        assertEquals("Health Potion\n Throwing Knife",testPlayer.getInventory().seeInventory());
    }
}
