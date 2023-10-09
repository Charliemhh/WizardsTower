import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class GameMapTests {
    //An example of how the constructor will create a map object.
//                          0       1       2       3       4    5
//int connections[][] = { {1, 2}, {0,4}, {0,3,5}, {2,4}, {1,3}, {2} };
//GameMap map1 = new GameMap(4, connections[][])
    @Test
    void MapShouldBeCorrectSize() {
        //Arrange
        int[][] connections = {{1, 2}, {0, 2}, {0, 1}};
        //Act
        GameMap testMap = new GameMap(3, connections);
        //Assert
        System.out.println(testMap.getMap().size());
        assert (testMap.getMap().size() == 3);
        //Expect 3 as there is room 0,1 and 2
    }

    @Test
    void MapContentsShouldBeCorrect() {
        //Arrange
        int[][] connections = {{1, 2}, {0, 2}, {0, 1}};
        ArrayList<Integer> testIndex2 = new ArrayList<>();
        testIndex2.add(0);
        testIndex2.add(1);
        //Act
        GameMap testMap = new GameMap(3, connections);
        //Assert
        assert (testMap.getMap().get(2).containsAll(testIndex2));
        System.out.println(testMap.getMap().get(2));
        System.out.println(testIndex2);
        //Expecting 0, 1
    }
}
