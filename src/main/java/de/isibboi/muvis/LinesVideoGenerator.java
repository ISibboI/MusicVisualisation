package de.isibboi.muvis;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class LinesVideoGenerator extends AbstractVideoGenerator {
	private double lastBeat = 0;
	private boolean once = true;
	
	@Override
	public void generateFrame(BufferedImage buffer, double beat) {
		if (once) {
			lastBeat = beat;
			once = false;
			return;
		}
		
		simpleFade(buffer, (byte) 1);
		
		Graphics2D g = buffer.createGraphics();
		g.setColor(Color.WHITE);
		Rectangle space = createSpace(buffer);
		
		final int lineCount = (int) (Math.floor(beat) - Math.ceil(lastBeat) + 1) * 10;
		
		for (int i = 0; i < lineCount; i++) {
			randomLine(g, space);
		}
		
		lastBeat = beat;
	}
}
