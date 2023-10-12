import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
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
        while (!player.getIsDead()) {
            enemyCheck(currentRoom);
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
                currentRoom.getEnemyInRoom().clear();
            } else {
                Enemy enemy = EnemyTypes.generateEnemy(currentRoom.getEnemyInRoom().get(0));
                combatHandler.combatRound(player, Objects.requireNonNull(enemy));
                currentRoom.getEnemyInRoom().clear();
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
                for (Equipment e : player.getEquipment().getEquipmentInventory()) {
                    System.out.println("Slot: " + e.getBodySlot()
                            + "  Name: " + e.getName());
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
        int option = 909;
        while (!currentRoom.getPassageLabels().containsKey(option)) {
            try {
                System.out.println("Choose a direction:");
                int i = 1;
                for (int j : currentRoom.getPassageLabels().keySet()) {
                    System.out.println(i + " : " + currentRoom.getPassageLabels().get(j));
                    i++;
                }
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next();
            }
            System.out.println("Please enter a valid option:");
        }
        currentRoom = getRoom(player.getCurrentLocation());
        System.out.println("You find yourself in " + currentRoom.getShortDesc());
    }

    private void checkForThings() {
        //objects in room can only be truly implemented when items and equipment are true objects
//        for (Object thing :currentRoom.getThingsInRoom()){
//            if (thing.getClass()== )
//        }
    }

}
