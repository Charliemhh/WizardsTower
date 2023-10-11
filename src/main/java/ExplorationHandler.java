import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExplorationHandler {
    private Player player = null;

    private final Scanner scanner = new Scanner(System.in);
    private final GameMap gameMap;

    private final ArrayList<GameRoom> gameRooms;
    private GameRoom currentRoom;
    private final CombatHandler combatHandler = new CombatHandler();


    public ExplorationHandler(Player player, GameMap gameMap, ArrayList<GameRoom> gameRooms) {
        this.player = player;
        this.gameMap = gameMap;
        this.gameRooms = gameRooms;
    }

    public GameRoom getRoom(int index) {
        return this.gameRooms.get(index);
    }

    public GameMap getGameMap() {
        return this.gameMap;
    }

    public void ExplorationStart() {
        currentRoom = getRoom(player.getCurrentLocation());
        System.out.println("You find yourself in " + currentRoom.getShortDesc());
        enemyCheck(currentRoom);
        while (!player.getIsDead()) {
            playerOption();
        }
    }

    private void enemyCheck(GameRoom currentRoom) {
        if (!currentRoom.getEnemyInRoom().isEmpty()) {
            if (currentRoom.getEnemyInRoom().size() > 1) {
                var enemies = new ArrayList<Enemy>();
                for (int i : currentRoom.getEnemyInRoom()) {
                    enemies.add(EnemyTypes.generateEnemy(i));
                }
                combatHandler.combatRound(player, enemies);
            }
        }
    }

    private void playerOption() {
        int option;
        System.out.println("What would you like to do?");
        System.out.println("1: Move elsewhere");
        System.out.println("2: Take a look around");
        System.out.println("3: Check for traps");
        System.out.println("4: Open your inventory");
        System.out.println("5: Open your equipment");
        System.out.println("6: Look at your currently worn equipment");
        //checkForThings();
        while (true) {
            try {
                System.out.println("Choose an Action: ");
                option = scanner.nextInt();
                if (option > 0 && option <= 6) {
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Please chose a valid option:");
            }
        }
        switch (option) {
            case 1:
                navigate();
                break;
            case 2:
                System.out.println(currentRoom.getLongDesc());
                break;
            case 3:
                checkForTraps();
                break;
            case 4:
                player.getInventory().seeInventory();//for now should have ability to use.
                break;
            case 5:
                for (int i : player.getEquipment().getEquipmentInventory().keySet()) {
                    System.out.println("Slot: " + player.getEquipment().getEquipmentInventory().get(i)
                            + "  Name: " + player.getEquipment().getEquipmentName(i));
                }
                break;
            case 6:
                player.getEquipment().getEquippedItems();
                break;
        }
    }

    private void checkForTraps() {

    }

    private void navigate() {

    }

    private void checkForThings() {
        //objects in room can only be truly implemented when items and equipment are true objects
//        for (Object thing :currentRoom.getThingsInRoom()){
//            if (thing.getClass()== )
//        }
    }

}
