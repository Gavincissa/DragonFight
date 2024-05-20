public class Fairy extends Pet{

    static int level = 1;
    int effectValue;

    public Fairy(){
        effectValue = calculateEffect();
    }

    @Override
    public void act() {
        if(Player.currentHP < Player.baseHP) {
            Player.currentHP += effectValue;
            if(Player.currentHP > Player.baseHP){
                Player.currentHP = Player.baseHP;
            }
        }
    }

    public int calculateEffect(){
        return 2 * level;
    }
}
