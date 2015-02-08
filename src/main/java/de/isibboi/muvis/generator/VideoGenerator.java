package de.isibboi.muvis.generator;

import java.awt.image.BufferedImage;

public interface VideoGenerator {
	void generateFrame(BufferedImage buffer, double beat);
}
