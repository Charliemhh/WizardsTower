public enum AttackType {
    PHYSICAL(0), SARCASTIC(1), BRAINY(2), SNEAKY(3);

    private final int value;

    AttackType(int value) {
        this.value = value;
    }


    public int getValue() {
        return value;
    }
}
