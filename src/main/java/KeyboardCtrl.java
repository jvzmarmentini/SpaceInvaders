import javafx.scene.input.KeyCode;

/**
 * Represents the basic game character
 * @author Gabriel Panho, Gabriel Verdi e João Marmentini
 */
public interface KeyboardCtrl {
    void OnInput(KeyCode keyCode, boolean isPressed);
}
