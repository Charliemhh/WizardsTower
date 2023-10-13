import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameRoom {
    private final int roomIndex; //Matches with index number from GameMap

    private String shortDesc; //Used when entering

    private String longDesc; //Used for examining

    private HashMap<Integer, String> passageLabels; //Text applied to possible pathways

    private ArrayList<Integer> enemyInRoom; // ID of enemy in the room

    private ArrayList<Integer> trapInRoom; // ID of trap in the room

    private ArrayList<RoomItems> thingsInRoom;

    public GameRoom(int roomIndex) {
        this.roomIndex = roomIndex;
    }

    public GameRoom(int roomIndex, ArrayList<Integer> enemyInRoom, ArrayList<Integer> trapInRoom) {
        this.roomIndex = roomIndex;
        this.enemyInRoom = enemyInRoom;
        this.trapInRoom = trapInRoom;
        this.passageLabels = new HashMap<>();
    }

    public int getRoomIndex() {
        return roomIndex;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public ArrayList<Integer> getEnemyInRoom() {
        return enemyInRoom;
    }

    public ArrayList<Integer> getTrapInRoom() {
        return trapInRoom;
    }

    public void setTrapInRoom(ArrayList<Integer> trapInRoom) {
        this.trapInRoom = trapInRoom;
    }

    public void setEnemyInRoom(ArrayList<Integer> enemyInRoom) {
        this.enemyInRoom = enemyInRoom;
    }

    public HashMap<Integer, String> getPassageLabels() {
        return passageLabels;
    }

    public void setPassageLabels(HashMap<Integer, String> passageLabels) {
        this.passageLabels = passageLabels;
    }

    public ArrayList<RoomItems> getThingsInRoom() {
        return thingsInRoom;
    }

    public void setThingsInRoom(ArrayList<RoomItems> thingsInRoom) {
        this.thingsInRoom = thingsInRoom;
    }


    private void roomitemPickUp(RoomItems roomItems) {

    }

    enum RoomItemTypes {
        EQUIPMENT, ITEM;
    }

    public static class RoomItems {
        private final RoomItemTypes type;

        private final int itemID;

        private final int quantity;

        private final String inspectDescription;

        public RoomItems(RoomItemTypes type, int itemID, int quantity, String inspectDescription) {
            this.type = type;
            this.itemID = itemID;
            this.quantity = quantity;
            this.inspectDescription = inspectDescription;
        }

        public RoomItemTypes getType() {
            return type;
        }

        public int getItemID() {
            return itemID;
        }

        public int getQuantity() {
            return quantity;
        }

        public String getInspectDescription() {
            return inspectDescription;
        }

    }
}
