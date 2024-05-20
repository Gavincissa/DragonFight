import java.util.Random;

public class Giant extends Pet{

    int effectValue;
    static int level = 1;
    Random rand = new Random();

    public Giant(){
        effectValue = calculateEffect();
    }

    public int calculateEffect(){
        return (rand.nextInt(3)+6) * level;
    }

    @Override
    public void act() {
        Dragon.damage -= effectValue;
    }
}
