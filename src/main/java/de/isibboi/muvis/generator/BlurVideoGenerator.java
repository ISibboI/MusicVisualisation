package de.isibboi.muvis.generator;

public class BlurVideoGenerator extends FloatBufferedVideoGenerator {
	private double lastBeat;
	private boolean once = true;

	@Override
	protected void generateFrame(FloatImage image, double beat) {
		if (once) {
			once = false;
			lastBeat = beat;
			return;
		}

		
	}
}