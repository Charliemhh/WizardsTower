import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class InventoryTests {
    @Test
    void canAddToInventory(){
        Player testPlayer = new Player.PlayerBuilder(
                "test", 0
        ).build();
        assertTrue(testPlayer.getInventory().getPlayerInventory().containsKey(0));
    }
    @Test
    void canRemoveFromInventory(){
        Player testPlayer = new Player.PlayerBuilder(
                "test", 0
        ).build();
        testPlayer.getInventory().removeFromInventory(0);
        assertNull(testPlayer.getInventory().getPlayerInventory().get(0));
    }
    @Test
    void canUseItemThroughInventory(){
        Player testPlayer = new Player.PlayerBuilder(
                "test", 0
        ).build();
        testPlayer.setCurrentHP(2);
        testPlayer.getInventory().useItem(0,testPlayer, mock(Enemy.class));
        assertEquals(12,testPlayer.getCurrentHP());
    }
}
