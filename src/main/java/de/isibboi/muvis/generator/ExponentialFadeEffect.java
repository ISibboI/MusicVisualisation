package de.isibboi.muvis.generator;

public class ExponentialFadeEffect extends VideoEffect {
	private final double strength;
	
	public ExponentialFadeEffect(double d) {
		this.strength = d;
	}
	
	@Override
	protected FloatImage applyEffectInternal(FloatImage image, double beat, double lastBeat) {
		final float factor = (float) Math.exp(strength * (lastBeat - beat));
		
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				image.setPixel(x, y, image.getPixel(x, y).multiply(factor));
			}
		}
		
		return image;
	}
}