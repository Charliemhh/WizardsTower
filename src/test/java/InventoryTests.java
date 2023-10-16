import Characters.Enemy;
import Characters.Player;
import org.junit.jupiter.api.Test;

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
        assertNotEquals("Healing Potion", testPlayer.getInventory().getPlayerInventory().get(0).getName());
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
