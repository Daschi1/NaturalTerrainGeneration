package de.daschi.visualization.emulator;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.List;

public class EmulatedChunk {
    private static final List<EmulatedChunk> emulatedChunks = new ArrayList<>();

    public static void updateChunkMesh(final Canvas canvas) {
        EmulatedChunk.emulatedChunks.clear();
        final double width = canvas.getWidth();
        final int widthChunks = (int) Math.ceil(width / 16);
        final double height = canvas.getHeight();
        final int heightChunks = (int) Math.ceil(height / 16);

        for (int i = 0; i < widthChunks; i++) {
            for (int j = 0; j < heightChunks; j++) {
                final EmulatedChunk emulatedChunk = new EmulatedChunk(i * 16, j * 16);
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        canvas.getGraphicsContext2D().getPixelWriter().setColor(x + emulatedChunk.getOriginX(), z + emulatedChunk.getOriginZ(), emulatedChunk.getEmulatedBlocks()[x * z].getEmulatedMaterial().getColor());
                    }
                }
            }
        }
    }

    public static List<EmulatedChunk> getEmulatedChunks() {
        return EmulatedChunk.emulatedChunks;
    }

    private final int originX;
    private final int originZ;
    private final EmulatedBlock[] emulatedBlocks;

    public EmulatedChunk(final int originX, final int originZ) {
        this.originX = originX;
        this.originZ = originZ;
        this.emulatedBlocks = new EmulatedBlock[16 * 1 * 16];

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                this.emulatedBlocks[x * z] = new EmulatedBlock(EmulatedMaterial.DEEP_WATER);
            }
        }

        EmulatedChunk.emulatedChunks.add(this);
    }

    public int getOriginX() {
        return this.originX;
    }

    public int getOriginZ() {
        return this.originZ;
    }

    public EmulatedBlock[] getEmulatedBlocks() {
        return this.emulatedBlocks;
    }
}
