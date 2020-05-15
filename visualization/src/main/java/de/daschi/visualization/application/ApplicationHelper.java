package de.daschi.visualization.application;

import javafx.beans.property.Property;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;

public class ApplicationHelper {
    //slider setting generator
    /*public static HBox generateSliderSetting(final String name, final double min, final double max, final double defaultValue, final double steps, final Property<Number> numberProperty) {
        final HBox hBox = ApplicationHelper.getHBox(Pos.CENTER);

        //create label
        final Label label = ApplicationHelper.getLabel(name);

        //generate slider
        final Slider slider = new Slider(min, max, defaultValue);
        slider.setBlockIncrement(steps);
        slider.setMajorTickUnit(steps);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.valueProperty().bindBidirectional(numberProperty);
        slider.valueProperty().addListener((observable, oldValue, newValue) -> Application.updateNoise());

        hBox.getChildren().addAll(label, slider);
        return hBox;
    }*/

    //spinner setting generator
    public static HBox generateSpinnerSetting(final String name, final double min, final double max, final double defaultValue, final double steps, final Property<Number> numberProperty) {
        final HBox hBox = ApplicationHelper.getHBox(Pos.CENTER_RIGHT);

        //create label
        final Label label = ApplicationHelper.getLabel(name);

        //generate spinner
        final Spinner<Double> spinner = new Spinner<>(min, max, defaultValue, steps);
        spinner.setEditable(true);
        spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            numberProperty.setValue(newValue);
            Application.updateNoise();
        });

        hBox.getChildren().addAll(label, spinner);
        return hBox;
    }

    //hBox generator
    public static HBox getHBox(final Pos pos) {
        final HBox hBox = new HBox(Application.getOFFSET());
        hBox.setAlignment(pos);
        return hBox;
    }

    //label generator
    public static Label getLabel(final String name) {
        return new Label(name + ":");
    }
}
