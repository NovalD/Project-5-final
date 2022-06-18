
/**
 * Write a description of class Game here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Game
{
    private Player player;
    private NPC enemy;

    private int score;

    public Game() {
        player = new Player("Player", 20, 10, 5);
        enemy = new NPC("Enemy", 20, 10, 5);
    }

    public boolean fight() {
        
        
        UF.println(player.getName() + " fights against " + enemy.getName(), 1000);
        int rounds = 1;

        while(player.getHealth() > 0 && enemy.getHealth() > 0) {
            UF.println(" --- Round " + rounds + " --- \n", 1000);
            UF.println(player.getName() + " Stats", 0);
            UF.println("Life: " + UF.displayGraphical(player.getHealth(), player.getHealthMax(), "#"), 0);
            UF.println("Mana: " + UF.displayGraphical(player.getMana(), player.getManaMax(), "@") + "\n", 1000);
            UF.println(enemy.getName() + " Stats", 0);
            UF.println("Life: " + UF.displayGraphical(enemy.getHealth(), enemy.getHealthMax(), "#"), 0);
            UF.println("Mana: " + UF.displayGraphical(enemy.getMana(), enemy.getManaMax(), "@") + "\n", 1000);
            
            if(player.getHealth() <= 0) break;
            player.chooseSkill(enemy);
            if(enemy.getHealth() <= 0 || player.getHealth() <= 0) break;
            
            if(enemy.getHealth() <= 0) break;
            enemy.chooseSkill(player);
            rounds++;
        }

        if(player.getHealth() > 0 && enemy.getHealth() <= 0) {
            UF.println("YOU HAVE WON! YOU ARE NOW STRONGER", 2000);
            UF.println("PREPARE FOR THE NEXT FIGHT!\n", 2000);
            score++;
            enemy.getStronger(1.2);
            player.getStronger();
            return true;
        } else {
            UF.println("Game over!", 0);
            UF.println("You reached " + score + " Points. Gratulation!", 2000);
            return false;
        }
    }

    public void mainLoop() {
        while(fight());
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.mainLoop();
    }
}
