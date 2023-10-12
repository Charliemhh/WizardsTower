import java.util.ArrayList;

public class CombatHandlerTesting {

    public static void main(String[] args) {
        CombatHandler combatHandler = new CombatHandler();
        Player testPlayer = new Player.PlayerBuilder(
                "Test",
                4).build();
        Enemy testenemy = EnemyTypes.generateEnemy(0);
//        Enemy testEnemy2 = EnemyTypes.generateEnemy(0);
//        ArrayList<Enemy> enemies = new ArrayList<>();
//        enemies.add(testenemy);
//        enemies.add(testEnemy2);
        combatHandler.combatRound(testPlayer,testenemy);
    }
}
