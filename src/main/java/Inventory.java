import java.util.ArrayList;
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

    public void removeFromInventory(int itemID) {
        playerInventory.remove(itemID);
    }

    public void useItem(int itemID, Player player, Enemy enemy) {
        int newQuantity = Item.useItem(itemID, playerInventory.get(itemID), player, enemy);
        playerInventory.put(itemID, newQuantity);//Updates with reduced Quantity
    }

    public void useItem(int itemID, Player player, ArrayList<Enemy> enemies) {
        int newQuantity = Item.useItem(itemID, playerInventory.get(itemID), player, enemies);
        playerInventory.put(itemID, newQuantity);//Updates with reduced Quantity
    }

    public void seeInventory() {
        for (int i : playerInventory.keySet()) {
            int j = i + 1;
            System.out.println(j + ":" + Item.getName(i) + " x " + playerInventory.get(i));
        }
    }

    public void seeDescription(int itemID) {
        System.out.println(Item.getDesc(itemID));
    }
}
