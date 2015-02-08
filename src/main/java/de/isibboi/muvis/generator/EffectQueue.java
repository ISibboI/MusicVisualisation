package de.isibboi.muvis.generator;

import java.util.LinkedList;
import java.util.List;

public class EffectQueue {
	private List<VideoEffect> effects = new LinkedList<>();
	
	public boolean addEffect(VideoEffect effect) {
		return effects.add(effect);
	}
	
	public void applyEffectQueue(FloatImage image, double beat, double lastBeat) {
		for (VideoEffect effect : effects) {
			effect.applyEffect(image, beat, lastBeat);
		}
	}
}
