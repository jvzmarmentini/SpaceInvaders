/**
 * Represents an angry alien that crosses the screen over while shooting
 * @author Gabriel Panho, Gabriel Verdi e João Marmentini
 */
public class AngryAlien extends Alien {
    private final int RELOAD_TIME = 1000000000;
    private int shot_timer = 0;

    public AngryAlien(int px, int py) {
        super(px, py, "alien.png");
    }

    @Override
    public void Update(long deltaTime) {
        super.Update(deltaTime);
        if (shot_timer > 0)
            shot_timer -= deltaTime / 2;
        else {
            if (Params.getInstance().nextInt(3) == 0) {
                Game.getInstance().addChar(new AlienShot(getX(), getY() + 40));
            }
            shot_timer = RELOAD_TIME;
        }
    }
}
