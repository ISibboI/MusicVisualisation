package de.isibboi.muvis.generator;

import java.util.Arrays;

public class GaussianBlurEffect extends VideoEffect {
	private final MatrixEffect matrixEffect;
	
	public GaussianBlurEffect(int level, double variance) {
		level = level * 2 + 1;
		float[] matrix = new float[level * level];
		float sum = 0;
		
		for (int i = 0; i < level; i++) {
			for (int j = 0; j < level; j++) {
				float f = (float) twoDimensionalGaussian(i - level / 2, j - level / 2, variance);
				matrix[i + j * level] = f;
				sum += f;
			}
		}
		
		for (int i = 0; i < level * level; i++) {
			matrix[i] /= sum;
		}
		
		System.out.println(Arrays.toString(matrix));
		
		matrixEffect = new MatrixEffect(matrix, level, level, WrapMode.CLAMP);
	}
	
	private double twoDimensionalGaussian(double x, double y, double variance) {
		variance *= variance;
		double result = 0.5 * Math.PI;
		result /= variance;
		result *= Math.exp(-0.5 / variance * (x * x + y * y));
		
		return result;
	}

	@Override
	protected FloatImage applyEffectInternal(FloatImage image, double beat, double lastBeat) {
		return matrixEffect.applyEffect(image, beat, lastBeat);
	}
}