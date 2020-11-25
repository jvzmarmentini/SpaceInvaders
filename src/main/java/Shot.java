import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Represents a shot that crosses the screen from bottom to up and then dismiss
 * @author Gabriel Panho, Gabriel Verdi e João Marmentini
 */
public abstract class Shot extends BasicElement {
    String hexColor;

    public Shot(int px, int py, String hexColor) {
        super(px, py);
        // this.hexColor = hexColor;
    }

    @Override
    public abstract void start();

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

    @Override
    public void Draw(GraphicsContext graphicsContext) {
        //FIXME: quando um inimigo dispara, a cor do tiro sai diferente do que deveria
        graphicsContext.fillOval(getX(), getY(), 8, 16);
        graphicsContext.setFill(Paint.valueOf(hexColor));
    }
}
