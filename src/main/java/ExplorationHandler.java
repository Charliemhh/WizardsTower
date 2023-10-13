import java.util.*;

public class ExplorationHandler {
    private final Player player;

    private final Scanner scanner = new Scanner(System.in);
    private final GameMap gameMap;

    private final ArrayList<GameRoom> gameRooms;
    private GameRoom currentRoom;
    private final CombatHandler combatHandler = new CombatHandler();
    boolean firstRoom = true;
    boolean endOfLevel = false; //To be used to end levels and cause the loading of the next down the line.


    public ExplorationHandler(Player player, GameMap gameMap, ArrayList<GameRoom> gameRooms) {
        this.player = player;
        this.gameMap = gameMap;
        this.gameRooms = gameRooms;
    }

    public GameRoom getRoom(int index) {
        return this.gameRooms.get(index);
    }

    public GameMap getGameMap() {//will be used later.
        return this.gameMap;
    }

    public void ExplorationStart() {
        currentRoom = getRoom(this.player.getCurrentLocation());
        System.out.println("You find yourself in " + currentRoom.getShortDesc());
        while (!this.player.getIsDead()) {
            enemyCheck(currentRoom);
            if (this.player.getIsDead()) {
                break;
            }
            playerOption();
        }
        System.out.println("Game Over!");
    }

    private void enemyCheck(GameRoom currentRoom) {
        if (!currentRoom.getEnemyInRoom().isEmpty()) {
            if (currentRoom.getEnemyInRoom().size() > 1) {
                var enemies = new ArrayList<Enemy>();
                for (int i : currentRoom.getEnemyInRoom()) {
                    enemies.add(EnemyTypes.generateEnemy(i));
                }
                combatHandler.combatRound(this.player, enemies);
                currentRoom.getEnemyInRoom().clear();
            } else {
                Enemy enemy = EnemyTypes.generateEnemy(currentRoom.getEnemyInRoom().get(0));
                combatHandler.combatRound(this.player, Objects.requireNonNull(enemy));
                currentRoom.getEnemyInRoom().clear();
            }
        }
    }

    private void playerOption() {
        if (!firstRoom) {
            trapActivationRisk();
        }
        int option;
        System.out.println("What would you like to do?");
        System.out.println("1: Move elsewhere");
        System.out.println("2: Take a look around");
        System.out.println("3: Check for traps");
        System.out.println("4: Open your inventory");
        System.out.println("5: Look at your equipment");
        while (true) {
            try {
                System.out.println("Choose an Action: ");
                option = scanner.nextInt();
                if (option > 0 && option <= 5) {
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
                this.player.getInventory().presentUseOptions(this.player);
                break;
            case 5:
                this.player.getEquipment().presentForExploration(this.player);
                break;
        }
    }

    private void trapActivationRisk() {
        if (!currentRoom.getTrapInRoom().isEmpty()) {
            Random random = new Random();
            int activationChance = random.nextInt(100);
            if (activationChance > 50) {
                int dodgeChance = this.player.getStatBlock().getLitheness();
                for (int i = 0; i < currentRoom.getTrapInRoom().size(); i++) {
                    int dodgeDifficulty = random.nextInt(10);
                    Trap trapCurrentRoom = Trap.TrapCreator.createTrap(currentRoom.getTrapInRoom().get(i));
                    assert trapCurrentRoom != null;
                    System.out.println(trapCurrentRoom.getActiveDescription());
                    if (dodgeChance < dodgeDifficulty) {
                        this.player.setCurrentHP(this.player.getCurrentHP() - trapCurrentRoom.trapActivate());
                        System.out.println("You fail to dodge! You take " + trapCurrentRoom.getTrapEffects() + " Damage!");
                        System.out.println("Leaving you at " + this.player.getCurrentHP() + "/" + this.player.getMaxHP() + "HP");
                    } else {
                        System.out.println("You manage to dodge out of the way!");
                    }
                }
            }
        }
    }

    private void checkForTraps() {
        if (!currentRoom.getTrapInRoom().isEmpty()) {
            boolean trapFound = false;
            Random random = new Random();
            int discoverChance = this.player.getStatBlock().getBraininess() + this.player.getStatBlock().getLitheness();
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
        this.player.setCurrentLocation(adjRoomList.get(option));
        currentRoom = getRoom(this.player.getCurrentLocation());
        System.out.println("You find yourself in " + currentRoom.getShortDesc());
        firstRoom = false;
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
                this.player.getInventory().pickUp(roomItems.getItemID(), roomItems.getQuantity());
                break;
            case EQUIPMENT:
                this.player.getEquipment().addToEquipmentInventory(Equipment.EquipmentGen(roomItems.getItemID()));

        }
    }
}
