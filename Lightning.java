public class Lightning extends Spell{

    public Lightning(Player player){
        damage = 25 + (player.magic*2);
    }

    @Override
    public void use(Inventory inv) {
        Dragon.hp -= damage;
        inv.lightning--;
    }

    @Override
    public boolean spellCheck(Inventory inv) {
        if (inv.lightning == 0) {
            return false;
        } else {
            return true;
        }
    }


}
