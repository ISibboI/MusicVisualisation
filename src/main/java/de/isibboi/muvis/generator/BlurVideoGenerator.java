package de.isibboi.muvis.generator;

public class BlurVideoGenerator extends FloatBufferedVideoGenerator {
	private double lastBeat;
	private boolean once = true;
	
	private final EffectQueue effects = new EffectQueue();

	public BlurVideoGenerator() {
		effects.addEffect(new ExponentialFadeEffect(0.05));
		effects.addEffect(new MatrixEffect(new float[]{
				0.025f, 0.05f, 0.05f,
				0.125f, 0.05f, 0.05f,
				0.5f, 0.125f, 0.025f}, 3, 3, WrapMode.CLAMP));
		effects.addEffect(new PixelNoiseRingEffect(0.5, 2400, getSinTable()));
	}

	@Override
	protected FloatImage generateFrame(FloatImage image, double beat) {
		if (once) {
			once = false;
			lastBeat = beat;
			return image;
		}

		image = effects.applyEffectQueue(image, beat, lastBeat);
		
		lastBeat = beat;
		return image;
	}
}