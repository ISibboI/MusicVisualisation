package de.isibboi.muvis.generator;

import java.util.LinkedList;
import java.util.List;

public class EffectQueue {
	private List<VideoEffect> effects = new LinkedList<>();
	
	public boolean addEffect(VideoEffect effect) {
		return effects.add(effect);
	}
	
	public FloatImage applyEffectQueue(FloatImage image, double beat, double lastBeat) {
		for (VideoEffect effect : effects) {
			image = effect.applyEffect(image, beat, lastBeat);
		}
		
		return image;
	}
}
