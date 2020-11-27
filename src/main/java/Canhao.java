import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;


/**
 * Represents the game Gun
 * @author Gabriel Panho, Gabriel Verdi e João Marmentini
 */
public class Canhao extends BasicElement implements KeyboardCtrl {
    private final int RELOAD_TIME = 500000000; // Time is in nanoseconds
    private int shot_timer = 0;
    private Image image = new Image("ship.png", 45, 60,false, true);

    public Canhao(int px, int py) {
        super(px, py);
    }

    @Override
    public void start() {
        setLimH(20, Params.WINDOW_WIDTH - 20);
        setLimV(Params.WINDOW_HEIGHT - 100, Params.WINDOW_HEIGHT);
    }

    @Override
    public void Update(long deltaTime) {
        if (jaColidiu()) {
            System.out.println("dead");
            Game.getInstance().setGameOver();
        }
        setPosX(getX() + getDirH() * getSpeed());
        if (shot_timer > 0)
            shot_timer -= deltaTime;
    }

    @Override
    public void OnInput(KeyCode keyCode, boolean isPressed) {
        if (keyCode == KeyCode.LEFT) {
            int dh = isPressed ? -1 : 0;
            setDirH(dh);
        }
        if (keyCode == KeyCode.RIGHT) {
            int dh = isPressed ? 1 : 0;
            setDirH(dh);
        }
        if (keyCode == KeyCode.SPACE) {
            if (shot_timer <= 0) {
                Game.getInstance().addChar(new GunShot(getX() + 16, getY() - 32, true));
                shot_timer = RELOAD_TIME;
            }
        }
    }

    @Override
    public int getAltura() {
        return 80;
    }

    @Override
    public int getLargura() {
        return 32;
    }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, getX(),getY());

       /*  graphicsContext.setFill(Paint.valueOf("#FF0000"));
        graphicsContext.fillRect(getX(), getY() + 16, 32, 32);
        graphicsContext.fillRect(getX() + 8, getY() - 16, 16, 48); */
    }
}
