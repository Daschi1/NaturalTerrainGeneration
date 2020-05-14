package de.daschi.visualization.application;

import de.daschi.core.math.MathHelper;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class Application extends javafx.application.Application {

    private static final int WIDTH = 900, HEIGHT = 700, OFFSET = 10; //size and offset
    private static final SimpleIntegerProperty seed = new SimpleIntegerProperty(10000);//seed
    private static final SimpleIntegerProperty octaves = new SimpleIntegerProperty(3);// octaves
    private static final SimpleFloatProperty persistence = new SimpleFloatProperty(0.5f); //persistence
    private static final SimpleFloatProperty lacunarity = new SimpleFloatProperty(2); //lacunarity
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

        //seed setting
        final TextField textField = new TextField("10000"); // TODO: 15.05.2020 make extra funxtion + label
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (textField.getText().length() >= 9) {
                textField.setText("999999999"); // FIXME: 15.05.2020 exeeding integer limit + empty seed
            }
            Application.seed.set(Integer.parseInt(newValue));
            this.drawNoise();
        });

        //octaves setting
        final HBox octaves = this.generateSliderSetting("Octaves", 1, 6, 3, 1, Application.octaves);

        //octaves persistence
        final HBox persistence = this.generateSliderSetting("Persistence", 0.1, 2, 0.5, 0.1, Application.persistence);

        //octaves lacunarity
        final HBox lacunarity = this.generateSliderSetting("Lacunarity", 0.1, 4, 2, 0.1, Application.lacunarity);

        //create settings
        final VBox settings = new VBox(Application.OFFSET, textField, octaves, persistence, lacunarity);
        settings.setPadding(new Insets(Application.OFFSET));

        Application.canvas = new Canvas(Application.WIDTH - settings.getWidth(), Application.HEIGHT - Application.OFFSET); //create canvas

        //create root
        final BorderPane root = new BorderPane();
        root.setPadding(new Insets(Application.OFFSET, Application.OFFSET, Application.OFFSET, 0));
        root.setLeft(settings);
        root.setCenter(Application.canvas);

        primaryStage.setScene(new Scene(root, Application.WIDTH, Application.HEIGHT)); //create scene

        primaryStage.show(); //show primaryStage

        this.drawNoise(); //draw noise
    }

    //slider setting generator
    private HBox generateSliderSetting(final String name, final double min, final double max, final double defaultValue, final double steps, final Property<Number> numberProperty) {
        final HBox hBox = new HBox(Application.OFFSET);

        //create label
        final Label label = new Label(name + ": " + defaultValue);

        //generate slider
        final Slider slider = new Slider(min, max, defaultValue);
        slider.setBlockIncrement(steps);
        slider.setMajorTickUnit(steps);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.valueProperty().bindBidirectional(numberProperty);
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            label.setText(name + ": " + new DecimalFormat("0.0").format(newValue));
            this.drawNoise();
        });

        hBox.getChildren().addAll(label, slider);
        return hBox;
    }

    private void drawNoise() {
        //basic noise visualization
        Application.canvas.getGraphicsContext2D().clearRect(0, 0, Application.canvas.getWidth(), Application.canvas.getHeight());
        for (int x = 0; x < Application.canvas.getWidth(); x++) {
            for (int y = 0; y < Application.canvas.getHeight(); y++) {
                Application.canvas.getGraphicsContext2D().getPixelWriter().setColor(x, y, Color.gray(MathHelper.generatePerlinNoise(Application.seed.get(), x, y, Application.octaves.get(), Application.persistence.get(), Application.lacunarity.get(), 0, 1)));
            }
        }
    }
}
