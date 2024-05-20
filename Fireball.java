public class Fireball extends Spell{

    public Fireball(Player player){
        damage = 35 + (player.magic*2);
    }

    public void use(Inventory inv){
        Dragon.hp -= damage;
        inv.fireball--;
    }

    @Override
    public boolean spellCheck(Inventory inv) {
        if (inv.fireball == 0) {
            return false;
        } else {
            return true;
        }
    }
}
