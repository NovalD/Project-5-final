import java.util.Scanner;
/**
 * Write a description of class Person here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Person
{
    private String name;

    protected int healthMax;
    protected int manaMax;
    protected int powerMax;

    protected int healthA;
    protected int manaB;
    protected int powerC;

    
    protected Choice fireball;
    protected Choice thunder;
    protected Choice healing;
    protected Choice attack;

    
    protected boolean alive;
    protected boolean isKnockedOut;
    

    public Person(String name, int healthMax, int manaMax, int powerMax) {
        this.name = name;
        this.healthMax = healthMax;
        this.manaMax = manaMax;
        this.powerMax = powerMax;

        this.healthA = healthMax;
        this.manaB = manaMax;
        this.powerC = powerMax;

        
        attack = new Choice(this, "Attack", 1, 1, 1);
        fireball = new Choice(this, "Fireball", 2, 1, 1);
        thunder = new Choice(this, "Thunder", 1, 0.5, 2);
        healing = new Choice(this, "Healing", 1, 2, 3);
        
        
        
        alive = true;
        isKnockedOut = false;
        
    }

    @Override 
    public String toString() {
        String text = "";
        text += name + "\n";
        text += "Life:  " + healthA + " / " + healthMax + "\n";
        text += "Mana:  " + manaB + " / " + manaMax + "\n";
        text += "Power: " + powerC + " / " + powerMax + "\n";
        return text; 
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return healthA;
    }

    public int getHealthMax() {
        return healthMax;
    }

    public int getMana() {
        return manaB;
    }

    public int getManaMax() {
        return manaMax;
    }

    public int getPower() {
        return powerC;
    }

    public boolean isKnockedOut() {
        if(isKnockedOut) {
            UF.println(name + " is knocked out and can't cast skills this round\n", 1000);
            isKnockedOut = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void sustainDamage(int value) {
        if(value > 0) {
            healthA -= value;
            UF.println(name + " has gotten " + value + " damage\n", 1000);
        }

        if (healthA <= 0) {
            alive = false;
        }
    }

    public boolean useMana(int value) {
        if(value <= manaB) {
            manaB -= value;
            UF.println(name + " has used " + value + " mana.", 1000);
            return true;
        } else {
            UF.println(name + " has not enough mana.", 1000);
            return false;
        }
    }

    public void cureLife(int value) {
        int difference = healthMax - healthA;
        if(value >= difference) {
            healthA = healthMax;
            UF.println(name + " is fully healed.", 1000);
        } else {
            healthMax += value;
            UF.println(name + " has healed " + value + " life points.", 1000);
        }
    }

    public void castAttack(Person enemy) {
        UF.println(name + " casts " + attack.getName() + " on " + enemy.getName() + ".", 1000);
        if(useMana(attack.getManaRequirement())) {
            enemy.sustainDamage(attack.getValue());
        }
    }
    
    public void castFireball(Person enemy) {
        UF.println(name + " casts " + fireball.getName() + " on " + enemy.getName() + ".", 1000);
        if(useMana(fireball.getManaRequirement())) {
            enemy.sustainDamage(fireball.getValue());
        }
    }

    public void castHealing() {
        UF.println(name + " casts " + healing.getName() + ".", 1000);
        if(useMana(healing.getManaRequirement())) {
            cureLife(healing.getValue());
        }
    }

    public void castThunder(Person enemy) {
        UF.println(name + " casts " + thunder.getName() + " on " + enemy.getName() + ".", 1000);
        if(useMana(thunder.getManaRequirement())) {
            enemy.sustainDamage(thunder.getValue());
            enemy.isKnockedOut = true;
            UF.println(enemy.getName() + " is knocked out.", 1000);
        }
    }
}
