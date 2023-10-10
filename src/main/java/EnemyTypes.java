public class EnemyTypes {
    public static Enemy generateEnemy(int enemyTypeID) {
        switch (enemyTypeID) {
            case 0://Bag O'Bones
                return new Enemy("Bag O'Bones", 5, AttackType.PHYSICAL, 0);
            case 1://Werechicken
                return new Enemy("Were-Chicken", 7, AttackType.SNEAKY, 1);
            case 2: //Smart-Mouthed Slime
                return new Enemy("Smart-Mouthed Slime", 13, AttackType.SARCASTIC, 2);
            case 3: //Under-Cooked Calamari
                return new Enemy("Under-Cooked Calamari",11,AttackType.BRAINY,3);
            //Enemy types and stats not final.
        }
        return null;
    }
}
