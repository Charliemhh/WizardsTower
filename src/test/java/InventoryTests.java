import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class InventoryTests {
    @Test
    void canAddToInventory(){
        Player testPlayer = new Player.PlayerBuilder(
                "test", 0
        ).build();
        assertFalse(testPlayer.getInventory().getPlayerInventory().isEmpty());
    }
    @Test
    void canRemoveFromInventory(){
        Player testPlayer = new Player.PlayerBuilder(
                "test", 0
        ).build();
        testPlayer.getInventory().removeFromInventory(0);
        assertTrue(!Objects.equals(testPlayer.getInventory().getPlayerInventory().get(0).getName(), "Healing Potion"));
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
