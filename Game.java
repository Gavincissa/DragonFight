import java.util.*;
public class Game {

    public static void main(String[] args) {

        Player player = new Player();
        Dragon dragon = new Dragon();
        Shop shop = new Shop();
        Weapon weapon = new Weapon(0);

        printStart();

        do {
            try {
                weapon = weaponSelect();
                System.out.println();

            } catch (InputMismatchException e) {
                System.out.println("\nPlease enter a valid integer\n");
            }
        }while(weapon.ID != 1 && weapon.ID != 2 &&
                weapon.ID != 3 && weapon.ID != 4 && weapon.ID != 69);

        weapon.weaponMessage();

        player = buildCharacter(player);

        Inventory inventory = new Inventory();


        do {
            if(Dragon.level % 3 == 0 && Dragon.level != 0) {
                Shop.increasePrice();
            }
            beginNextLevel();
            battle(player, dragon, inventory, weapon);

            if (!player.isDead()) {
                System.out.println("The dragon collapses to the ground in defeat.\n\n");
                do {
                    try {
                        shop.printShop(inventory);
                        shop.purchase(inventory, player);
                    }catch (InputMismatchException E){
                        System.out.println("\nPlease enter a valid integer\n");
                    }
                } while (shop.select != 0);
                if(Dragon.level > 4){
                    do{
                        shop.printPetShop(inventory);
                        shop.purchase(inventory, player);
                    }while(shop.select!=0);
                }
            } else {
                System.out.println("You died. Game Over...\n\n");
                System.out.println("You made it to level " + Dragon.level);
            }

        } while (!player.isDead());
    }


    public static void printStart() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello traveler! What is your name?");
        Player.name = scan.nextLine();
        System.out.println();

        System.out.println("Great to have you in our realm, " + Player.name + "!");
        System.out.println("Unfortunately, an immortal dragon is here and wants to kill you.");
        System.out.println("The worst part is that every time the dragon is defeated, it grows stronger.\n");
        System.out.println("It is time to gear up. Please select a weapon.\n");

        System.out.println("1 - Dagger: Small yet quick. Can deal light damage with a higher hit chance.");
        System.out.println("2 - Broadsword: Ol' reliable. For average Joe's who enjoy a standard fighting experience.");
        System.out.println("3 - War Axe: This puppy can knock your opponent on their ass. High damage at a cost of hit chance.");
        System.out.println("4 - King's Hammer: The almighty hammer will rarely land a hit, but when it does, Hell will rain.");
    }

    public static Player buildCharacter(Player player) {
        Scanner scan = new Scanner(System.in);
        System.out.println("You have " +player.skillPoints+ " skill points to add to your character attributes (Attack, Defense, Agility, and Magic).");
        int count = player.skillPoints + 1;
        int attack = 0;
        int defense = 0;
        int agility = 0;
        int magic = 0;

        while(count > player.skillPoints){
            try {
                System.out.println("Enter attack:");
                attack = scan.nextInt();
                System.out.println("Enter defense:");
                defense = scan.nextInt();
                System.out.println("Enter agility:");
                agility = scan.nextInt();
                System.out.println("Enter magic:");
                magic = scan.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer\n");
                scan.next();
            }
            count = agility + defense + attack + magic;
            if (count > player.skillPoints) {
                System.out.println("Insufficient skill points. Please try again");
            }
        }

        if (count < player.skillPoints) {
            do {
                int tempAttack = attack;
                int tempDefense = defense;
                int tempAgility = agility;
                int tempMagic = magic;
                try {
                    System.out.println("You still have " + (player.skillPoints - count) + " skill points remaining.");
                    System.out.print("Attack: " + attack + " + ");
                    attack = attack + scan.nextInt();
                    System.out.print("Defense: " + defense + " + ");
                    defense = defense + scan.nextInt();
                    System.out.print("Agility: " + agility + " + ");
                    agility = agility + scan.nextInt();
                    System.out.print("Magic: " + magic + " + ");
                    magic = magic + scan.nextInt();

                }catch(InputMismatchException e){
                    System.out.println("\nPlease enter a valid integer\n");
                    scan.next();
                }
                count = agility + attack + defense + magic;

                if (count > player.skillPoints) {
                    System.out.println("Insufficient skill points. Please try again.\n");
                    attack = tempAttack;
                    defense = tempDefense;
                    agility = tempAgility;
                    magic = tempMagic;
                    count = agility + attack + defense + magic;
                }

            } while (count != player.skillPoints);
        }

        System.out.println("\nAttack: "+attack+
                "\nDefense: "+defense+
                "\nAgility: "+agility+
                "\nMagic: "+magic+"\n");

        return new Player(Player.name,50, attack, defense, agility, magic);

    }

    public static void battle(Player player, Dragon dragon, Inventory inventory, Weapon weapon){
        Spell fireball = new Fireball(player);
        Spell lightning = new Lightning(player);
        Poison poison = new Poison(player);
        Health heal = new Health();
        Skunk skunk = new Skunk();
        Fairy fairy = new Fairy();
        Giant giant = new Giant();

        boolean deadDragon = false;

        do {
            player.move(weapon, inventory, fireball, lightning, poison, heal, skunk, fairy);
            if(!player.stun&&!dragon.isDead()) {
                dragon.attack(player, giant);
            }

            player.petsEffects(skunk, fairy);

            if(player.stun){
                System.out.println("The dragon has been stunned! He does not attack you!\n\n");
            }

            else if(dragon.isDead()){
                deadDragon = true;
                Dragon.hp = Dragon.level * 50;
                inventory.addGold();
            }

        }while(!deadDragon && !player.isDead());
    }

    public static void beginNextLevel(){

        Player.poisonCount = 0;
        Dragon.level += 1;
        Dragon.hp = 50 * Dragon.level;

        Player.currentHP = Player.baseHP;

        if(Dragon.level > 1){
            System.out.println("The dragon rises from the dead with vengeance in his heart.\n");
        }

        System.out.println("Level "+ Dragon.level +"\n");
        System.out.println("The dragon approaches you with flames in his eyes.\n" +
                "'I am going to eat you "+ Player.name +"' he says.\n");
    }

    public static Weapon weaponSelect(){
        Scanner scan = new Scanner(System.in);
        System.out.println();
        int input = scan.nextInt();
        if((input > 4 && input != 69) || input < 1){
            System.out.println("\nPlease select a valid option");
        }
        return new Weapon(input);
    }
}
