public class EnemyTypes {
    public static Enemy generateEnemy(int enemyTypeID) {
        switch (enemyTypeID) {
            case 0://Bag O'Bones
                Enemy BagOBones = new Enemy("Bag O'Bones", 10, AttackType.PHYSICAL, 0);
                BagOBones.getStatBlock().generateEnemyStats(enemyTypeID);
                return BagOBones;
            case 1://Werechicken
                Enemy Werechicken = new Enemy("Were-Chicken", 15, AttackType.PHYSICAL, 1);
                Werechicken.getStatBlock().generateEnemyStats(enemyTypeID);
                return Werechicken;
            case 2: //Smart-Mouthed Slime
                Enemy SmartMouthedSlime = new Enemy("Smart-Mouthed Slime", 20, AttackType.SARCASTIC, 2);
                SmartMouthedSlime.getStatBlock().generateEnemyStats(enemyTypeID);
                return SmartMouthedSlime;
            //Enemy types and stats not final.
        }
        return null;
    }
}
