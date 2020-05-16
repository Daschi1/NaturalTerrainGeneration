package de.daschi.core.math;

public class MathHelper {

    public static float generateNoise(final int seed, final float x, final float y, final int octaves, final float persistence, final float lacunarity, final float targetMin, final float targetMax, final FastNoise.NoiseType noiseType) {
        final FastNoise fastNoise = new FastNoise(seed);
        fastNoise.SetFractalOctaves(octaves);
        fastNoise.SetFractalGain(persistence);
        fastNoise.SetFractalLacunarity(lacunarity);
        fastNoise.SetNoiseType(noiseType);
        fastNoise.SetCellularReturnType(FastNoise.CellularReturnType.CellValue);
        fastNoise.SetCellularDistanceFunction(FastNoise.CellularDistanceFunction.Natural);
        return MathHelper.scaleToRange(fastNoise.GetNoise(x, y), -1, 1, targetMin, targetMax);
    }

    public static float cellularNoiseDistanceLookup(final int seed, final float x, final float y) {
        final FastNoise fastNoise = new FastNoise(seed);
        fastNoise.SetNoiseType(FastNoise.NoiseType.Cellular);
        fastNoise.SetCellularReturnType(FastNoise.CellularReturnType.Distance2);
        fastNoise.SetCellularDistanceFunction(FastNoise.CellularDistanceFunction.Natural);
        return fastNoise.GetNoise(x, y);
    }

    public static float scaleToRange(final float value, final float valueMin, final float valueMax, final float targetMin, final float targetMax) {
        return (value - valueMin) / (valueMax - valueMin) * (targetMax - targetMin) + targetMin;
    }
}
