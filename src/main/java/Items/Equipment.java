package Items;

import Characters.AttackType;

public class Equipment {




    public enum BodySlot {
        HEAD, CHEST, MAINHAND, OFFHAND, PANTS, SHOES, ARMS
    }

    public enum EquipmentEffectType {
        DEFENCEBUFF, ATTACKBUFF, STATBUFF, NOBUFF
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
            case 6:
                return new Equipment(6,BodySlot.MAINHAND,EquipmentEffectType.ATTACKBUFF,
                        AttackType.BRAINY,2,"Hand-me-down Staff",
                        "Its more of a self-confident twig than a staff");
            case 7:
                return new Equipment(7,BodySlot.HEAD,EquipmentEffectType.NOBUFF,null,
                        0,"Off-brand Wizard's hat",
                        "Your mum said it was cool once.");
            case 8:
                return new Equipment(8,BodySlot.CHEST,EquipmentEffectType.DEFENCEBUFF,null,2,
                        "Normal Suit","Freshly Ironed, smells like success");
            case 9:
                return new Equipment(9,BodySlot.MAINHAND,EquipmentEffectType.ATTACKBUFF,AttackType.PHYSICAL,
                        3,"Briefcase","Contains all your documents for handling monster killing-based legal matters.");
            case 10:
                return new Equipment(10,BodySlot.MAINHAND,EquipmentEffectType.ATTACKBUFF,AttackType.SARCASTIC,2
                ,"Sarcasm Bat","Its a purely metaphorical bat, similar to a sharp wit");
            case 11:
                return new Equipment(11,BodySlot.CHEST,EquipmentEffectType.DEFENCEBUFF,null,2,
                        "Lord of the Things(tm)","This shirt displays to all you that you are a wellspring of movie trivia");
            case 12:
                return new Equipment(12,BodySlot.SHOES,EquipmentEffectType.DEFENCEBUFF,null,1,
                        "Posh Shoes"
                        ,"Surprisingly clean considering you've been walking through a monster filled tower");
            case 13:
                return new Equipment(13,BodySlot.CHEST,EquipmentEffectType.DEFENCEBUFF,null,2,
                        "Robe with holes in","At least it's comfortable to wear...");
        }
        return null;
    }

}
