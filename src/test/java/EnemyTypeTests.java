import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class EnemyTypeTests {
    @Test
    void canGenerateEachType() {
        //Arrange
        Enemy testOBones = EnemyTypes.generateEnemy(0);
        Enemy testChicken = EnemyTypes.generateEnemy(1);
        Enemy testSlime = EnemyTypes.generateEnemy(2);
        //Assert
        Assertions.assertNotNull(testOBones);
        Assertions.assertNotNull(testChicken);
        Assertions.assertNotNull(testSlime);
    }

    @Test
    void enemyStatsAreCorrect() {
        //Arrange
        Enemy testOBones = EnemyTypes.generateEnemy(0);
        //Assert
        assertEquals(4, testOBones.getStatBlock().getPhysique());
    }
    @Test
    void cannotCreateUndefinedEnemy(){
        Enemy testFail = EnemyTypes.generateEnemy(82);
        assertNull(testFail);
    }
}
