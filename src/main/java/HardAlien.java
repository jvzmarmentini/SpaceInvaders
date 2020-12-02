/**
 * Represents a hard alien that crosses the screen over and can take two shots before die.
 * @author Gabriel Panho, Gabriel Verdi e João Marmentini
 */
public class HardAlien extends Alien {
    private int shields = 2;

    public HardAlien(int px, int py) {
        super(px, py, "hardAlien.png");
        setSpeed(1);
    }

    @Override
    public void Update(long deltaTime) {
        if (shields > 0) {
            if (jaColidiu()) {
                shields--;
                super.setColidiu(false);
            }
        }
        super.Update(deltaTime);
    }

}
