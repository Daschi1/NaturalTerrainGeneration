package de.daschi.visualization.application;

import de.daschi.core.math.MathHelper;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    private static final int WIDTH = 500, HEIGHT = 500; //size
    private static Canvas canvas; //main canvas

    public static void main(final String[] args) {
        Application.launch(args); //launch application
    }

    public static Canvas getCanvas() {
        return Application.canvas;
    }

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("NaturalTerrainGeneration - Visualization"); //set title
        primaryStage.setResizable(false); //set resizable

        Application.canvas = new Canvas(Application.WIDTH, Application.HEIGHT); //create canvas
        final BorderPane root = new BorderPane(Application.canvas); //create root
        primaryStage.setScene(new Scene(root, Application.WIDTH, Application.HEIGHT)); //create scene

        //basic noise visualization
        for (int x = 0; x < Application.WIDTH; x++) {
            for (int y = 0; y < Application.HEIGHT; y++) {
                Application.canvas.getGraphicsContext2D().getPixelWriter().setColor(x, y, Color.gray(MathHelper.generatePerlinNoise(10000, x, y, 5, 0.3f, 1.9f, 0, 1)));
            }
        }

        primaryStage.show(); //show primaryStage
    }
}
