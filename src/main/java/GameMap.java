import java.util.ArrayList;
import java.util.LinkedList;

public class GameMap {
    //Class representing the connections between rooms.
    private final ArrayList<LinkedList<Integer>> Map;

    private int currentRoomIndex;

    public GameMap(int numberOfRooms, int[][] connections) {
        ArrayList<LinkedList<Integer>> Map = new ArrayList<>();
        for (int i = 0; i < numberOfRooms; i++) {
            Map.add(new LinkedList<>());
            for (int con = 0; con < connections[i].length; con++) {
                Map.get(i).add(connections[i][con]);
            }
        }
        this.Map = Map;
        this.currentRoomIndex = 0; //Set to first room by default
        //the above loops create a new linked list for each room, then fill said list
        //with the "adjacent" rooms by adding to linked list.
    }

    public ArrayList<LinkedList<Integer>> getMap() {
        return this.Map;
    }

    public int getCurrentRoomIndex() {
        return currentRoomIndex;
    }

    public void setCurrentRoomIndex(int currentRoomIndex) {
        this.currentRoomIndex = currentRoomIndex;
    }

    public LinkedList<Integer> getAdjacentRooms() {
        return this.Map.get(this.currentRoomIndex);
    }
}
