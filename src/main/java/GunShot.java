/**
 * Represents the shot of the gun.
 * @author Gabriel Panho, Gabriel Verdi e João Marmentini
 */
public class GunShot extends Shot {

    public GunShot(int px, int py) {
        super(px, py, "#00FF00");
    }

    @Override
    public void start() {
        setSpeed(3);
        setDirV(-1);
    }
    
}
