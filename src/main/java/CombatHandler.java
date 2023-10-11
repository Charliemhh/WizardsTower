import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CombatHandler {
    private Player player;

    private Enemy enemy;

    private boolean combatOver = false;

    public void combatRound(Player player, Enemy enemy) {
        ArrayList<Character> turnOrder = getTurnOrder(player, enemy);
        this.player = player;
        this.enemy = enemy;
        while (!combatOver) {
            for (Character actor : turnOrder) {
                if (actor.getClass() == Player.class) {
                    displayPlayerOptions();
                } else {
                    makeAttack(enemy, player);
                }
            }
            deadCheck();
        }

    }

    private void deadCheck() {
        if (player.getIsDead()) {
            System.out.println(player.getName() + " has died! What a shame.");
            combatOver = true;
        }
        if (enemy.getIsDead()) {
            System.out.println("The " + enemy.getName() + " dies, victory is yours!");
            combatOver = true;
        }
    }

    private void makeAttack(Character attacker, Character target) {
        System.out.println(attacker.getName() + getAttackFlavor(attacker.getAttackDam()[1]) + target.getName() + "for " + attacker.getAttackDam()[0] + " !");
        target.setCurrentHP(target.getCurrentHP() - attacker.getAttackDam()[0]);
        System.out.println("\n Leaving " + target.getName() + " at " + target.getCurrentHP() + " !");
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
        option = scanner.nextInt();
        //System.out.println("3: Try to escape!");
        while (true) {
            if (option >= 1 && option <= 3) {
                break;
            } else {
                System.out.println("Dear hero, please enter a valid menu option.");
            }
        }
        if (option == 1) {
            makeAttack(player, enemy);
        }

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
}
