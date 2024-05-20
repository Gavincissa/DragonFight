import java.util.*;
public class Dragon {
    static Random rand = new Random();

    static int hp;
    static int level;
    static int damage;



    public Dragon() {
        hp = 50;
        level = 0;
    }

    public int damage(){
        return ((rand.nextInt(7) + (level*2))) + (level*2);
    }


    public void attack(Player player,Giant giant) {
        if (hit(player)) {
            int netDamage = damage() - player.defense;
            if(netDamage < 0){
                netDamage = 0;
            }
            System.out.println(message(netDamage) + "\n");
            if(Player.hasGiant){
                int giantEffect = giant.calculateEffect();
                netDamage -= giantEffect;
                System.out.println("Your Gentle Giant has mitigated " + giantEffect + " damage for you!");
            }
            if(netDamage < 0){
                netDamage = 0;
            }
            Player.currentHP -= netDamage;

        } else {
            System.out.println("The dragon missed his attack!\n");
        }

    }

    public boolean hit(Player player) {
        if ((rand.nextInt(11) + 1) >= player.agility) {
            return true;
        } else {
            return false;
        }
    }

    public String message(int dmg) {
        int num = rand.nextInt(3) + 1;
        String message = " ";
        switch (num) {
            case 1:
                message = "The dragon breathes fire and deals " + dmg + " damage!";
                break;
            case 2:
                message = "The dragon tail whips you for " + dmg + " damage!";
                break;
            case 3:
                message = "The dragon stomps on you for " + dmg + " damage!";
                break;
            case 4:
                message = "The dragon uses sonic boom and deals " + dmg + " damage!";
        }
        return message;
    }

    public boolean isDead() {
        if (hp <= 0) {
            return true;
        } else {
            return false;
        }
    }
}