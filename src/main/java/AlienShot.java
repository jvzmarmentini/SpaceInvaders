import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Represents the angry alien shot
 * 
 * @author Gabriel Panho, Gabriel Verdi e João Marmentini
 */
public class AlienShot extends Shot {

    public AlienShot(int px, int py) {
        super(px, py);
    }

    @Override
    public void start() {
        setSpeed(2);
        setDirV(1);
    }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
        super.Draw(graphicsContext);
        graphicsContext.setFill(Paint.valueOf("#FF0000"));
    }
}
