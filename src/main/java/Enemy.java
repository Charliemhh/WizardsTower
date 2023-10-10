public class Enemy implements Character{

    private int maxHP;
    private StatBlock statBlock;

    private int attackDam;

    private String name;

    @Override
    public int getCurrentHP() {
        return 0;
    }

    @Override
    public void setCurrentHP(int currentHP) {

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

    public int getAttackDam() {
        return attackDam;
    }

    public Enemy(String name, int maxHPBase,int attackDam,int enemyTypeID) {
        this.name = name;
        this.statBlock = new StatBlock();
        this.statBlock.generateEnemyStats(enemyTypeID);
        this.maxHP = maxHPBase + this.statBlock.getPhysique();
        this.attackDam = attackDam;
    }
}
