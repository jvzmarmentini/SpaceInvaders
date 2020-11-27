import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Represents the shot of the gun.
 * 
 * @author Gabriel Panho, Gabriel Verdi e João Marmentini
 */
public class GunShot extends Shot {

    public GunShot(int px, int py) {
        super(px, py);
    }

    public GunShot(int px, int py, boolean rnd_color) {
        super(px, py, rnd_color);
    }

    @Override
    public void start() {
        setSpeed(3);
        setDirV(-1);
    }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
        super.Draw(graphicsContext);
        //graphicsContext.setFill(Paint.valueOf("#00FFFF"));
    }
}
