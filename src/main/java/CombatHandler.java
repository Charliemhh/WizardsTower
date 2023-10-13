import java.util.*;
import java.util.stream.Stream;

public class CombatHandler {

    private Player player;

    private Enemy enemy;

    private ArrayList<Enemy> enemies = new ArrayList<>();

    private boolean combatOver = false;

    private ArrayList<Character> turnOrder = new ArrayList<>();


    public void combatRound(Player player, Enemy enemy) {
        System.out.println("The " + enemy.getName() + " lurches towards you, prepare to fight!");
        this.player = player;
        this.enemy = enemy;
        turnOrder = getTurnOrder(player, enemy);
        while (!combatOver) {
            for (Character actor : turnOrder) {
                if (actor.getClass() == Player.class) {
                    displayPlayerOptions();
                    deadCheck();
                    if (combatOver) {
                        break;
                    }
                } else {
                    makeAttack(enemy, player);
                    deadCheck();
                }
            }
        }
    }

    public void combatRound(Player player, ArrayList<Enemy> enemies) {
        this.player = player;
        this.enemies = enemies;
        System.out.println("The vile forces of darkness lurch towards you, prepare to fight!");
        nameEnemies();
        while (!combatOver) {
            turnOrder = getTurnOrder(player, enemies);
            for (int i = 0; i < turnOrder.size(); i++) {
                if (turnOrder.get(i).getClass() == Player.class) {
                    displayPlayerOptions();
                    deadCheck();
                    if (combatOver) {
                        break;
                    }
                } else {
                    makeAttack(turnOrder.get(i), player);
                    deadCheck();
                }
            }
        }
    }


    private void deadCheck() {
        if (player.getIsDead()) {
            System.out.println(player.getName() + " has died! What a shame.\n");
            combatOver = true;
        }
        if (!enemies.isEmpty()) {
            for (int i = 0; i < enemies.size(); i++) {
                if (enemies.get(i).getIsDead()) {
                    allEnemiesDeadCheck();
                    if (enemies.isEmpty()) {
                        break;
                    }
                }

            }
        } else {
            if (enemy.getIsDead()) {
                System.out.println("The " + enemy.getName() + " dies, victory is yours!");
                player.addXP(enemy.getRewardXP());
                System.out.println("You gain " + enemy.getRewardXP() + "XP!\n");
                combatOver = true;
            }
        }

    }

    private void allEnemiesDeadCheck() {
        Boolean[] deadList = new Boolean[enemies.size()];
        Enemy deadToRemove = null;
        int i = 0;
        for (Enemy e : enemies) {
            if (e.getIsDead()) {
                System.out.println("The " + e.getName() + " dies");
                player.addXP(e.getRewardXP());
                System.out.println("You gain " + e.getRewardXP() + "XP!\n");
                deadList[i] = true;
                deadToRemove = e;
            } else {
                deadList[i] = false;
            }
            i++;
        }
        if (Stream.of(deadList).allMatch(Boolean::valueOf)) {
            combatOver = true;
            System.out.println("The enemies are dead, victory is yours!\n");
        }
        if (deadToRemove != null) {
            enemies.remove(deadToRemove);
            turnOrder.remove(deadToRemove);
        }

    }

    private void makeAttack(Character attacker, Character target) {
        if (attacker.getClass() == Player.class) {
            int[] attackResults = attacker.getAttackDam();
            String mainWeapon = player.getEquipment().getWeaponName();
            System.out.println(attacker.getName() + getAttackFlavor(attackResults[1]) +
                    target.getName() + " with their " + mainWeapon +
                    " for " + attackResults[0] + " damage!");
            target.setCurrentHP(target.getCurrentHP() - (attacker.getAttackDam()[0]));
        } else {
            System.out.println(attacker.getName() + getAttackFlavor(attacker.getAttackDam()[1]) +
                    target.getName() + " for " + (attacker.getAttackDam()[0] - player.getDefenceBonus()) + " damage!");
            target.setCurrentHP(target.getCurrentHP() - (attacker.getAttackDam()[0] - player.getDefenceBonus()));
        }
        if (target.getCurrentHP() < 0) {
            target.setCurrentHP(0);
        }
        System.out.println("Leaving " + target.getName() + " at " + target.getCurrentHP() + "/" + target.getMaxHP() + " HP!\n");
    }

    private String getAttackFlavor(int i) {
        //could be randomized later
        switch (i) {
            case 0:
                return " attacks ";
            case 1:
                return " viciously insults ";
            case 2:
                return " blasts ";
            case 3:
                return " ambushes ";
        }
        return null;
    }

    private void displayPlayerOptions() {
        int option;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your action hero!");
        System.out.println("1: Attack");
        System.out.println("2: Use an Item");
        //System.out.println("3: Try to escape!");
        while (true) {
            try {
                option = scanner.nextInt();
                if (option >= 1 && option <= 3) {
                    break;
                } else {
                    System.out.println("Dear hero, please enter a valid menu option.");
                }
            } catch (InputMismatchException err) {
                System.out.println("Please only enter an available option");
                scanner.nextInt();
            }
        }
        switch (option) {
            case 1:
                if (!enemies.isEmpty()) {
                    makeAttack(player, selectTarget(enemies));
                } else {
                    makeAttack(player, enemy);
                }
                break;
            case 2:
                int i = 0;
                while (!(player.getInventory().findItem(i) == null) && (i == 0)) {
                    try {
                        i = 0;
                        System.out.println("Choose an item to use:");
                        player.getInventory().seeInventory();
                        i = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Please select a valid option!");
                        scanner.next();
                    }
                }
                Item selectedItem = player.getInventory().getPlayerInventory().get(i - 1);
                if (!enemies.isEmpty()) {
                    player.getInventory().useItem(selectedItem.getItemID(), player, enemies);
                } else {
                    player.getInventory().useItem(selectedItem.getItemID(), player, enemy);
                }

        }

    }

    private void nameEnemies() {
        int i = 1;
        for (Enemy e : enemies) {
            e.setName(e.getName() + " " + i);
            i++;
        }
    }

    public static Enemy selectTarget(ArrayList<Enemy> enemies) {
        int i = 1;
        int option = 0;
        Scanner scanner = new Scanner(System.in);
        for (Enemy e : enemies) {
            System.out.println(i + ":" + e.getName());
            i++;
        }
        System.out.println("Please select a target: ");
        option = scanner.nextInt() - 1;
        while (!(option > 0) && !(option < enemies.size())) {
            System.out.println("Please select a valid target: ");
            option = scanner.nextInt() - 1;
        }
        return enemies.get(option);
    }

    private static ArrayList<Character> getTurnOrder(Player player, Enemy enemy) {
        ArrayList<Character> combatOrder = new ArrayList<>();
        if (player.getStatBlock().getLitheness() > enemy.getStatBlock().getLitheness()) {
            combatOrder.add(player);
            combatOrder.add(enemy);
        } else if (player.getStatBlock().getLitheness() < enemy.getStatBlock().getLitheness()) {
            combatOrder.add(enemy);
            combatOrder.add(player);
        } else {
            Random randomNum = new Random();
            int result = randomNum.nextInt(2);
            if (result == 1) {
                combatOrder.add(player);
                combatOrder.add(enemy);
            } else {
                combatOrder.add(enemy);
                combatOrder.add(player);
            }
        }
        return combatOrder;
    }

    private static ArrayList<Character> getTurnOrder(Player player, ArrayList<Enemy> enemies) {
        ArrayList<Character> combatOrder = new ArrayList<>();
        //Groups enemy to the speed of the first for now, later could do individually
        if (player.getStatBlock().getLitheness() > enemies.get(0).getStatBlock().getLitheness()) {
            combatOrder.add(player);
            combatOrder.addAll(enemies);

        } else if (player.getStatBlock().getLitheness() < enemies.get(0).getStatBlock().getLitheness()) {
            combatOrder.addAll(enemies);
            combatOrder.add(player);
        } else {
            Random randomNum = new Random();
            int result = randomNum.nextInt(2);
            if (result == 1) {
                combatOrder.add(player);
                combatOrder.addAll(enemies);
            } else {
                combatOrder.addAll(enemies);
                combatOrder.add(player);
            }
        }
        return combatOrder;
    }
}
