public class Enemy implements Character{

    private int maxHP;
    private StatBlock statBlock;
    @Override
    public int getMaxHP() {
        return this.maxHP;
    }

    @Override
    public StatBlock getStatBlock() {
        return this.statBlock;
    }
}
