/**
 * @author Gabriel Panho, Gabriel Verdi e João Marmentini
 */

public class InvokerAlien extends Alien {
    private final static int DRINK_POTION_TIME = 1000000000;
    private int mana = 1000000000;

    public InvokerAlien(int px, int py) {
        super(px, py, "invokerAlien.png");
    }

    @Override
    public void Update(long deltaTime) {
        if (mana > 0)
            mana -= deltaTime / 2;
        else {
            if (Params.getInstance().coinFlip()) {
                Game.getInstance().addChar(new Alien(10, 10, "alien.png"));
            }
            mana = DRINK_POTION_TIME;
        }
        super.Update(deltaTime);
    }
    
}
