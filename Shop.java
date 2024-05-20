import java.util.*;
public class Shop{
    static int hpCost = 50;
    static int atkCost = 50;
    static int dfsCost = 50;
    static int mgcCost = 50;
    static int fblCost = 50;
    static int lngCost = 60;
    static int psnCost = 70;
    int smlHthCost = 25;
    int lgHthCost = 75;
    int sknkCost = 500;
    int fryCost = 500;
    int gntCost = 500;
    static int sknkUpgrade = 400;
    static int fryUpgrade = 400;
    static int gntUpgrade = 400;
    int shopNumber = 0;
    static int select;


    public Shop(){

    }

    public void printShop(Inventory inventory) {
        Scanner scan = new Scanner(System.in);
        shopNumber = 1;
        System.out.println("Gold: " + inventory.gold);
        System.out.println("What would you like to buy?\n" +
                "1 - HP Upgrade - " + hpCost + " gold\n" +
                "2 - Attack Upgrade - " + atkCost + " gold\n" +
                "3 - Defense Upgrade - " + dfsCost + " gold\n" +
                "4 - Magic Upgrade - " + mgcCost + " gold\n" +
                "5 - Fireball - " + fblCost + " gold\n" +
                "6 - Lightning - " + lngCost + " gold\n" +
                "7 - Poison - " + psnCost + " gold\n" +
                "8 - Small health potion - " + smlHthCost + " gold\n" +
                "9 - Large health potion - " + lgHthCost + " gold\n" +
                "0 - Exit Shop\n" +
                "Please select an item or exit the shop to begin next level.");
        select = scan.nextInt();
    }

    public void printPetShop(Inventory inventory){
        Scanner scan = new Scanner(System.in);
        shopNumber = 2;
        System.out.println("Gold: "+inventory.gold);
        System.out.println("Pet Shop:");
        if (Player.hasSkunk) {
            System.out.println("1 - Upgrade Skunk - " + sknkUpgrade + " gold");
        } else {
            System.out.println("1 - Stinky Skunk - " + sknkCost + " gold");
        }
        if (Player.hasFairy) {
            System.out.println("2 - Upgrade Fairy - " + fryUpgrade + " gold");
        } else {
            System.out.println("2 - Friendly Fairy - " + fryCost + " gold");
        }
        if (Player.hasGiant) {
            System.out.println("3 - Upgrade Giant - " + gntUpgrade + " gold");
        } else {
            System.out.println("3 - Gentle Giant - " + gntCost + " gold");
        }
        System.out.println("0 - Exit Shop");
        select = scan.nextInt();
        if(select == 0) {
            shopNumber = 0;
        }
    }

    public void purchase(Inventory inventory, Player player){
        if(shopNumber == 1) {
            switch (select) {
                case 1:
                    if (inventory.gold >= hpCost) {
                        player.addHP();
                        inventory.removeGold(hpCost);
                        hpCost += 50;
                    } else {
                        System.out.println("You don't have enough gold...\n");
                    }

                    break;
                case 2:
                    if (inventory.gold >= atkCost) {
                        player.addAtk();
                        inventory.removeGold(atkCost);
                        atkCost += 50;
                    } else {
                        System.out.println("You don't have enough gold...\n");
                    }
                    break;
                case 3:
                    if (inventory.gold >= dfsCost) {
                        player.addDfs();
                        inventory.removeGold(dfsCost);
                        dfsCost += 50;
                    } else {
                        System.out.println("You don't have enough gold...\n");
                    }
                    break;
                case 4:
                    if (inventory.gold >= mgcCost) {
                        player.addMgc();
                        inventory.removeGold(mgcCost);
                        mgcCost += 50;
                    } else {
                        System.out.println("You don't have enough gold...\n");
                    }
                    break;
                case 5:
                    if (inventory.gold >= fblCost) {
                        inventory.addFireball();
                        inventory.removeGold(fblCost);
                    } else {
                        System.out.println("You don't have enough gold...\n");
                    }
                    break;
                case 6:
                    if (inventory.gold >= lngCost) {
                        inventory.addLightning();
                        inventory.removeGold(lngCost);
                    } else {
                        System.out.println("You don't have enough gold...\n");
                    }
                    break;
                case 7:
                    if (inventory.gold >= psnCost) {
                        inventory.addPoison();
                        inventory.removeGold(psnCost);
                    } else {
                        System.out.println("You don't have enough gold...\n");
                    }
                    break;
                case 8:
                    if (inventory.gold >= smlHthCost) {
                        inventory.addSmallHeal();
                        inventory.removeGold(smlHthCost);
                        smlHthCost += 5;
                    } else {
                        System.out.println("You don't have enough gold...\n");
                    }
                    break;
                case 9:
                    if (inventory.gold >= lgHthCost) {
                        inventory.addLargeHeal();
                        inventory.removeGold(lgHthCost);
                        lgHthCost += 25;
                    } else {
                        System.out.println("You don't have enough gold...\n");
                    }
                    break;
            }
        }
        if(shopNumber == 2){
            switch (select){
                case 1:
                    if(!Player.hasSkunk) {
                        if (inventory.gold >= sknkCost) {
                            Player.hasSkunk = true;
                            inventory.removeGold(sknkCost);
                        } else {
                            System.out.println("You don't have enough gold...");
                        }
                    }
                    else{
                        if (inventory.gold >= sknkUpgrade) {
                            Skunk.level++;
                            inventory.removeGold(sknkUpgrade);
                            sknkUpgrade *= 2;
                        } else {
                            System.out.println("You don't have enough gold...");
                        }
                    }
                    break;
                case 2:
                    if(!Player.hasFairy) {
                        if (inventory.gold >= fryCost) {
                            Player.hasFairy = true;
                            inventory.removeGold(fryCost);
                        } else {
                            System.out.println("You don't have enough gold...");
                        }
                    }
                    else{
                        if (inventory.gold >= fryUpgrade) {
                            Fairy.level++;
                            inventory.removeGold(fryUpgrade);
                            fryUpgrade *= 2;
                        } else {
                            System.out.println("You don't have enough gold...");
                        }
                    }
                    break;
                case 3:
                    if(!Player.hasGiant) {
                        if (inventory.gold >= gntCost) {
                            Player.hasGiant = true;
                            inventory.removeGold(gntCost);
                        } else {
                            System.out.println("You don't have enough gold...");
                        }
                    }
                    else{
                        if (inventory.gold >= gntUpgrade) {
                            Giant.level++;
                            inventory.removeGold(gntUpgrade);
                            gntUpgrade *= 2;
                        } else {
                            System.out.println("You don't have enough gold...");
                        }
                    }
                    break;
            }
        }
    }

    public static void increasePrice(){
        fblCost += 10;
        lngCost += 20;
        psnCost += 30;
    }


}
