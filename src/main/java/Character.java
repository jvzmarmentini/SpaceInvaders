import javafx.scene.canvas.GraphicsContext;

/**
 * Represents the basic game character
 * @author Gabriel Panho, Gabriel Verdi e João Marmentini
 */
public interface Character {
    int getX();
    int getY();
    int getAltura();
    int getLargura();

    void testaColisao(Character c);
    boolean jaColidiu();
    void setColidiu(boolean b);

    void start();
    boolean isActive();
    void Update(long deltaTime);
    void Draw(GraphicsContext graphicsContext);
}
