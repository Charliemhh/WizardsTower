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
        Enemy testenemy = EnemyTypes.generateEnemy(0);
        Enemy testEnemy2 = EnemyTypes.generateEnemy(0);
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(testenemy);
        enemies.add(testEnemy2);
        combatHandler.combatRound(testPlayer,enemies);
    }
}
