public class Health extends Potion{

    int healValue;

    public Health(){

    }
    public void use(Inventory inv) {
        Player.currentHP += healValue;
    }

}
