import java.util.ArrayList;

public class ExplorationHandler {
    private Player player = null;

    private final GameMap gameMap;

    private final ArrayList<GameRoom> gameRooms;
    private final CombatHandler combatHandler = new CombatHandler();

    public ExplorationHandler(Player player, GameMap gameMap, ArrayList<GameRoom> gameRooms) {
        this.player = player;
        this.gameMap = gameMap;
        this.gameRooms = gameRooms;
    }

    public GameRoom getRoom(int index){
        return this.gameRooms.get(index);
    }

    public GameMap getGameMap(){
        return this.gameMap;
    }

    public void ExplorationStart(){
        GameRoom currentRoom = getRoom(player.getCurrentLocation());
        System.out.println("You find yourself in "+currentRoom.getShortDesc());
        if (!currentRoom.getEnemyInRoom().isEmpty()){
            if (currentRoom.getEnemyInRoom().size()>1){
                var enemies = new ArrayList<Enemy>();
                for (int i: currentRoom.getEnemyInRoom()){
                    enemies.add(EnemyTypes.generateEnemy(i));
                }
                combatHandler.combatRound(player,enemies);
            }

        }
        System.out.println("What would you like to do?");
        playerOption();
    }

    private void playerOption() {
        System.out.println("1: Move elsewhere");
    }

}
