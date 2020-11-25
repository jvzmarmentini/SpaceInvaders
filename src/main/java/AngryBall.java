import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class AngryBall extends Ball {
    private final int RELOAD_TIME = 1000000000;
    private int shot_timer = 0;

    public AngryBall(int px, int py) {
        super(px, py);
    }

    @Override
    public void Update(long deltaTime) {
        super.Update(deltaTime);
        if (shot_timer > 0)
            shot_timer -= deltaTime / 2;
        else {
            if (Params.getInstance().nextInt(3) == 0) {
                Game.getInstance().addChar(new Shot(getX(), getY() + 40, 1));
            }
            shot_timer = RELOAD_TIME;
        }
    }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Paint.valueOf("#FF00FF"));
        graphicsContext.fillOval(getX(), getY(), 32, 32);
    }
}
