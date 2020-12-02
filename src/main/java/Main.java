// import java.awt.Color;

import java.awt.Color;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
// import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import java.util.Scanner;

/**
 * Handles window initialization and primary game setup
 * 
 * @author Gabriel Panho, Gabriel Verdi e João Marmentini
 */

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        // Initialize Window
        stage.setTitle(Params.WINDOW_TITLE);
        stage.setResizable(false);

        // Group root = new Group();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        Scene scene = new Scene(gridPane, Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
        stage.setScene(scene);

        Canvas canvas = new Canvas(Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Paint.valueOf("#00FF00"));
        gc.fillRect(75, 75, 100, 100);

        Label start = new Label("Space Invaders");
        Button btnStart = new Button();
        Button btnStop = new Button();

        btnStart.setText("Space Invaders");
        btnStart.setOnAction((ActionEvent event) -> {
            cleanScreen(gridPane);
            starGame(gridPane, canvas, scene);
        });
        btnStop.setText("Quit");
        btnStop.setOnAction((ActionEvent event) -> {
            stage.close();
        });

        gridPane.add(start, 0, 0);
        gridPane.add(btnStart, 0, 1);
        gridPane.add(btnStop, 0, 2);
        GridPane.setHalignment(start, HPos.CENTER);
        GridPane.setHalignment(btnStart, HPos.CENTER);
        GridPane.setHalignment(btnStop, HPos.CENTER);
        stage.setScene(scene);

        // Show window
        stage.show();
    }

    private void starGame(GridPane root, Canvas canvas, Scene scene) {
        Label name = new Label("Nome: ");
        root.getChildren().add(name);
        root.getChildren().add(canvas);
        Image backgound = new Image("background.png", Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT, false, true);

        // Setup Game object
        Game.getInstance().Start();
        Game.getInstance().Run();

        // Register User Input Handler
        scene.setOnKeyPressed((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), true);
        });

        scene.setOnKeyReleased((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), false);
        });

        // Register Game Loop
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        new AnimationTimer() {
            long lastNanoTime = System.nanoTime();

            @Override
            public void handle(long currentNanoTime) {
                long deltaTime = currentNanoTime - lastNanoTime;

                Game.getInstance().Update(currentNanoTime, deltaTime);
                gc.drawImage(backgound, 0, 0);
                gc.setFill(Paint.valueOf("#00FF00")); // seta a para que ela não fique random na pontuação
                gc.fillText("Pontos: " + Game.getInstance().getPontos(), 10, 10);
                gc.fillText("Fase: " + Game.getInstance().getFase(), Params.WINDOW_WIDTH/2, 10);
                Game.getInstance().Draw(gc);
                if (Game.getInstance().isGameOver()) {
                    Game.getInstance().salvaPontos();
                    gc.fillText("\nHighScores: \n" + Game.getInstance().getHighScore(), 10, 10);
                    stop();
                }

                lastNanoTime = currentNanoTime;
            }

        }.start();
    }

    private void cleanScreen(GridPane root) {
        root.getChildren().clear();
    }

    public static void main(String args[]) {
        launch();
    }
}
