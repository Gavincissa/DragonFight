public abstract class Spell {

    int damage;

    public abstract void use(Inventory inv);

    public abstract boolean spellCheck(Inventory inv);

}
