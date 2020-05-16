package de.daschi.visualization.application;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ApplicationHelper {
    //slider setting generator
    /*public static Pane generateSliderSetting(final String name, final double min, final double max, final double defaultValue, final double steps, final Property<Number> property) {
        final HBox hBox = ApplicationHelper.getHBox(Pos.CENTER);

        //create label
        final Label label = ApplicationHelper.getLabel(name);

        //generate slider
        final Slider slider = new Slider(min, max, defaultValue);
        slider.setBlockIncrement(steps);
        slider.setMajorTickUnit(steps);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.valueProperty().bindBidirectional(property);
        slider.valueProperty().addListener((observable, oldValue, newValue) -> Application.updateNoise());

        hBox.getChildren().addAll(label, slider);
        return hBox;
    }*/

    //spinner setting generator
    public static Pane generateSpinnerSetting(final String name, final double min, final double max, final double defaultValue, final double steps, final Property<Number> property) {
        final HBox hBox = ApplicationHelper.getHBox(Pos.CENTER_RIGHT);

        //create label
        final Label label = ApplicationHelper.getLabel(name);

        //generate spinner
        final Spinner<Double> spinner = new Spinner<>(min, max, defaultValue, steps);
        spinner.setEditable(true);
        spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue >= min && newValue <= max) {
                property.setValue(newValue);
                Application.updateNoise();
            }
        });

        hBox.getChildren().addAll(label, spinner);
        return hBox;
    }

    //radioButton setting generator
    public static Pane generateRadioButtonsSetting(final String name, final SimpleStringProperty property, final Object... objects) {
        final VBox vBox = ApplicationHelper.getVBox(Pos.CENTER_LEFT);

        //create label
        final Label label = ApplicationHelper.getLabel(name);

        //generate radioButtons
        final ToggleGroup toggleGroup = new ToggleGroup();
        final RadioButton[] radioButtons = new RadioButton[objects.length];
        for (int i = 0; i < objects.length; i++) {
            final RadioButton radioButton = new RadioButton(objects[i].toString());
            radioButton.setToggleGroup(toggleGroup);
            radioButtons[i] = radioButton;
        }
        radioButtons[0].setSelected(true);
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            property.setValue(((RadioButton) newValue).getText());
            Application.updateNoise();
        });

        vBox.getChildren().add(label);
        vBox.getChildren().addAll(radioButtons);
        return vBox;
    }

    //generate tabPane
    public static TabPane generateTabPane(final Tab... tabs) {
        final TabPane tabPane = new TabPane(tabs);
        tabPane.setSide(Side.TOP);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        return tabPane;
    }

    //hBox generator
    public static HBox getHBox(final Pos pos) {
        final HBox hBox = new HBox(Application.getOFFSET());
        hBox.setAlignment(pos);
        return hBox;
    }

    //vBox generator
    public static VBox getVBox(final Pos pos) {
        final VBox vBox = new VBox(Application.getOFFSET());
        vBox.setAlignment(pos);
        return vBox;
    }

    //label generator
    public static Label getLabel(final String name) {
        return new Label(name + ":");
    }
}
