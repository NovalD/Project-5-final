import java.util.Scanner;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player extends Person
{
    private static Scanner input = new Scanner(System.in);

    public Player(String name, int lifeMax, int manaMax, int powerMax) {
        super(name, lifeMax, manaMax, powerMax);
    }   

    public void chooseSkill(Person enemy) {
        if(!isKnockedOut()) {
            System.out.println("[1] Attack");
            System.out.println("[2] Fireball");
            System.out.println("[3] Thunder");
            System.out.println("[4] Healing");
            System.out.println("[5] Give up");
            System.out.print("Input: ");
            String command = input.next();

            if(command.equals("1")) {
                castAttack(enemy);
            } else if (command.equals("2")) {
                castFireball(enemy);
            } else if (command.equals("3")) {
                castThunder(enemy);
            } else if (command.equals("4")) {
                castHealing();
            } else if (command.equals("5")) {
                healthA = 0;
            }
        }
    }

    public void getStronger() {
        healthMax += 5;
        manaMax += 2;
        powerMax += 1;

        healthA = healthMax;
        manaB = manaMax;
        powerC = powerMax;

        isKnockedOut = false;
    }
}
