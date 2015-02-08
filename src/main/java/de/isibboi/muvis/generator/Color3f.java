package de.isibboi.muvis.generator;

public class Color3f {
	public float r;
	public float g;
	public float b;

	public Color3f(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public Color3f() {
	}

	public int getRGB() {
		int r = (int) (this.r * 256);

		if (r == 256) {
			r = 255;
		}

		int g = (int) (this.g * 256);

		if (g == 256) {
			g = 255;
		}

		int b = (int) (this.b * 256);

		if (b == 256) {
			b = 255;
		}

		return 0xFF000000 | ((((int) r) << 16) & 0xFF0000) | ((((int) g) << 8) & 0xFF00) | ((((int) b) << 0) & 0xFF);
	}
}