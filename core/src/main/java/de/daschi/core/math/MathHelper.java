package de.daschi.core.math;

public class MathHelper {

    public static int generatePerlinNoise(final int seed, final int x, final int y, final int octaves, final float persistence, final float lacunarity, final float targetMin, final float targetMax, final FastNoise.NoiseType noiseType) {
        final FastNoise fastNoise = new FastNoise(seed);
        fastNoise.SetFractalOctaves(octaves);
        fastNoise.SetFractalGain(persistence);
        fastNoise.SetFractalLacunarity(lacunarity);
        fastNoise.SetNoiseType(noiseType);
        final float noise = MathHelper.scaleToRange(fastNoise.GetNoise(x, y), -1, 1, 0, 1);
        return Math.round(MathHelper.scaleToRange(noise, 0, 1, targetMin, targetMax));
    }

    public static float scaleToRange(final float value, final float valueMin, final float valueMax, final float targetMin, final float targetMax) {
        return (value - valueMin) / (valueMax - valueMin) * (targetMax - targetMin) + targetMin;
    }
}
