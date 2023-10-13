import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameCreator {


    public static void main(String[] args) throws InterruptedException {
        GameMap gameMap = createGameMap(0);
        Scanner scanner = new Scanner(System.in);
        ArrayList<GameRoom> gameRooms = populateGameMap(gameMap);
        System.out.println("Welcome to Wizard's Tower!");
        Player player = createYourCharacter();
        welcomeMessage(player);
        ExplorationHandler explorationHandler = new ExplorationHandler(player, gameMap, gameRooms);
        Thread.sleep(1500);
        explorationHandler.ExplorationStart();

    }

    private static Player createYourCharacter() {
        Scanner scanner = new Scanner(System.in);
        int selectedClass = 0;
        System.out.println("Please enter your name: ");
        String name = scanner.next();
        System.out.println("Please select your class: ");
        System.out.println("1: Squire's Assistant");
        System.out.println("2: Clumsy Thief");
        System.out.println("3: Trainee Wizard");
        System.out.println("4: Mild-Mannered Accountant");
        System.out.println("5: Sarcastic So-And-So");
        while (true) {
            try {
                int option = scanner.nextInt();
                option -= 1;
                if (option >= 0 && option < 6) {
                    selectedClass = option;
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please make a valid selection: ");
                scanner.next();
            }
        }
        return new Player.PlayerBuilder(name, selectedClass).build();
    }

    private static ArrayList<GameRoom> populateGameMap(GameMap gameMap) {
        ArrayList<GameRoom> gameRooms = new ArrayList<>();
        for (int i = 0; i < gameMap.getMap().size(); i++) {
            GameRoom gameRoom = new GameRoom(i);
            gameRooms.add(i, gameRoom);
        }
        gameRooms = addRoomDetails(gameRooms);
        return gameRooms;
    }

    private static GameMap createGameMap(int i) {
        if (i == 0) {
            int[][] connections = {{1, 2}, {0, 2}, {0, 1}};
            return new GameMap(3, connections);
        }
        return null;
    }

    private static void welcomeMessage(Player player) {
        System.out.println("Welcome " + player.getName() + " to the wizard's tower!");
        System.out.println(" The great wizard Bumble-Guff has called for help" +
                " the king has tasked you with climbing his tower with the goal of saving him.\n " +
                "Sadly for you the king doesn't much like bumble-guff after an unfortunate incident " +
                "at the castle.\n So, rather than someone competent, you’ve been sent." +
                "\nThe creatures and traps lying in wait will confound even the most intrepid of adventurers," +
                " so be careful!\n\n");
    }

    private static ArrayList<GameRoom> addRoomDetails(ArrayList<GameRoom> gameRooms) {
        var populatedGameRooms = new ArrayList<GameRoom>();
        for (int i = 0;i < gameRooms.size();i++) {
            GameRoom gameRoom = gameRooms.get(i);
            var testTrapArray = new ArrayList<Integer>();
            var testEnemyArray = new ArrayList<Integer>();
            switch (gameRoom.getRoomIndex()) {
                case 0:
                    testTrapArray.add(0);
                    testEnemyArray.add(1);
                    break;
                case 1:
                    testEnemyArray.add(0);
                    testEnemyArray.add(0);
                    break;
                case 2:
                    testTrapArray.add(1);
                    testEnemyArray.add(2);
                    break;
            }
            gameRoom.setThingsInRoom(getThingInRoom(gameRoom, i));
            gameRoom.setShortDesc("Test room " + (i+1));
            gameRoom.setLongDesc("Long Description for test room " + (i+1));
            gameRoom.setTrapInRoom(testTrapArray);
            gameRoom.setEnemyInRoom(testEnemyArray);
            getPassageLabels(gameRoom, i);
            populatedGameRooms.add(gameRoom.getRoomIndex(),gameRoom);
        }
        return populatedGameRooms;
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
