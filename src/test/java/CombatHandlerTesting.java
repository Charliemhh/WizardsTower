import Characters.Enemy;
import Characters.EnemyTypes;
import Characters.Player;
import Game.CombatHandler;

import java.util.ArrayList;

public class CombatHandlerTesting {

    public static void main(String[] args) {
        CombatHandler combatHandler = new CombatHandler();
        Player testPlayer = new Player.PlayerBuilder(
                "Test",
                0).build();
        testPlayer.addXP(300);
        Enemy testenemy = EnemyTypes.generateEnemy(5);
        Enemy testEnemy2 = EnemyTypes.generateEnemy(5);
        Enemy testEnemy3 = EnemyTypes.generateEnemy(5);
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(testenemy);
        enemies.add(testEnemy2);
        enemies.add(testEnemy3);
        combatHandler.combatRound(testPlayer,enemies);
    }
}
