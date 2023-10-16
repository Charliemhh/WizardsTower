package Characters;

public class EnemyTypes {
    public static Enemy generateEnemy(int enemyTypeID) {
        switch (enemyTypeID) {
            case 0://Bag O'Bones
                return new Enemy("Bag O'Bones", 3, AttackType.PHYSICAL, 0, 35, false);
            case 1://Werechicken
                return new Enemy("Were-Chicken", 5, AttackType.SNEAKY, 1, 40, false);
            case 2://Smart-Mouthed Slime
                return new Enemy("Smart-Mouthed Slime", 8, AttackType.SARCASTIC, 2, 50, false);
            case 3://Under-Cooked Calamari
                return new Enemy("Under-Cooked Calamari",6,AttackType.BRAINY,3, 60, false);
            case 4://Shank-wielding Pigeon
                return new Enemy("Shank-wielding Pigeon",5,AttackType.SNEAKY,4,40, false);
            case 5://Pathetic Kobold
                return new Enemy("Pathetic Kobold",5,AttackType.PHYSICAL,5,30, false);
            case 6://Frozen Yoghost
                return new Enemy("Frozen Yoghost", 9,AttackType.BRAINY,6, 60, false);
            case 7://Head-Chef Zamsey - First floor boss
                return new Enemy("Head-Chef Zamsey",40,AttackType.SARCASTIC,7,300, true);

        }
        return null;
    }
}
