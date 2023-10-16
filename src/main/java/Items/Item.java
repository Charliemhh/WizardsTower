package Items;

import Characters.Enemy;
import Characters.Player;
import Game.CombatHandler;

import java.util.ArrayList;

public class Item {


    public enum ItemEffectType {
        HEALING, SINGLETARGET, MULTITARGET, CURE

    }
    //Let the refactoring commence!

    private final String name;
    private final String examineText;
    private final String useText;

    private final ItemEffectType itemEffectType;
    private final int effectPower;
    private final int itemID;
    private int quantity;


    public Item(int itemID, String name, String examineText, String useText, ItemEffectType effectType, int effectPower, int quantity) {
        this.itemID = itemID;
        this.name = name;
        this.examineText = examineText;
        this.useText = useText;
        this.itemEffectType = effectType;
        this.effectPower = effectPower;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getExamineText() {
        return examineText;
    }

    public ItemEffectType getItemEffectType() {
        return itemEffectType;
    }


    public int getItemID() {
        return itemID;
    }

    public void useItemSingleTarget(Enemy enemy, Player player) {
        if (this.quantity > 0) {
            System.out.println(this.useText);
            enemy.setCurrentHP(enemy.getCurrentHP() - this.effectPower);
            if (enemy.getCurrentHP() < 0) {
                enemy.setCurrentHP(0);
            }
            System.out.println("Leaving " + enemy.getName() + " at " + enemy.getCurrentHP() + "/" + enemy.getMaxHP() + "HP");
            this.quantity -= 1;
        } else {
            System.out.println("You are out of stock!");

        }
        inventoryCleanUp(player);
    }

    public void useItemSingleTarget(ArrayList<Enemy> enemies, Player player) {
        if (this.quantity > 0) {
            Enemy enemy = CombatHandler.selectTarget(enemies);
            System.out.println(this.useText);
            enemy.setCurrentHP(enemy.getCurrentHP() - this.effectPower);
            if (enemy.getCurrentHP() < 0) {
                enemy.setCurrentHP(0);
            }
            System.out.println("Leaving " + enemy.getName() +
                    " at " + enemy.getCurrentHP() + "/" + enemy.getMaxHP() + "HP");
            this.quantity -= 1;
        } else {
            System.out.println("You are out of stock!");
        }
        inventoryCleanUp(player);
    }

    private void inventoryCleanUp(Player player) {
        if (this.quantity == 0){
            player.getInventory().removeFromInventory(this.getItemID());
        }
    }



    public void useItemCure(Player player) {
        if (this.quantity > 0){
            System.out.println(this.useText);
            //Will be added to later when there are status effects
        }
        else{
            System.out.println("You are out of stock!");
        }
        inventoryCleanUp(player);
    }

    public void useItemHealing(Player player) {
        if (this.quantity > 0){
            System.out.println(this.useText);
            player.setCurrentHP(player.getCurrentHP()+this.effectPower);
            System.out.println("Your HP is now: " + player.getCurrentHP() +"/"+ player.getMaxHP()+"HP");
            this.quantity -= 1;
        }
        else {
            System.out.println("You are out of stock!");
        }
        inventoryCleanUp(player);
    }

    public void useItemMultiTarget(ArrayList<Enemy> enemies, Player player) {
        if (this.quantity > 0){
            System.out.println(this.useText);
            for (Enemy enemy: enemies){
                enemy.setCurrentHP(enemy.getCurrentHP()-this.effectPower);
                if (enemy.getCurrentHP() < 0) {
                    enemy.setCurrentHP(0);
                }
                System.out.println("Leaving " + enemy.getName() +
                        " at " + enemy.getCurrentHP() + "/" + enemy.getMaxHP() + "HP");
            }
            this.quantity -= 1;
        }
        else {
            System.out.println("You are out of Stock!");
        }
        inventoryCleanUp(player);
    }

    public void useItemMultiTarget(Enemy enemy, Player player) {
        useItemSingleTarget(enemy,player);
    }

    public static Item ItemGen(int itemID, int quantity) {
        switch (itemID) {
            case 0:
                return new Item(0, "Healing Potion",
                        "A burbling flask of ominously sweet liquid, restores your health.",
                        "You feel yourself become more healthy in a vaguely magical way," +
                                " it leaves an unpleasant aftertaste in your mouth", ItemEffectType.HEALING,
                        10, quantity);
            case 1:
                return new Item(1, "Throwing Knife",
                        "Throwing these shards of jagged metal makes you feel both dangerous and cool.",
                        "You deftly throw the knife towards your enemy", ItemEffectType.SINGLETARGET,
                        5, quantity);
            case 2:
                return new Item(2,"Firebomb",
                        "Fireballs for the masses! Smells of sulfur and bronze",
                        "The firebomb explodes!", ItemEffectType.MULTITARGET,
                        6,quantity);
            case 3:
                return new Item(3,"Joke Book","Sir Fitzroy's Jokes for the daft and useless",
                        "The enemy is psychically damaged with how unfunny you are. Are you proud of yourself?",
                        ItemEffectType.SINGLETARGET,6,quantity);
        }
        System.out.println("Not Valid Items.Item ID");
        return null;
    }
}
