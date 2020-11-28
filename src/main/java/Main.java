// import java.awt.Color;

import java.awt.Color;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
// import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
// import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;


/**
 * Handles window initialization and primary game setup
 * @author Gabriel Panho, Gabriel Verdi e João Marmentini
 */

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Image backgound = new Image("background.png",Params.WINDOW_WIDTH,Params.WINDOW_HEIGHT,false,true);
        // Initialize Window
        stage.setTitle(Params.WINDOW_TITLE);
        stage.setResizable(false);

        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene( scene );

        Canvas canvas = new Canvas(Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT );

        root.getChildren().add( canvas );

        // Setup Game object
        Game.getInstance().Start();

        // Register User Input Handler
        scene.setOnKeyPressed((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), true);
        });

        scene.setOnKeyReleased((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), false);
        });

        // Register Game Loop
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        new AnimationTimer()
        {
            long lastNanoTime = System.nanoTime();

            @Override
            public void handle(long currentNanoTime)
            {
                long deltaTime = currentNanoTime - lastNanoTime;

                Game.getInstance().Update(currentNanoTime, deltaTime);
                gc.drawImage(backgound, 0, 0);
                //gc.clearRect(0, 0, Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
                gc.setFill(Paint.valueOf("#00FF00")); //seta a para que ela não fique random na pontuação
                gc.fillText("Pontos: "+Game.getInstance().getPontos(), 10, 10);
                Game.getInstance().Draw(gc);
                if (Game.getInstance().isGameOver()){
                    Game.getInstance().salvaPontos();
                    stop();
                }

                lastNanoTime = currentNanoTime;
            }

        }.start();

        // Show window
        stage.show();
    }

    public static void main(String args[]) {
        launch();
    }
}
