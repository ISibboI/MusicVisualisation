package de.isibboi.muvis.generator;

import java.util.Random;

public class RandomFillEffect extends VideoEffect {
	private final Random r = new Random();
	
	@Override
	protected FloatImage applyEffectInternal(FloatImage image, double beat, double lastBeat) {
		Color3f color = new Color3f(r.nextFloat(), r.nextFloat(), r.nextFloat());
		
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				image.setPixel(x, y, color);
			}
		}
		
		return image;
	}
}