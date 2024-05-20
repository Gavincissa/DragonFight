public class Poison extends Spell{

    int tickDamage;

    public Poison(Player player){
        damage = 40 + player.magic;
        tickDamage = 5 + player.magic;
    }

    @Override
    public void use(Inventory inv) {
        Dragon.hp -= damage;
        inv.poison--;
    }

    @Override
    public boolean spellCheck(Inventory inv) {
        if (inv.poison == 0) {
            return false;
        } else {
            return true;
        }
    }
}
