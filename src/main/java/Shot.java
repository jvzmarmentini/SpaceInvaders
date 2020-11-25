import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Represents a shot that crosses the screen from bottom to up and then dismiss
 * 
 * @author Bernardo Copstein and Rafael Copstein
 */
public class Shot extends BasicElement {
    private int direction;

    public Shot(int px, int py, int direction) {
        super(px, py);
        this.direction = direction;
    }

    @Override
    public void start() {
        setDirV(direction);
        setSpeed(3);
    }

    @Override
    public void Update(long deltaTime) {
        if (jaColidiu()) {
            deactivate();
        } else {
            setPosY(getY() + getDirV() * getSpeed());
            // Se chegou na parte superior da tela ...
            if (getY() <= getLMinV()) {
                // Desaparece
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

    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Paint.valueOf("#00FF00"));
        graphicsContext.fillOval(getX(), getY(), 8, 16);
    }
}
