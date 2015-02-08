package de.isibboi.muvis.generator;

import java.awt.image.BufferedImage;

public abstract class FloatBufferedVideoGenerator implements VideoGenerator {
	private FloatImage image;
	private int[] rgb = new int[0];
	
	private final SinTable sin = new SinTable(1 << 13);
	
	@Override
	public BufferedImage generateFrame(BufferedImage buffer, double beat) {
		if (image == null) {
			image = new FloatImage(buffer.getWidth(), buffer.getHeight());
		}

		if (image.getWidth() != buffer.getWidth() || image.getHeight() != buffer.getHeight()) {
			resizeTo(buffer.getWidth(), buffer.getHeight());
		}
		
		image = generateFrame(image, beat);

		drawImageTo(buffer);
		return buffer;
	}
	
	protected abstract FloatImage generateFrame(FloatImage image, double beat);

	private void drawImageTo(BufferedImage buffer) {
		rgb = image.toRGBBuffer(rgb);
		buffer.setRGB(0, 0, buffer.getWidth(), buffer.getHeight(), rgb, 0, buffer.getWidth());
	}

	private void resizeTo(int newWidth, int newHeight) {
		FloatImage newImage = new FloatImage(newWidth, newHeight);

		for (int x = 0; x < newWidth; x++) {
			for (int y = 0; y < newHeight; y++) {
				int oldX = x * image.getWidth() / newWidth;
				int oldY = y * image.getHeight() / newHeight;

				newImage.setPixel(x, y, image.getPixel(oldX, oldY));
			}
		}

		image = newImage;
	}
	
	protected SinTable getSinTable() {
		return sin;
	}
}