package de.daschi.visualization.application;

import de.daschi.core.math.FastNoise;
import de.daschi.core.math.MathHelper;
import de.daschi.visualization.emulator.EmulatedChunk;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    private static final int WIDTH = 900, HEIGHT = 700, OFFSET = 10; //size and offset
    private static final SimpleIntegerProperty seed = new SimpleIntegerProperty(10000);//seed
    private static final SimpleIntegerProperty octaves = new SimpleIntegerProperty(3);// octaves
    private static final SimpleFloatProperty persistence = new SimpleFloatProperty(0.5f); //persistence
    private static final SimpleFloatProperty lacunarity = new SimpleFloatProperty(2); //lacunarity
    private static final SimpleStringProperty noiseType = new SimpleStringProperty(FastNoise.NoiseType.Value.toString());//noiseType
    private static Canvas basicNoiseCanvas; //basic noise canvas
    private static Canvas terrainCanvas; //terrain canvas

    public static void main(final String[] args) {
        Application.launch(args); //launch application
    }

    public static Canvas getBasicNoiseCanvas() {
        return Application.basicNoiseCanvas;
    }

    public static Canvas getTerrainCanvas() {
        return Application.terrainCanvas;
    }

    public static int getWIDTH() {
        return Application.WIDTH;
    }

    public static int getHEIGHT() {
        return Application.HEIGHT;
    }

    public static int getOFFSET() {
        return Application.OFFSET;
    }

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("NaturalTerrainGeneration - Visualization"); //set title
        primaryStage.setResizable(false); //set resizable

//create root
        final BorderPane root = new BorderPane();
        root.setPadding(new Insets(Application.OFFSET, Application.OFFSET, Application.OFFSET, 0));

        //seed setting
        final Pane seed = ApplicationHelper.generateSpinnerSetting("Seed", Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1, Application.seed);

        //octaves setting
        final Pane octaves = ApplicationHelper.generateSpinnerSetting("Octaves", 1, 9, 3, 1, Application.octaves);

        //persistence setting
        final Pane persistence = ApplicationHelper.generateSpinnerSetting("Persistence", 0.1, 2, 0.5, 0.1, Application.persistence);

        //lacunarity setting
        final Pane lacunarity = ApplicationHelper.generateSpinnerSetting("Lacunarity", 0.1, 4, 2, 0.1, Application.lacunarity);

        //noiseType setting
        final Pane noiseType = ApplicationHelper.generateRadioButtonsSetting("NoiseType", Application.noiseType, (Object[]) FastNoise.NoiseType.values());

        //create settings
        final VBox settings = new VBox(Application.OFFSET, seed, octaves, persistence, lacunarity, noiseType);
        settings.setPadding(new Insets(Application.OFFSET));
        root.setLeft(settings);

        primaryStage.setScene(new Scene(root, Application.WIDTH, Application.HEIGHT)); //create scene
        primaryStage.show(); //show primaryStage

        //create canvases
        Application.basicNoiseCanvas = new Canvas(Application.WIDTH - settings.getWidth(), Application.HEIGHT - Application.OFFSET);
        Application.terrainCanvas = new Canvas(Application.WIDTH - settings.getWidth(), Application.HEIGHT - Application.OFFSET);
        final TabPane tabPane = ApplicationHelper.generateTabPane(new Tab("Basic noise", Application.basicNoiseCanvas), new Tab("Terrain", Application.terrainCanvas));
        root.setCenter(tabPane);

        Application.updateNoise(); //draw noise
        EmulatedChunk.updateChunkMesh(Application.terrainCanvas); //draw terrain
    }

    public static void updateNoise() {
        //basic noise visualization
        Application.basicNoiseCanvas.getGraphicsContext2D().clearRect(0, 0, Application.basicNoiseCanvas.getWidth(), Application.basicNoiseCanvas.getHeight());
        for (int x = 0; x < Application.basicNoiseCanvas.getWidth(); x++) {
            for (int y = 0; y < Application.basicNoiseCanvas.getHeight(); y++) {
                Application.basicNoiseCanvas.getGraphicsContext2D().getPixelWriter().setColor(x, y, Color.gray(MathHelper.generateNoise(Application.seed.get(), x, y, Application.octaves.get(), Application.persistence.get(), Application.lacunarity.get(), 0, 1, FastNoise.NoiseType.valueOf(Application.noiseType.get()))));
            }
        }
    }
}
