import java.util.*;
public class Player {
    Random rand = new Random();

    int skillPoints = 9;
    int attack;
    int defense;
    static int baseHP;
    static int currentHP;
    int agility;
    int magic;
    static String name;
    boolean stun;
    static int poisonCount;
    static boolean hasSkunk = false;
    static boolean hasFairy = false;
    static boolean hasGiant = false;

    public Player(){

    }

    public Player(String name, int hp, int attack, int defense, int agility, int magic){
        baseHP = hp;
        this.attack = attack;
        this.defense = defense;
        this.agility = agility;
        this.magic = magic;
        Player.name = name;
    }

    public int damage(Weapon wpn, int attackID){
        return ((rand.nextInt(3) + 2) + (wpn.damage*3)) + (attack*3) + (attackID*4);
    }

    public boolean hit(Weapon wpn, int attackID){
        return ((rand.nextInt(100)) + (attackID * 15)) <= ((wpn.speed * 7) + 55);
    }

    public void move(Weapon wpn, Inventory inv, Spell fireball, Spell lightning, Poison poison, Health heal, Skunk skunk, Fairy fairy) {
        Scanner scan = new Scanner(System.in);
        boolean repeat;
        int attackID = 0;
        do {
            repeat = false;
            stun = false;

            attackMenu(inv, fireball, lightning, poison);

            try {
                attackID = scan.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Please enter a valid integer\n");
                scan.next();
            }

            if (attackID > 0 && attackID < 5 && hit(wpn, attackID)) {
                int damage = damage(wpn, attackID);
                Dragon.hp -= damage;
                System.out.println(message(damage, attackID, wpn.name));
            }

            else if (attackID == 5) {
                if (inv.fireball > 0) {
                    fireball.use(inv);
                    System.out.println(message(fireball.damage, 5, ""));
                }
                else {
                    System.out.println("You are out of fireballs!");
                    repeat = true;
                }
            }
            else if (attackID == 6) {
                if (inv.lightning > 0) {
                    lightning.use(inv);
                    System.out.println(message(lightning.damage, 6, ""));
                    stun = true;
                }
                else {
                    System.out.println("You are out of lightning!");
                    repeat = true;
                }
            }
            else if (attackID == 7) {
                if (inv.poison > 0) {
                    poison.use(inv);
                    poisonCount = 5;
                    System.out.println(message(poison.damage, 7, ""));
                } else {
                    System.out.println("You are out of poison!");
                    repeat = true;
                }
            }
            else if(attackID == 8){
                if(inv.smallHeal > 0){
                    heal.healValue = 20;
                    if(currentHP >= baseHP){
                        System.out.println("Cannot heal any further. Max HP reached!");
                    }
                    else {
                        heal.use(inv);
                        inv.smallHeal--;
                        if(currentHP>baseHP){
                            currentHP = baseHP;
                        }
                    }
                    move(wpn, inv, fireball, lightning, poison, heal, skunk, fairy);
                }
                else{
                    System.out.println("You are out of small health potions!");
                }
            }
            else if(attackID == 9){
                if(inv.largeHeal > 0){
                    heal.healValue = 85;
                    inv.largeHeal--;
                    if(currentHP == baseHP){
                        System.out.println("Cannot heal any further. Max HP reached!");
                    }
                    else {
                        heal.use(inv);
                        inv.largeHeal--;
                        if(currentHP>baseHP){
                            currentHP = baseHP;
                        }
                    }
                }
                else{
                    System.out.println("You are out of large health potions!");
                }
                move(wpn, inv, fireball, lightning, poison, heal, skunk, fairy);
            }
            else {
                System.out.println("You missed your attack!");
            }

            if(poisonCount>0){
                Dragon.hp -= poison.tickDamage;
                System.out.println("The dragon is poisoned and suffers "+poison.tickDamage+" extra damage this turn!\n");
                poisonCount--;
            }
        } while (repeat) ;
    }

    public void petsEffects(Skunk skunk, Fairy fairy){
        if(hasSkunk){
            skunk.act();
            System.out.println("Stinky Skunk farts on the dragon and deals "+ skunk.effectValue + " damage");
        }
        if(hasFairy){
            fairy.act();
            System.out.println("Friendly Fairy grants you her healing blessing and heals you " +fairy.effectValue + " HP");
        }
    }

    public void attackMenu(Inventory inv, Spell fireball, Spell lightning, Poison poison){
        System.out.println("Dragon: " + Dragon.hp + " HP");
        System.out.println(name + ": " + currentHP + " HP");
        System.out.println();

        System.out.println("""
                What would you like to do?
                1 - Quick attack
                2 - Normal attack
                3 - Strong attack
                4 - I'm feeling lucky
                """);

        if (fireball.spellCheck(inv)|| lightning.spellCheck(inv)|| poison.spellCheck(inv)) {
            System.out.println("Spells:");
            if (fireball.spellCheck(inv)) {
                System.out.println("5 - Fireball x " + inv.fireball);
            }
            if (lightning.spellCheck(inv)) {
                System.out.println("6 - Lightning x " + inv.lightning);
            }
            if (poison.spellCheck(inv)) {
                System.out.println("7 - Poison x " + inv.poison);
            }
            System.out.println();
        }

        if(inv.smallHeal>0|| inv.largeHeal>0){
            System.out.println("Potions:");
            if(inv.smallHeal>0){
                System.out.println("8 - Small Health Potion x " + inv.smallHeal);
            }
            if(inv.largeHeal>0){
                System.out.println("9 - Large Health Potion x " + inv.largeHeal);
            }
            System.out.println();
        }
    }


    public String message(int dmg, int attackID, String wpn){
        return switch (attackID) {
            case 1 -> "You quickly jab your " + wpn + " at the dragon, dealing " + dmg + " damage!";
            case 2 -> "You firmly swing your " + wpn + " at the dragon, dealing " + dmg + " damage!";
            case 3 -> "You lunge your " + wpn + " at the dragon, dealing " + dmg + " damage!";
            case 4 -> "You use all your might to heave your " + wpn + " at the dragon, dealing a whopping " + dmg + " damage!!!";
            case 5 -> "You launch a fireball at the dragon dealing " + dmg + " damage!";
            case 6 -> "You cast lightning bolt at the dragon dealing " + dmg + " damage!";
            case 7 -> "You throw poison at the dragon dealing " + dmg + " damage";
            default -> " ";
        };
    }

    public boolean isDead(){
        return currentHP < 0;
    }

    public void addHP(){
        System.out.println("HP upgrade purchased!\n");
        baseHP += 10;
        System.out.println("Max HP is now "+baseHP+"!");
    }

    public void addAtk(){
        System.out.println("Attack upgrade purchased!\n");
        attack += 1;
        System.out.println("Your attack is now "+attack+"!");
    }

    public void addDfs(){
        System.out.println("Defense upgrade purchased!\n");
        defense += 1;
        System.out.println("Your defense is now "+defense+"!");
    }

    public void addMgc(){
        System.out.println("Magic upgrade purchased!\n");
        magic += 1;
        System.out.println("Your magic is now "+magic+"!");
    }


}
