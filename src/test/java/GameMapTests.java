import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

public class GameMapTests {

    int[][] connections = {{1, 2}, {0, 2}, {0, 1}};
    GameMap testMap = new GameMap(3, connections);

    @Test
    void MapShouldBeCorrectSize() {
        assert (testMap.getMap().size() == 3);
        //Expect 3 as there is room 0,1 and 2
    }

    @Test
    void MapContentsShouldBeCorrect() {
        //Arrange
        ArrayList<Integer> testIndex2 = new ArrayList<>();
        testIndex2.add(0);
        testIndex2.add(1);
        //Act
        GameMap testMap = new GameMap(3, connections);
        //Assert
        assert (testMap.getMap().get(2).containsAll(testIndex2));
        //Expecting 0, 1
    }

    @Test
    void defaultAdjacentRoomShouldBeCorrect() {
        //Arrange
        LinkedList<Integer> testIndex0 = new LinkedList<>();
        testIndex0.add(1);
        testIndex0.add(2);
        //Act
        GameMap testMap = new GameMap(3, connections);
        //Assert
        assert (testIndex0.containsAll(testMap.getAdjacentRooms()));
        //Expect 1 and 2 for location 0


    }
    @Test
    void newAdjacentRoomShouldBeCorrect(){
        //Arrange
        LinkedList<Integer> testIndex1 = new LinkedList<>();
        testIndex1.add(0);
        testIndex1.add(2);
        //Act
        testMap.setCurrentRoomIndex(1);
        //Assert
        assert (testIndex1.containsAll(testMap.getAdjacentRooms()));
        //Expect 0 and 2 for location 1 - imagine the player has moved
    }
}
