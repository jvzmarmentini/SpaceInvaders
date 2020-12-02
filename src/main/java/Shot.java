import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Represents a shot that crosses the screen from bottom to up and then dismiss
 * 
 * @author Gabriel Panho, Gabriel Verdi e João Marmentini
 */
public abstract class Shot extends BasicElement {
    private boolean rnd_color;

    public Shot(int px, int py) {
        super(px, py);
        this.rnd_color = false;
    }

    public Shot(int px, int py, boolean rnd_color) {
        super(px, py);
        this.rnd_color = rnd_color;
    }

    @Override
    public abstract void start();

    @Override
    public void Update(long deltaTime) {
        if (jaColidiu()) {
            deactivate();
        } else {
            setPosY(getY() + getDirV() * getSpeed());
            if (getY() <= getLMinV() || getLMaxV() <= getY()) {
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
        if (rnd_color) {
            graphicsContext.setFill(Paint.valueOf(Params.getInstance().rnd_hexcolor()));
        }else{
            graphicsContext.setFill(Paint.valueOf("#FF0000"));
        }
        graphicsContext.fillOval(getX(), getY(), 4, 12);
    }
}
