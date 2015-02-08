package de.isibboi.muvis;

import java.awt.image.BufferedImage;

public interface VideoGenerator {
	void generateFrame(BufferedImage buffer, double beat);
}
