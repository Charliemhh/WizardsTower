import java.util.ArrayList;
import java.util.HashMap;

public class ExplorationHandlerTest {
    public static void main(String[] args) {
        Player testPlayer = new Player.PlayerBuilder(
                "Test",
                0).build();
        testPlayer.getEquipment().addToEquipmentInventory(5,BodySlot.MAINHAND);
        int[][] connections = {{1, 2}, {0, 2}, {0, 1}};
        GameMap testMap = new GameMap(3, connections);
        GameRoom testRoom1 = new GameRoom(0);
        GameRoom testRoom2 = new GameRoom(0);
        GameRoom testRoom3 = new GameRoom(0);
        ArrayList<GameRoom> gameRooms = new ArrayList<>();
        gameRooms.add(testRoom1);
        gameRooms.add(testRoom2);
        gameRooms.add(testRoom3);
        addRoomDetails(gameRooms);
        var explorationHandler = new ExplorationHandler(testPlayer,testMap,gameRooms);
        explorationHandler.ExplorationStart();
    }

    private static void addRoomDetails(ArrayList<GameRoom> gameRooms) {
        var testTrapArray = new ArrayList<Integer>();
        testTrapArray.add(0);
        var testEnemyArray = new ArrayList<Integer>();
        testEnemyArray.add(0);
        int i = 1;
        for (GameRoom gameRoom : gameRooms) {
            gameRoom.setShortDesc("Test room " + i);
            gameRoom.setLongDesc("Long Description for test room " + i);
            gameRoom.setTrapInRoom(testTrapArray);
            gameRoom.setEnemyInRoom(testEnemyArray);
            getPassageLabels(gameRoom,i);
            i++;
        }
    }

    private static void getPassageLabels(GameRoom gameRoom,int index) {

        switch (index){
            case 1:
                var passageMap1 = new HashMap<Integer,String>();
                passageMap1.put(2,"passage to test room 2");
                passageMap1.put(3,"passage to test room 3");
                gameRoom.setPassageLabels(passageMap1);
            case 2:
                var passageMap2 = new HashMap<Integer,String>();
                passageMap2.put(1,"passage to test room 1");
                passageMap2.put(3,"passage to test room 3");
                gameRoom.setPassageLabels(passageMap2);
            case 3:
                var passageMap3 = new HashMap<Integer,String>();
                passageMap3.put(1,"passage to test room 1");
                passageMap3.put(2,"passage to test room 2");
                gameRoom.setPassageLabels(passageMap3);
        }
    }
}
