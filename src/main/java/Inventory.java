import java.util.HashMap;

public class Inventory {

    private final HashMap<Integer, Integer> playerInventory;

    public Inventory() {
        this.playerInventory = new HashMap<>();
    }

    public HashMap<Integer, Integer> getPlayerInventory() {
        return playerInventory;
    }

    public void addToInventory(int itemID, int quantity) {
        playerInventory.put(itemID, quantity);
    }

    public void removeFromInventory(int itemID, int quantity) {
        playerInventory.remove(itemID);
    }

    public void useItem(int itemID, Player player) {
        Item.useItem(itemID, playerInventory.get(itemID), player);
    }

    public void seeInventory(){
        for (int i: playerInventory.keySet()){
            System.out.println(Item.getName(i));
        }
    }
}
