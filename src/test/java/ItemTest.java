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
        Item.useItem(0, 1, testPlayer, mock(Enemy.class));
        assertEquals(12, testPlayer.getCurrentHP());
    }

    @Test
    void itemQuantityIsReducedByUse() {
        Player testPlayer = new Player.PlayerBuilder(
                "test",
                1).build();
        int quantity = 1;
        quantity = Item.useItem(0, quantity, testPlayer, mock(Enemy.class));
        assertEquals(0, quantity);
    }
}