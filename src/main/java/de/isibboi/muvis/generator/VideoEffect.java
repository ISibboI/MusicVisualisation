package de.isibboi.muvis.generator;

public abstract class VideoEffect {
	private boolean enabled = true;
	
	public FloatImage applyEffect(FloatImage image, double beat, double lastBeat) {
		if (enabled) {
			return applyEffectInternal(image, beat, lastBeat);
		} else {
			return image;
		}
	}

	protected abstract FloatImage applyEffectInternal(FloatImage image, double beat, double lastBeat);

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}