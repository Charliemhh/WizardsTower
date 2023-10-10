import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    @Test
    void potionCanRestoreHP(){
        Player testPlayer = new Player.PlayerBuilder(
                "Bodge Jones",
                1).build();
        testPlayer.setCurrentHP(2);
        Item.useItem(0,1,testPlayer);
        assertEquals(12,testPlayer.getCurrentHP());
    }
    @Test
    void itemQuantityIsReducedByUse(){
        Player testPlayer = new Player.PlayerBuilder(
                "Bodge Jones",
                1).build();
        int quantity = 1;
        quantity = Item.useItem(0,quantity,testPlayer);
        assertEquals(0,quantity);
    }
}