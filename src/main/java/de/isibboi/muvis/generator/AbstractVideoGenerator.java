package de.isibboi.muvis.generator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class AbstractVideoGenerator implements VideoGenerator {
	protected final Random r = new Random();

	protected void simpleFade(BufferedImage image, byte strength) {
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int rgb = image.getRGB(x, y);
				byte r = (byte) (rgb >> 16);
				byte g = (byte) (rgb >> 8);
				byte b = (byte) (rgb >> 0);

				if (r >= strength || r < 0) {
					r -= strength;
				}

				if (g >= strength || g < 0) {
					g -= strength;
				}

				if (b >= strength || b < 0) {
					b -= strength;
				}

				rgb = 0xFF000000 | ((r << 16) & 0xFF0000) | ((g << 8) & 0xFF00)
						| ((b << 0) & 0xFF);

				image.setRGB(x, y, rgb);
			}
		}
	}

	protected void perPixelOp(BufferedImage image, PerPixelOp perPixelOp) {
		Color3f c = new Color3f();
		
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int rgb = image.getRGB(x, y);
				c.r = (rgb >> 16) & 0xFF;
				c.g = (rgb >> 8) & 0xFF;
				c.b = (rgb >> 0) & 0xFF;
				
				perPixelOp.perPixelOp(c);
				
				rgb = 0xFF000000 | ((((int) c.r) << 16) & 0xFF0000)
						| ((((int) c.g) << 8) & 0xFF00)
						| ((((int) c.b) << 0) & 0xFF);

				image.setRGB(x, y, rgb);
			}
		}
	}

	protected void randomLine(Graphics2D g, Rectangle space) {
		int x1 = r.nextInt(space.width) + space.x;
		int y1 = r.nextInt(space.height) + space.y;
		int x2 = r.nextInt(space.width) + space.x;
		int y2 = r.nextInt(space.height) + space.y;

		g.drawLine(x1, y1, x2, y2);
	}

	protected Rectangle createSpace(BufferedImage image) {
		return new Rectangle(0, 0, image.getWidth(), image.getHeight());
	}
}