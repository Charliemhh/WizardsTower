public class Enemy implements Character {

    private final int maxHP;
    private int currentHP;
    private final StatBlock statBlock;

    private final int attackDam;

    private final String name;

    private boolean isDead;

    @Override
    public int getCurrentHP() {
        return this.currentHP;
    }

    @Override
    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
        if (currentHP <= 0) {
            setDead(true);
        }
    }

    @Override
    public int getMaxHP() {
        return this.maxHP;
    }

    @Override
    public StatBlock getStatBlock() {
        return this.statBlock;
    }

    public String getName() {
        return name;
    }

    public int getAttackDam(AttackType type) {
        switch (type) {
            case PHYSICAL:
                return (this.getStatBlock().getStrength());
            case SARCASTIC:
                return (this.getStatBlock().getBanter());
            case BRAINY:
                return (this.getStatBlock().getBraininess());
            case SNEAKY:
                return (this.getStatBlock().getLitheness());
        }
        return attackDam;
    }

    public Enemy(String name, int maxHPBase, AttackType attackType, int enemyTypeID) {
        this.name = name;
        this.isDead = false;
        this.statBlock = new StatBlock();
        this.statBlock.generateEnemyStats(enemyTypeID);
        this.maxHP = maxHPBase + this.statBlock.getPhysique();
        this.attackDam = getAttackDam(attackType);
    }

    public boolean getIsDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
