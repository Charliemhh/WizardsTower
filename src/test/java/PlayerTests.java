import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTests {

    @Test
    void canCreatePlayerUsingBuilder(){
        //Arrange
        Player testPlayer = new Player.PlayerBuilder(
                "Boggins Jones",
                1).build();
        //Act
        //Assert
        assertEquals("Boggins Jones", testPlayer.getName());
        assertTrue(testPlayer.getEquipment().containsKey(70));

    }
    @Test
    void createdPlayerHasCorrectStats(){
        //Arrange
        Player testPlayer = new Player.PlayerBuilder(
                "Harry Tibbs",
                0).build();
        //Act
        //Assert
        assertEquals(10 , testPlayer.getStatBlock().getStrength());
        assertEquals(5, testPlayer.getStatBlock().getBanter());
    }
    @Test
    void physiqueStatIncreasesMaxHP(){
        //Arrange
        Player testPlayer = new Player.PlayerBuilder(
                "Yerry Curtains",
                0).build();
        //Assert
        assertEquals(27, testPlayer.getMaxHP());
        //If the hp formula changes, so should this test
    }

    @Test
    void canAddXPToPlayer(){
        //Arrange
        Player testPlayer = new Player.PlayerBuilder(
                "Ham Fiesta",
                0).build();
        //Act
        testPlayer.addXP(50);
        //Assert
        assertEquals(50, testPlayer.getXP());
    }
    @Test
    void playersStatsIncreaseOnLevelUp(){
        //Arrange
        Player testPlayer = new Player.PlayerBuilder(
                "The Big Oreo", 0
        ).build();
        //Act
        testPlayer.addXP(150);
        //Assert
        assertEquals(13 ,testPlayer.getStatBlock().getPhysique());

    }
}
