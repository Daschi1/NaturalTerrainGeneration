package de.daschi.visualization.emulator;

import javafx.scene.paint.Color;

public enum EmulatedMaterial {
    DEEP_WATER(Color.DARKBLUE),
    WATER(Color.LIGHTBLUE),
    SAND(Color.SANDYBROWN),
    GRASS(Color.GREEN),
    STONE(Color.GRAY),
    SNOW(Color.SNOW);

    private final Color color;

    EmulatedMaterial(final Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }
}
