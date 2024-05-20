public class Skunk extends Pet{

    static int level = 1;
    int effectValue;
    public Skunk(){
        effectValue = calculateEffect();
    }

    @Override
    public void act() {
        Dragon.hp -= effectValue;
    }

    public int calculateEffect(){
        return 10 * level;
    }
}
