package de.isibboi.muvis.generator;

import java.util.Random;

public class PixelNoiseRingEffect extends VideoEffect {
	private final Random r = new Random();
	private final SinTable sin;
	private final double pixelsPerBeat;
	private final double frequency;

	public PixelNoiseRingEffect(double frequency, double pixelsPerBeat, SinTable sin) {
		this.frequency = frequency;
		this.pixelsPerBeat = pixelsPerBeat;
		this.sin = sin;
	}

	@Override
	protected FloatImage applyEffectInternal(final FloatImage image, final double beat, final double lastBeat) {
		final int pixelCount = (int) (Math.floor(beat * pixelsPerBeat) - Math.ceil(lastBeat * pixelsPerBeat)) + 1;
		System.out.println(pixelCount);

		if (pixelCount > 0) {
			float size = Math.min(image.getWidth(), image.getHeight());
			float centerX = image.getWidth() / 2;
			float centerY = image.getHeight() / 2;

			for (int i = 0; i < pixelCount; i++) {
				float angle = (float) (2 * Math.PI * r.nextDouble());
				float radius = (float) (r.nextGaussian() * size / 64) + size / 4 + size / 8 * sin.sin((float) (2 * Math.PI * beat * frequency));
				Color3f c = new Color3f(0.5f + 0.5f * r.nextFloat(), r.nextFloat(), 1);

				int x = (int) (centerX + sin.cos(angle) * radius);
				int y = (int) (centerY + sin.sin(angle) * radius);

				image.setPixel(x, y, c, MissMode.MOVE_INSIDE);
			}
		}
		
		return image;
	}
}
