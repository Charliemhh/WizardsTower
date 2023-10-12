import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ItemTest {
    @Test
    void potionCanRestoreHP() {
        Player testPlayer = new Player.PlayerBuilder(
                "test",
                1).build();
        testPlayer.setCurrentHP(2);
        testPlayer.getInventory().useItem(0,testPlayer,mock(Enemy.class));
        assertEquals(12, testPlayer.getCurrentHP());
    }

    @Test
    void itemQuantityIsReducedByUse() {
        Player testPlayer = new Player.PlayerBuilder(
                "test",
                1).build();
        testPlayer.getInventory().useItem(0,testPlayer,mock(Enemy.class));
        assertEquals(1, testPlayer.getInventory().findItem(0).getQuantity());
    }
    @Test
    void itemPickUpWorks(){
        Player testPlayer = new Player.PlayerBuilder(
                "test",
                1).build();
        testPlayer.getInventory().pickUp(3,5);
        assertTrue(testPlayer.getInventory().findItem(3).getQuantity()==5);
    }
}