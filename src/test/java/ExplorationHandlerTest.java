import Characters.Player;
import Game.ExplorationHandler;
import Game.GameMap;
import Game.GameRoom;
import Items.Equipment;

import java.util.ArrayList;
import java.util.HashMap;

public class ExplorationHandlerTest {
    public static void main(String[] args) {
        Player testPlayer = new Player.PlayerBuilder(
                "Test",
                0).build();
        testPlayer.getEquipment().addToEquipmentInventory(Equipment.EquipmentGen(5));
        int[][] connections = {{1, 2}, {0, 2}, {0, 1}};
        testPlayer.addXP(200);
        GameMap testMap = new GameMap(3, connections);
        GameRoom testRoom1 = new GameRoom(0);
        GameRoom testRoom2 = new GameRoom(1);
        GameRoom testRoom3 = new GameRoom(2);
        ArrayList<GameRoom> gameRooms = new ArrayList<>();
        gameRooms.add(testRoom1);
        gameRooms.add(testRoom2);
        gameRooms.add(testRoom3);
        addRoomDetails(gameRooms);
        var explorationHandler = new ExplorationHandler(testPlayer, testMap, gameRooms);
        explorationHandler.ExplorationStart();
    }

    private static void addRoomDetails(ArrayList<GameRoom> gameRooms) {
        var testTrapArray = new ArrayList<Integer>();
        var testEnemyArray = new ArrayList<Integer>();
        testTrapArray.add(0);
        testTrapArray.add(1);
        int i = 1;
        for (GameRoom gameRoom : gameRooms) {
            gameRoom.setThingsInRoom(getThingInRoom(gameRoom, i - 1));
            gameRoom.setShortDesc("Test room " + i);
            gameRoom.setLongDesc("Long Description for test room " + i);
            gameRoom.setTrapInRoom(testTrapArray);
            gameRoom.setEnemyInRoom(testEnemyArray);
            getPassageLabels(gameRoom, i - 1);
            i++;
        }
    }

    private static ArrayList<GameRoom.RoomItems> getThingInRoom(GameRoom gameRoom, int index) {
        var testRoomItem = new GameRoom.RoomItems(GameRoom.RoomItemTypes.ITEM, 0, 3,
                "You see the shine of glass");
        var testRoomItem2 = new GameRoom.RoomItems(GameRoom.RoomItemTypes.EQUIPMENT, 6, 0,
                "A long piece of wood is propped up against a wall");
        var testThingsInRoomArray = new ArrayList<GameRoom.RoomItems>();
        testThingsInRoomArray.add(testRoomItem);
        testThingsInRoomArray.add(testRoomItem2);
        return testThingsInRoomArray;
    }

    private static void getPassageLabels(GameRoom gameRoom, int index) {

        switch (index) {
            case 0:
                var passageMap1 = new HashMap<Integer, String>();
                passageMap1.put(1, "passage to test room 2");
                passageMap1.put(2, "passage to test room 3");
                gameRoom.setPassageLabels(passageMap1);
                break;
            case 1:
                var passageMap2 = new HashMap<Integer, String>();
                passageMap2.put(0, "passage to test room 1");
                passageMap2.put(2, "passage to test room 3");
                gameRoom.setPassageLabels(passageMap2);
                break;
            case 2:
                var passageMap3 = new HashMap<Integer, String>();
                passageMap3.put(0, "passage to test room 1");
                passageMap3.put(1, "passage to test room 2");
                gameRoom.setPassageLabels(passageMap3);
                break;
        }
    }
}
