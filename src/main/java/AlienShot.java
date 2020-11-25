/**
 * Represents the angry alien shot
 * @author Gabriel Panho, Gabriel Verdi e João Marmentini
 */
public class AlienShot extends Shot {

    public AlienShot(int px, int py) {
        super(px, py, "#FF00FF");
    }

    @Override
    public void start() {
        setSpeed(2);
        setDirV(1);
    }
}
