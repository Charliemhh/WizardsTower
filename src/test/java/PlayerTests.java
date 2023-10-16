import Characters.Player;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTests {

    @Test
    void canCreatePlayerUsingBuilder() {
        //Arrange
        Player testPlayer = new Player.PlayerBuilder(
                "test",
                1).build();
        //Act
        //Assert
        assertEquals("test", testPlayer.getName());

    }

    @Test
    void createdPlayerHasCorrectStats() {
        //Arrange
        Player testPlayer = new Player.PlayerBuilder(
                "test",
                0).build();
        //Act
        //Assert
        assertEquals(10, testPlayer.getStatBlock().getStrength());
        assertEquals(5, testPlayer.getStatBlock().getBanter());
    }

    @Test
    void physiqueStatIncreasesMaxHP() {
        //Arrange
        Player testPlayer = new Player.PlayerBuilder(
                "test",
                0).build();
        //Assert
        assertEquals(27, testPlayer.getMaxHP());
        //If the hp formula changes, so should this test
    }

    @Test
    void canAddXPToPlayer() {
        //Arrange
        Player testPlayer = new Player.PlayerBuilder(
                "test",
                0).build();
        //Act
        testPlayer.addXP(50);
        //Assert
        assertEquals(50, testPlayer.getXP());
    }

    @Test
    void playersStatsIncreaseOnLevelUp() {
        //Arrange
        Player testPlayer = new Player.PlayerBuilder(
                "test", 0
        ).build();
        //Act
        testPlayer.addXP(150);
        //Assert
        assertEquals(13, testPlayer.getStatBlock().getPhysique());

    }

    @Test
    void canLevelUpMultipleTimes() {
        //Arrange
        Player testPlayer = new Player.PlayerBuilder(
                "test", 0
        ).build();
        //Act
        testPlayer.addXP(300);
        //Assert
        assertEquals(19, testPlayer.getStatBlock().getPhysique());
    }

    @Test
    void playerCanDie() {
        Player testPlayer = new Player.PlayerBuilder(
                "test", 0
        ).build();
        assertFalse(Objects.requireNonNull(testPlayer).getIsDead());
        testPlayer.setCurrentHP(0);
        assertTrue(testPlayer.getIsDead());

    }
}
