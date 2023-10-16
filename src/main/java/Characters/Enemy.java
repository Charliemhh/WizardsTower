package Characters;

public class Enemy implements Character {

    private final int maxHP;
    private int currentHP;
    private final StatBlock statBlock;

    private final int rewardXP;

    private final int attackDam;

    private String name;

    private boolean isDead;

    private final AttackType attackType;

    @Override
    public int getCurrentHP() {
        return this.currentHP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    @Override
    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
        if (currentHP <= 0) {
            setDead(true);
        }
    }

    public AttackType getAttackType() {
        return this.attackType;
    }

    @Override
    public int getMaxHP() {
        return this.maxHP;
    }

    @Override
    public StatBlock getStatBlock() {
        return this.statBlock;
    }

    public int genAttackDam(AttackType type) {
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

    public int[] getAttackDam() {
        int attackTypeInt = this.attackType.getValue();
        return new int[]{this.attackDam,attackTypeInt};
    }

    public Enemy(String name, int maxHPBase, AttackType attackType, int enemyTypeID, int rewardXP) {
        this.name = name;
        this.rewardXP = rewardXP;
        this.isDead = false;
        this.statBlock = new StatBlock();
        this.statBlock.generateEnemyStats(enemyTypeID);
        this.maxHP = maxHPBase + this.statBlock.getPhysique();
        this.currentHP = this.maxHP;
        this.attackType = attackType;
        this.attackDam = genAttackDam(attackType);
    }

    public boolean getIsDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getRewardXP() {
        return rewardXP;
    }
}
