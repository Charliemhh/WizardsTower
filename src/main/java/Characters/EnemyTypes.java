package Characters;

public class EnemyTypes {
    public static Enemy generateEnemy(int enemyTypeID) {
        switch (enemyTypeID) {
            case 0://Bag O'Bones
                return new Enemy("Bag O'Bones", 3, AttackType.PHYSICAL, 0, 25);
            case 1://Werechicken
                return new Enemy("Were-Chicken", 5, AttackType.SNEAKY, 1, 35);
            case 2://Smart-Mouthed Slime
                return new Enemy("Smart-Mouthed Slime", 8, AttackType.SARCASTIC, 2, 40);
            case 3://Under-Cooked Calamari
                return new Enemy("Under-Cooked Calamari",6,AttackType.BRAINY,3, 45);
            case 4://Shank-wielding Pigeon
                return new Enemy("Shank-wielding Pigeon",5,AttackType.SNEAKY,4,30);
            case 5://Pathetic Kobold
                return new Enemy("Pathetic Kobold",5,AttackType.PHYSICAL,5,25);

        }
        return null;
    }
}
