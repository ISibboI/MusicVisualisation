package de.isibboi.muvis.generator;

public class MatrixEffect extends VideoEffect {
	private final float[] matrix;
	private final int width;
	private final int height;
	private final WrapMode wrapMode;
	
	/**
	 * @param matrix Line wise effect matrix
	 * @param width
	 * @param height
	 * @param wrapMode
	 */
	public MatrixEffect(float[] matrix, int width, int height, WrapMode wrapMode) {
		this.matrix = matrix;
		this.width = width;
		this.height = height;
		this.wrapMode = wrapMode;
		
		if (width % 2 != 1 || width < 1) {
			throw new IllegalArgumentException("width must be odd and greater than zero.");
		}
		
		if (height % 2 != 1 || height < 1) {
			throw new IllegalArgumentException("height must be odd and greater than zero.");
		}
	}
	
	@Override
	protected FloatImage applyEffectInternal(FloatImage image, double beat, double lastBeat) {
		FloatImage result = new FloatImage(image);
		Color3f c = new Color3f();
		
		for (int x = 0; x < result.getWidth(); x++) {
			for (int y = 0; y < result.getHeight(); y++) {
				c.setBlack();
				
				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {
						c.add(image.getPixel(x + (i - width / 2), y + (j - height / 2), wrapMode).multiply(matrix[i + j * width]));
					}
				}
				
				result.setPixel(x, y, c);
			}
		}
		
		return result;
	}
}