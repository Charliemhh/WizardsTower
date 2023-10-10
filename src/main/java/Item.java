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


    public static int useItem(int itemID, int quantity, Player player) {
        if (quantity > 0) {
            itemEffect(itemID, player);
            return quantity - 1;
        } else {
            System.out.println("You are out of stock!");
            return quantity;
        }
    }

    private static void itemEffect(int itemID, Player player) {
        switch (itemID) {
            case 0://Basic Healing Potion
                player.setCurrentHP(player.getCurrentHP() + 10);
                break;
            case 1://Throwing Knife
                //Needs combat handler knowledge to work could ask player for target before using?
        }
    }
}
