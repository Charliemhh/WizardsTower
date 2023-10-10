import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

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
        assert testOBones != null;
        assertEquals(4, testOBones.getStatBlock().getPhysique());
    }

    @Test
    void cannotCreateUndefinedEnemy() {
        Enemy testFail = EnemyTypes.generateEnemy(82);
        assertNull(testFail);
    }

    @Test
    void enemyCanDie() {
        Enemy testOBones = EnemyTypes.generateEnemy(0);
        assertFalse(Objects.requireNonNull(testOBones).getIsDead());
        testOBones.setCurrentHP(0);
        assertTrue(testOBones.getIsDead());

    }
    @Test
    void enemyDamageIsRightType(){
        Enemy testSquid = EnemyTypes.generateEnemy(3);
        assert testSquid != null;
        assertEquals(10,testSquid.getAttackDam());
    }
}
