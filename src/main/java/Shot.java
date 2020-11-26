import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Represents a shot that crosses the screen from bottom to up and then dismiss
 * @author Gabriel Panho, Gabriel Verdi e João Marmentini
 */
public abstract class Shot extends BasicElement {

    public Shot(int px, int py) {
        super(px, py);
    }

    @Override
    public abstract void start();

    @Override
    public void Update(long deltaTime) {
        if (jaColidiu()) {
            deactivate();
        } else {
            setPosY(getY() + getDirV() * getSpeed());
            if (getY() <= getLMinV()) {
                deactivate();
            }
        }
    }

    @Override
    public int getAltura() {
        return 16;
    }

    @Override
    public int getLargura() {
        return 8;
    }

    @Override
    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.fillOval(getX(), getY(), 4, 12);
    }
}
