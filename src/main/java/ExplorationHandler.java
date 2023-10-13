import java.util.*;

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
                checkThingsInRoom();
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
        if (!currentRoom.getTrapInRoom().isEmpty()) {
            boolean trapFound = false;
            Random random = new Random();
            int discoverChance = player.getStatBlock().getBraininess() + player.getStatBlock().getLitheness();
            for (int i = 0; i < currentRoom.getTrapInRoom().size(); i++) {
                int discoverBoundary = random.nextInt(20);
                int trapID = currentRoom.getTrapInRoom().get(i);
                if (discoverBoundary < discoverChance) {
                    Trap discoveredTrap = Trap.TrapCreator.createTrap(trapID);
                    assert discoveredTrap != null;
                    System.out.println("You discover a trap!");
                    System.out.println(discoveredTrap.getDiscoveryDescription());
                    currentRoom.getTrapInRoom().remove(trapID);
                    trapFound = true;
                    System.out.println("You carefully disarm the trap.");
                }
            }
            if (!trapFound) {
                System.out.println("You don't discover any traps.");
            }
        } else {
            System.out.println("You don't discover any traps.");
        }
    }

    private void navigate() {
        int option = 0;
        boolean choiceMade = false;
        var adjRoomList = gameMap.getMap().get(currentRoom.getRoomIndex());
        while (!adjRoomList.contains(adjRoomList.get(option)) || (!choiceMade)) {
            try {
                for (int i : adjRoomList) {
                    System.out.println(adjRoomList.indexOf(i) + 1 + " : " + currentRoom.getPassageLabels().get(i));
                }
                System.out.println("Choose a direction:");
                option = scanner.nextInt();
                option -= 1;
                choiceMade = true;
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Please enter a valid option:");
            }
        }
        player.setCurrentLocation(adjRoomList.get(option));
        currentRoom = getRoom(player.getCurrentLocation());
        System.out.println("You find yourself in " + currentRoom.getShortDesc());
    }

    public void checkThingsInRoom() {
        int i = 0;
        Scanner scanner = new Scanner(System.in);
        if (!this.currentRoom.getThingsInRoom().isEmpty()) {


            while (true) {
                try {
                    for (GameRoom.RoomItems thing : this.currentRoom.getThingsInRoom()) {
                        System.out.println(i + 1 + ":" + thing.getInspectDescription());
                        i++;
                    }
                    int option = scanner.nextInt();
                    if (this.currentRoom.getThingsInRoom().get(option - 1) != null) {
                        roomItemPickUp(this.currentRoom.getThingsInRoom().get(option - 1));
                        this.currentRoom.getThingsInRoom().remove(this.currentRoom.getThingsInRoom().get(option - 1));
                        break;
                    }
                } catch (InputMismatchException e) {
                    scanner.next();
                    System.out.println("Please select a valid option");
                }
            }
        } else {
            System.out.println("Nothing catches your eye");
        }

    }

    private void roomItemPickUp(GameRoom.RoomItems roomItems) {
        switch (roomItems.getType()) {
            case ITEM:
                player.getInventory().pickUp(roomItems.getItemID(), roomItems.getQuantity());
                break;
            case EQUIPMENT:
                player.getEquipment().addToEquipmentInventory(Equipment.EquipmentGen(roomItems.getItemID()));

        }
    }
}
