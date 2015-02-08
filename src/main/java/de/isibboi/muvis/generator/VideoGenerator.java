package de.isibboi.muvis.generator;

import java.awt.image.BufferedImage;

public interface VideoGenerator {
	BufferedImage generateFrame(BufferedImage buffer, double beat);
}
