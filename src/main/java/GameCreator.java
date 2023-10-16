import Characters.Player;
import Game.ExplorationHandler;
import Game.GameMap;
import Game.GameRoom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameCreator {


    public static void main(String[] args) throws InterruptedException {
        GameMap gameMap = createGameMap();
        ArrayList<GameRoom> gameRooms = populateGameMap(gameMap);
        System.out.println("Welcome to Wizard's Tower!");
        Player player = createYourCharacter();
        welcomeMessage(player);
        ExplorationHandler explorationHandler = new ExplorationHandler(player, gameMap, gameRooms);
        Thread.sleep(700);
        explorationHandler.ExplorationStart();

    }

    private static Player createYourCharacter() {
        Scanner scanner = new Scanner(System.in);
        int selectedClass = 0;
        System.out.println("Please enter your name: ");
        String name = scanner.next();
        System.out.println("Please select your class: ");
        System.out.println("1: Squire's Assistant");
        System.out.println("2: Clumsy Thief");
        System.out.println("3: Trainee Wizard");
        System.out.println("4: Mild-Mannered Accountant");
        System.out.println("5: Sarcastic So-And-So");
        while (true) {
            try {
                int option = scanner.nextInt();
                option -= 1;
                if (option >= 0 && option < 6) {
                    selectedClass = option;
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please make a valid selection: ");
                scanner.next();
            }
        }
        return new Player.PlayerBuilder(name, selectedClass).build();
    }

    private static ArrayList<GameRoom> populateGameMap(GameMap gameMap) {
        ArrayList<GameRoom> gameRooms = new ArrayList<>();
        for (int i = 0; i < gameMap.getMap().size(); i++) {
            GameRoom gameRoom = new GameRoom(i);
            gameRooms.add(i, gameRoom);
        }
        gameRooms = addRoomDetails(gameRooms);
        return gameRooms;
    }

    private static GameMap createGameMap() {
        int[][] connections = {{1}, {0, 2, 3}, {1, 5}, {1, 4, 5, 6}, {3}, {2, 3, 8}, {3, 9, 10},
                {8}, {5, 7, 9}, {6, 8, 10, 11}, {6, 9}, {9, 12}, {11}};
        return new GameMap(13, connections);
    }

    private static void welcomeMessage(Player player) {
        System.out.println("Welcome " + player.getName() + " to the wizard's tower!");
        System.out.println(" The great wizard Bumble-Guff has called for help" +
                " the king has tasked you with climbing his tower with the goal of saving him.\n " +
                "Sadly for you the king doesn't much like bumble-guff after an unfortunate incident " +
                "at the castle.\n So, rather than someone competent, you’ve been sent." +
                "\nThe creatures and traps lying in wait will confound even the most intrepid of adventurers," +
                " so be careful!\n\n");
    }

    private static ArrayList<GameRoom> addRoomDetails(ArrayList<GameRoom> gameRooms) {
        var populatedGameRooms = new ArrayList<GameRoom>();
        for (int i = 0; i < gameRooms.size(); i++) {
            GameRoom gameRoom = gameRooms.get(i);
            var roomTrapArray = new ArrayList<Integer>();
            var roomEnemyArray = new ArrayList<Integer>();
            var roomThingsArray = new ArrayList<GameRoom.RoomItems>();
            switch (gameRoom.getRoomIndex()) {
                case 0://Entrance
                    gameRoom.setShortDesc("the entrance-way to Bumble-Guff's tower!");
                    gameRoom.setLongDesc("The only exit was sealed behind you by guards as you entered," +
                            " they must have high hopes for you!" + "\nThe room smells vaguely like floor " +
                            "cleaner and wizardry");
                    roomThingsArray.add(0, new GameRoom.RoomItems(GameRoom.RoomItemTypes.ITEM
                            , 0, 3, "You find a helpful bundle of supplies!"));
                    break;
                case 1://Welcoming Hall
                    gameRoom.setShortDesc("a large welcoming hall!");
                    gameRoom.setLongDesc("It's full of unanswered post and " +
                            "still-sealed editions of Wizard's Quarterly, " +
                            "Looks like Bumble-Guff hasn't tidied up in a while.");
                    roomEnemyArray.add(0);
                    roomEnemyArray.add(5);
                    roomThingsArray.add(0, new GameRoom.RoomItems(GameRoom.RoomItemTypes.ITEM,
                            1, 2, "You find some fairly sharp letter openers!"));
                    break;
                case 2://Waiting Room
                    roomTrapArray.add(0);
                    gameRoom.setShortDesc("a small cozy waiting room!");
                    gameRoom.setLongDesc("Sadly for you, the magical furniture union is on strike," +
                            "leaving only a single weary scab stool to rest your behind on...");
                    break;
                case 3://Storage Room
                    roomEnemyArray.add(1);
                    roomEnemyArray.add(5);
                    gameRoom.setShortDesc("a storage room!");
                    gameRoom.setLongDesc("It's full of tins of beans, painful memories and crushed hopes(tm)" +
                            "It's probably not worth stuffing your pockets with any of this stuff");
                    break;
                case 4://Cold Storage
                    gameRoom.setShortDesc("a giant walk-in freezer!");
                    gameRoom.setLongDesc("Still filled with ready meals that are several decades out of date," +
                            "questionable meat and some sort of prehistoric dwarf encased in ice - A small " +
                            "affixed nameplate reads: Grug - died as he lived, filthy and covered in lice");
                    roomEnemyArray.add(6);
                    roomEnemyArray.add(0);
                    roomThingsArray.add(0, new GameRoom.RoomItems(GameRoom.RoomItemTypes.EQUIPMENT,
                            14, 0,
                            "You find a sword awkwardly jutting out of the ice."));
                    break;
                case 5://Cloak Room
                    gameRoom.setShortDesc("a giant cloak room, smells disgusting!");
                    gameRoom.setLongDesc("You're on a rescue mission, " +
                            "not a 'pilfer every pocket you come across' mission. Have some self-respect.");
                    roomThingsArray.add(0, new GameRoom.RoomItems(GameRoom.RoomItemTypes.ITEM, 3, 2,
                            "One of the coats must have belonged to a clown," +
                                    " you find some spare joke books!"));
                    break;
                case 6://Kitchen
                    gameRoom.setShortDesc("a kitchen!");
                    gameRoom.setLongDesc("Your hopes of a non-monstrous meal are dashed as soon " +
                            "as you see the lack of staff. You just can't get the help these days!");
                    roomThingsArray.add(0, new GameRoom.RoomItems(GameRoom.RoomItemTypes.ITEM, 0
                            , 3, "Some emergency first aid potions catch your eye!"));
                    roomEnemyArray.add(3);
                    roomEnemyArray.add(5);
                    break;
                case 7://Hidden Cell
                    gameRoom.setShortDesc("a tiny hidden cell.");
                    gameRoom.setLongDesc("It's dank, dark and has a mold problem. Basically the perfect" +
                            "environment for most kinds of dwarf and micro-hippopotamus.");
                    roomThingsArray.add(0, new GameRoom.RoomItems(GameRoom.RoomItemTypes.EQUIPMENT, 15
                            , 0, "Under the bed you find something sharp and sneaky"));
                    break;
                case 8://Guest Prison
                    gameRoom.setShortDesc("a bank of prison cells, all marked - Guest.");
                    gameRoom.setLongDesc("The walls are slick with slime, the guest book hasn't had a signature in" +
                            "over a year. Last entry came from a frost gnome called 'I.C Weiner'");
                    roomEnemyArray.add(0);
                    roomEnemyArray.add(2);
                    roomTrapArray.add(1);
                    break;
                case 9://Dining Room
                    gameRoom.setShortDesc("a luxurious dining room!");
                    gameRoom.setLongDesc("The tables are still laid, sadly the fruit in the fruit bowls are" +
                            "all plastic.");
                    roomEnemyArray.add(5);
                    roomEnemyArray.add(5);
                    roomEnemyArray.add(4);
                    break;
                case 10://Smoking area
                    gameRoom.setShortDesc("a large stone balcony, the floor is littered with old pipe ash.");
                    gameRoom.setLongDesc("Looking out over the horizon, you can see the rest of the kingdom," +
                            " the cool breeze reminds you. \nTaking a moment to relax and heal isn't so bad." +
                            "However, looking up the tower looms above you," +
                            " even when you find the stairs theres a long way to go!");
                    break;
                case 11://Strange Living Room
                    gameRoom.setShortDesc("a suspiciously normal seeming living room.");
                    gameRoom.setLongDesc("The furniture seems comfortable, the fire crackles. It makes you feel vaguely" +
                            " sleepy. Better keep moving or you'll need to use the awaken-rods again.");
                    break;
                case 12://Boss Room/Stairwell
                    gameRoom.setShortDesc("the stairwell, you could get to the next floor from here!");
                    gameRoom.setLongDesc("Hopefully the folks in the seven-hells kitchen don't send their goons after you.");
                    roomEnemyArray.add(7);
                    break;

            }
            gameRoom.setThingsInRoom(roomThingsArray);
            gameRoom.setTrapInRoom(roomTrapArray);
            gameRoom.setEnemyInRoom(roomEnemyArray);
            getPassageLabels(gameRoom, i);
            populatedGameRooms.add(gameRoom.getRoomIndex(), gameRoom);
        }
        return populatedGameRooms;
    }

    private static void getPassageLabels(GameRoom gameRoom, int index) {
        var passageMap = new HashMap<Integer, String>();
        switch (index) {
            case 0://Entrance
                passageMap.put(1, "A great tall doorway leading further into the tower.");
                gameRoom.setPassageLabels(passageMap);
                break;
            case 1://Welcoming Hall
                passageMap.put(0, "The great doorway behind you leads back to the still-sealed entrance.");
                passageMap.put(2, "A warm glow of light is shining through a dark corridor.");
                passageMap.put(3, "A smell of mildew and sawdust emanates through a simple wooden door.");
                gameRoom.setPassageLabels(passageMap);
                break;
            case 2://Waiting Room
                passageMap.put(1, "The corridor back to the welcoming hall.");
                passageMap.put(5, "A small un-assuming door, the air is thick with mothballs.");
                gameRoom.setPassageLabels(passageMap);
                break;
            case 3://Storage Room
                passageMap.put(1, "The corridor back to the welcoming hall");
                passageMap.put(4, "You begin to shiver just at the sight of this magical ice door.");
                passageMap.put(5, "A small crawlspace seems to lead elsewhere, Hope you're not claustrophobic.");
                passageMap.put(6, "The dwarf-steel double doors fill you with an intense feeling of anxiety.");
                gameRoom.setPassageLabels(passageMap);
                break;
            case 4://Cold Storage
                passageMap.put(3, "The passageway leading back to food storage");
                gameRoom.setPassageLabels(passageMap);
                break;
            case 5://Cloak Room
                passageMap.put(2, "The passage way leading back to the unionized waiting room");
                passageMap.put(3, "A small crawlspace, you're not really considering it are you?");
                passageMap.put(8, "Behind several racks of coats you find a hidden door!");
                gameRoom.setPassageLabels(passageMap);
                break;
            case 6://Kitchen
                passageMap.put(3, "The passage way back to the store room, a path trodden by many a chef");
                passageMap.put(9, "Double doors with round panes in the top-half," +
                        " seem to lead to where food was once served");
                passageMap.put(10, "A small side door with 'No Smoking' proudly decorating the nearby wall");
                gameRoom.setPassageLabels(passageMap);
                break;
            case 7://Hidden Cell
                passageMap.put(8, "The small rusted gate to the guest prison, just what was Bumble-Guff up to?");
                gameRoom.setPassageLabels(passageMap);
                break;
            case 8://Guest Prison
                passageMap.put(5, "The once hidden doorway back to the musky cloakroom.");
                passageMap.put(7, "You catch a small loose grate out of the corner of your eye.");
                passageMap.put(9, "A lever connected to a hidden entrance with light spilling " +
                        "from the gaps between the wooden panels," +
                        "such shoddy workmanship. Must have been cowboy goblins.");
                gameRoom.setPassageLabels(passageMap);
                break;
            case 9://Dining Room
                passageMap.put(6, "The double doors leading into the kitchen.");
                passageMap.put(8, "The facade is shoddy, The hidden door budget must have been cut.");
                passageMap.put(10, "A door leading to some sort of outside balcony.");
                passageMap.put(11, "An unassuming door leading deeper into the tower.");
                gameRoom.setPassageLabels(passageMap);
                break;
            case 10://Smoking Area
                passageMap.put(6, "A staff only door.");
                passageMap.put(9, "A door leading into the dining area.");
                gameRoom.setPassageLabels(passageMap);
                break;
            case 11://Strange Living Room
                passageMap.put(9, "Maybe this isn't such a good idea, back to the dining area");
                passageMap.put(12, "Push onwards, that stupid wizard isn't gonna rescue himself!");
                gameRoom.setPassageLabels(passageMap);
                break;
            case 12://Boss Room / Stairwell
                passageMap.put(11, "The rest of the floor, is there something you missed?");
                gameRoom.setPassageLabels(passageMap);
                break;
        }
    }

}
