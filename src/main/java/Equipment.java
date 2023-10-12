public class Equipment {




    public enum BodySlot {
        HEAD, CHEST, MAINHAND, OFFHAND, PANTS, SHOES, ARMS;
    }

    public enum EquipmentEffectType {
        DEFENCEBUFF, ATTACKBUFF, STATBUFF, NOBUFF;
    }

    private final BodySlot bodySlot;

    private final EquipmentEffectType effectType;

    private AttackType attackType;

    private final int effectPower;

    private final int equipmentID;

    private final String name;

    private final String examineText;

    public Equipment(int equipmentID, BodySlot bodySlot, EquipmentEffectType effectType, AttackType attackType, int effectPower, String name, String examineText) {
        this.bodySlot = bodySlot;
        this.effectType = effectType;
        this.attackType = attackType;
        this.effectPower = effectPower;
        this.equipmentID = equipmentID;
        this.name = name;
        this.examineText = examineText;
    }

    public BodySlot getBodySlot() {
        return bodySlot;
    }

    public EquipmentEffectType getEffectType() {
        return effectType;
    }

    public int getEffectPower() {
        return effectPower;
    }
    public int getEquipmentID() {
        return equipmentID;
    }

    public String getName() {
        return name;
    }

    public String getExamineText() {
        return examineText;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }

    public static Equipment EquipmentGen(int equipmentID) {
        switch (equipmentID) {
            case 0:
                return new Equipment(0, BodySlot.HEAD,
                        EquipmentEffectType.DEFENCEBUFF, null, 2, "Iron Helmet",
                        "Its a rusty hand-me-down, but it might save your life.");
            case 1:
                return new Equipment(1, BodySlot.CHEST,
                        EquipmentEffectType.DEFENCEBUFF, null, 1, "Chain-Mail Shirt",
                        "It's heavy, makes you sweat and makes it hard to breathe.");
            case 2:
                return new Equipment(2, BodySlot.MAINHAND, EquipmentEffectType.ATTACKBUFF, AttackType.PHYSICAL,
                        0, "Rusty Sword", "You might as well nicely ask the enemies to start bleeding.");
            case 3:
                return new Equipment(3, BodySlot.HEAD, EquipmentEffectType.DEFENCEBUFF, null,
                        1, "Dusty Beret", "It's not as cool looking as you think");
            case 4:
                return new Equipment(4, BodySlot.CHEST, EquipmentEffectType.DEFENCEBUFF, null,
                        1, "Leather Shirt", "It still stinks of beer from the last hero who wore it.");
            case 5:
                return new Equipment(5, BodySlot.MAINHAND, EquipmentEffectType.ATTACKBUFF,
                        AttackType.PHYSICAL, 2, "Wee Dagger",
                        "Stick 'em with the pointy end");
        }
        return null;
    }

}
