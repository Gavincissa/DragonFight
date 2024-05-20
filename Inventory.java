import java.util.Random;

public class Inventory {

    int gold;
    int fireball;
    int lightning;
    int poison;
    int smallHeal;
    int largeHeal;

    public Inventory(){
        gold = 0;
        if(Player.name.equalsIgnoreCase("fart nugget")){
            gold = 10000;
        }
        fireball = 0;
        lightning = 0;
        poison = 0;
        smallHeal = 0;
        largeHeal = 0;
        if(Player.name.equals("magician")){
            fireball = 1;
            lightning = 1;
            poison = 1;
            smallHeal = 1;
            largeHeal = 1;
        }
    }

    public void addGold(){
        Random rand = new Random();
        gold += (rand.nextInt(60) + 70) * Dragon.level;
    }

    public void removeGold(int amount){
        gold -= amount;
    }

    public void addFireball(){
        System.out.println("Fireball purchased!\n");
        fireball += 1;
    }

    public void addLightning(){
        System.out.println("Lightning purchased!\n");
        lightning += 1;
    }

    public void addPoison(){
        System.out.println("Poison purchased!\n");
        poison += 1;
    }

    public void addSmallHeal(){
        System.out.println("Small health potion purchased!\n");
        smallHeal += 1;
    }

    public void addLargeHeal(){
        System.out.println("Large health potion purchased!\n");
        largeHeal += 1;
    }
}
