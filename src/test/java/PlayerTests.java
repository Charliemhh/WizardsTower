import org.junit.jupiter.api.Test;

public class PlayerTests {

    @Test
    void canCreatePlayerUsingBuilder(){
        //Arrange
        Player testPlayer = new Player.PlayerBuilder(
                "Boggins Jones",
                1).build();
        //Act
        //Assert
        assert (testPlayer.getName().equals("Boggins Jones"));
        assert (testPlayer.getEquipment().containsKey(70));

    }
}
