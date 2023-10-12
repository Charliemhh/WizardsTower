import java.util.ArrayList;

public class ItemInventory {

    private final ArrayList<Item> playerInventory;

    public ItemInventory() {
        this.playerInventory = new ArrayList<>();
    }

    public ArrayList<Item> getPlayerInventory() {
        return playerInventory;
    }

    public void addToInventory(int itemID, int quantity) {
        this.playerInventory.add(Item.ItemGen(itemID, quantity));
    }

    public void removeFromInventory(int itemID) {
        playerInventory.remove(itemID);
    }

    public Item findItem(int itemID) {
        for (Item item : playerInventory) {
            if (item.getItemID() == itemID) {
                return item;
            }
        }
        return null;
    }

    public void useItem(int itemID, Player player, Enemy enemy) {
        if (findItem(itemID) != null) {
            Item foundItem = findItem(itemID);
            switch (foundItem.getItemEffectType()){
                case SINGLETARGET:
                    foundItem.useItemSingleTarget(enemy);
                    break;
                case CURE:
                    foundItem.useItemCure(player);
                    break;
                case HEALING:
                    foundItem.useItemHealing(player);
                    break;
                case MULTITARGET:
                    foundItem.useItemMultiTarget(enemy);
                    break;
            }
        }
        else {
            System.out.println("Item was not found ERROR");
        }
    }

    public void useItem(int itemID, Player player, ArrayList<Enemy> enemies) {
        if (findItem(itemID) != null) {
            Item foundItem = findItem(itemID);
            switch (foundItem.getItemEffectType()){
                case SINGLETARGET:
                    foundItem.useItemSingleTarget(enemies);
                    break;
                case CURE:
                    foundItem.useItemCure(player);
                    break;
                case HEALING:
                    foundItem.useItemHealing(player);
                    break;
                case MULTITARGET:
                    foundItem.useItemMultiTarget(enemies);
                    break;
            }
        }
        else {
            System.out.println("Item was not found ERROR");
        }
    }

    public void seeInventory() {
        for (int i = 0; i < playerInventory.size();i++){
            Item item = playerInventory.get(i);
            System.out.println(i+1 + ":" + item.getName() + " x " + item.getQuantity());
        }
    }

    public void seeDescription(int itemID) {
        for (Item item : playerInventory) {
            if (item.getItemID() == itemID) {
                System.out.println(item.getExamineText());
            }
        }
    }
}
