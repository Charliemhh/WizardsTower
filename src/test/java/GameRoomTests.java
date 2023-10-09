import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Objects;

public class GameRoomTests {

    @Test
    void canCreateRoom(){
        //Act
        GameRoom testRoom = new GameRoom(0);
        //Assert
        assert (testRoom.getRoomIndex()== 0);
    }
    @Test
    void canCreateRoomWithIDs(){
        //Arrange
        ArrayList<Integer> enemyInRoom = new ArrayList<>();
        enemyInRoom.add(0);
        ArrayList<Integer> trapInRoom = new ArrayList<>();
        trapInRoom.add(0);
        //Act
        GameRoom testRoom = new GameRoom(0, enemyInRoom,trapInRoom);
        //Assert
        assert(testRoom.getEnemyInRoom().contains(0));
        assert(testRoom.getTrapInRoom().contains(0));
    }
    @Test
    void canGetRoomDetails(){
        //Arrange
        GameRoom testRoom = new GameRoom(0);
        ArrayList<String> passageDesc = new ArrayList<>();
        passageDesc.add("A Cell door");
        passageDesc.add("Hole in Wall");
        //Act
        testRoom.setShortDesc("A cramped damp gaol cell");
        testRoom.setLongDesc("The walls are slick with mysterious wetness," +
                "a poorly maintained stool sits, lonely, in the corner." +
                "A tattered cloth draped over a rotted wooden frame dominates the room.");
        testRoom.setPassageLabels(passageDesc);
        //Assert
        assert (testRoom.getShortDesc().contains("A cramped damp gaol cell"));
        assert (testRoom.getLongDesc().contains("The walls are slick with mysterious wetness," +
                "a poorly maintained stool sits, lonely, in the corner." +
                "A tattered cloth draped over a rotted wooden frame dominates the room."));
        assert (Objects.equals(testRoom.getPassageLabels().get(0), "A Cell door"));
    }
}
