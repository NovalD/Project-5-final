import java.util.concurrent.ThreadLocalRandom;
/**
 * Write a description of class NPC here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NPC extends Person
{
    public NPC(String name, int lifeMax, int manaMax, int powerMax) {
        super(name, lifeMax, manaMax, powerMax);
    }

    
    public void chooseSkill(Person enemy) {
        if(isKnockedOut()) {
            return;
        }

        if (manaB == 0) {
            healthA = 0;
            UF.println(getName() + " gives up because he has no mana.", 1000);
            return;
        }

        int decision;
        while(true) {
            decision = ThreadLocalRandom.current().nextInt(0, 4);

            if((decision == 1 && healing.getManaRequirement() > manaB) || (decision == 1 && healthMax == healthA) || (decision == 2 && thunder.getManaRequirement() > manaB)) {
                continue;
            } else {
                break;
            }
        }

        if(decision == 0) {
            castFireball(enemy);
        } else if (decision == 1) {
            castHealing();
        } else if (decision == 2) {
            castThunder(enemy);
        }
    }

    public void getStronger(double factor) {
        healthMax *= factor;
        manaMax *= factor;
        manaMax += 3;
        powerMax += factor;

        healthA = healthMax;
        manaB = manaMax;
        powerC = powerMax;

        isKnockedOut = false;
        
    }
}
