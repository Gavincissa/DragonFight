public class Weapon {

    int speed;
    int damage;
    int ID;
    String name;

    public Weapon(int ID){
        this.ID = ID;
        if(ID==1){
            speed = 4;
            damage = 1;
            name = "dagger";
        }
        if(ID==2){
            speed = 3;
            damage = 2;
            name = "broadsword";
        }
        if(ID==3){
            speed = 2;
            damage = 3;
            name = "war axe";
        }
        if(ID==4){
            speed = 1;
            damage = 5;
            name = "king's hammer";
        }
        if(ID==69){
            speed = 69;
            damage = 69;
            name = "secret dildo";
        }
    }

    public void weaponMessage(){
        switch (ID){
            case 1:
                System.out.println("You have chosen the dagger. Interesting choice...\n");
                break;
            case 2:
                System.out.println("You have chosen the broadsword... basic\n");
                break;
            case 3:
                System.out.println("You have chosen the war axe. Time to hack some heads off.\n");
                break;
            case 4:
                System.out.println("You have chosen the king's hammer. You are a king...\n");
                break;
            case 69:
                System.out.println("You have discovered the secret dildo. Nice.\n");
                break;
        }
    }
}
