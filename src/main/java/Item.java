import java.util.ArrayList;

public class Item {


    public static String getName(int itemID) {
        switch (itemID) {
            case 0:
                return "Health Potion";
            case 1:
                return "Throwing Knife";
        }
        return "Item Not Found";
    }

    public static String getDesc(int itemID) {
        switch (itemID) {
            case 0:
                return "A burbling flask of ominously sweet liquid, restores your health.";
            case 1:
                return "Throwing these shards of jagged metal makes you feel both dangerous and cool.";
        }
        return "Item Not Found";
    }


    public static int useItem(int itemID, int quantity, Player player, Enemy enemy) {
        if (quantity > 0) {
            itemEffect(itemID, player, enemy);
            return quantity - 1;
        } else {
            System.out.println("You are out of stock!");
            return quantity;
        }
    }
    public static int useItem(int itemID, int quantity, Player player, ArrayList<Enemy> enemies) {
        if (quantity > 0) {
            itemEffect(itemID, player, enemies);
            return quantity - 1;
        } else {
            System.out.println("You are out of stock!");
            return quantity;
        }
    }

    private static void itemEffect(int itemID, Player player, ArrayList<Enemy> enemies) {
        switch (itemID) {
            case 0://Basic Healing Potion
                System.out.println("You feel yourself become more healthy in a vaguely magical way," +
                        " it leaves an unpleasant aftertaste in your mouth");
                player.setCurrentHP(player.getCurrentHP() + 10);
                System.out.println("Your HP is now "+player.getCurrentHP()+"/"+player.getMaxHP());
                break;
            case 1://Throwing Knife
                Enemy enemy = CombatHandler.selectTarget(enemies);
                System.out.println("You deftly throw the knife towards your enemy");
                enemy.setCurrentHP(enemy.getCurrentHP()-3);
                if (enemy.getCurrentHP() < 0) {
                    enemy.setCurrentHP(0);
                }
                System.out.println("Leaving "+enemy.getName()+" at "+enemy.getCurrentHP()+"/"+enemy.getMaxHP()+"HP");
        }
    }

    private static void itemEffect(int itemID, Player player, Enemy enemy) {
        switch (itemID) {
            case 0://Basic Healing Potion
                System.out.println("You feel yourself become more healthy in a vaguely magical way," +
                        " it leaves an unpleasant aftertaste in your mouth");
                player.setCurrentHP(player.getCurrentHP() + 10);
                System.out.println("Your HP is now "+player.getCurrentHP()+"/"+player.getMaxHP());
                break;
            case 1://Throwing Knife
                System.out.println("You deftly throw the knife towards your enemy");
                enemy.setCurrentHP(enemy.getCurrentHP()-3);
                if (enemy.getCurrentHP() < 0) {
                    enemy.setCurrentHP(0);
                }
                System.out.println("Leaving "+enemy.getName()+" at "+enemy.getCurrentHP()+"/"+enemy.getMaxHP()+"HP");
        }
    }
}
