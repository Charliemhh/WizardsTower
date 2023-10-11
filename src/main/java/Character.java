public interface Character {

    int getCurrentHP();

    void setCurrentHP(int currentHP);

    int getMaxHP();

    int[] getAttackDam();

    int genAttackDam(AttackType type);
    StatBlock getStatBlock();

    String getName();
}
