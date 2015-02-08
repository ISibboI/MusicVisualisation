package de.isibboi.muvis.generator;

public abstract class VideoEffect {
	private boolean enabled = true;
	
	public void applyEffect(FloatImage image, double beat, double lastBeat) {
		if (enabled) {
			applyEffectInternal(image, beat, lastBeat);
		}
	}

	protected abstract void applyEffectInternal(FloatImage image, double beat, double lastBeat);

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}