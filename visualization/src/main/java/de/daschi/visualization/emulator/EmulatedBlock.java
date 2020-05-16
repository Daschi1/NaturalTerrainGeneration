package de.daschi.visualization.emulator;

public class EmulatedBlock {
    private final EmulatedMaterial emulatedMaterial;

    public EmulatedBlock(final EmulatedMaterial emulatedMaterial) {
        this.emulatedMaterial = emulatedMaterial;
    }

    public EmulatedMaterial getEmulatedMaterial() {
        return this.emulatedMaterial;
    }
}
