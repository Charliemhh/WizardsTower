import java.util.ArrayList;

public class GameRoom {
    private final int roomIndex; //Matches with index number from GameMap

    private String shortDesc; //Used when entering

    private String longDesc; //Used for examining

    private ArrayList<String> passageLabels; //Text applied to possible pathways

    private ArrayList<Integer> enemyInRoom; // ID of enemy in the room

    private ArrayList<Integer> trapInRoom; // ID of trap in the room

    public GameRoom(int roomIndex){
        this.roomIndex = roomIndex;
    }
    public GameRoom(int roomIndex, ArrayList<Integer> enemyInRoom, ArrayList<Integer> trapInRoom){
        this.roomIndex = roomIndex;
        this.enemyInRoom = enemyInRoom;
        this.trapInRoom = trapInRoom;
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
    public ArrayList<String> getPassageLabels() {
        return passageLabels;
    }

    public void setPassageLabels(ArrayList<String> passageLabels) {
        this.passageLabels = passageLabels;
    }
}
