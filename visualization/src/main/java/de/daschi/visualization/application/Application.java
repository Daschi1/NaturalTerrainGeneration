package de.daschi.visualization.application;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

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

        Application.canvas = new Canvas(); //create canvas
        final BorderPane root = new BorderPane(Application.canvas); //create root
        primaryStage.setScene(new Scene(root, 500, 500)); //create scene

        primaryStage.show(); //show primaryStage
    }
}
