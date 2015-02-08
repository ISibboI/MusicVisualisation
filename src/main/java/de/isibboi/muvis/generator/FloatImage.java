package de.isibboi.muvis.generator;

public class FloatImage {
	private final float[] pixels;
	private final int width;
	private final int height;

	public FloatImage(int width, int height) {
		pixels = new float[3 * width * height];
		this.width = width;
		this.height = height;
	}

	public FloatImage(FloatImage image) {
		this(image.getWidth(), image.getHeight());
	}

	public float getValue(int x, int y, int c) {
		return pixels[3 * (x + y * width) + c];
	}

	public void setValue(int x, int y, int c, float value) {
		pixels[3 * (x + y * width) + c] = value;
	}

	public Color3f getPixel(int x, int y) {
		return new Color3f(getValue(x, y, 0), getValue(x, y, 1), getValue(x, y, 2));
	}

	public void setPixel(int x, int y, Color3f color) {
		setValue(x, y, 0, color.r);
		setValue(x, y, 1, color.g);
		setValue(x, y, 2, color.b);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[] toRGBBuffer(int[] buffer) {
		if (buffer.length < width * height) {
			buffer = new int[width * height];
		}

		Color3f c = new Color3f();

		for (int i = 0; i < width * height; i++) {
			c.r = pixels[i * 3 + 0];
			c.g = pixels[i * 3 + 1];
			c.b = pixels[i * 3 + 2];
			buffer[i] = c.getRGB();
		}

		return buffer;
	}

	public Color3f getPixel(int x, int y, WrapMode wrap) {
		switch (wrap) {
		case ZERO:
			if (x < 0 || y < 0 || x >= width || y >= height) {
				return new Color3f();
			} else {
				return getPixel(x, y);
			}

		case CLAMP:
			if (x < 0) {
				x = 0;
			}

			if (y < 0) {
				y = 0;
			}

			if (x >= width) {
				x = width - 1;
			}

			if (y >= height) {
				y = height - 1;
			}

			return getPixel(x, y);

		case REPEAT:
			x %= width;
			y %= height;

			if (x < 0) {
				x += width;
			}

			if (y < 0) {
				y += height;
			}

			return getPixel(x, y);

		default:
			throw new RuntimeException("Unknown WrapMode: " + wrap);
		}
	}

	public void setPixel(int x, int y, Color3f color, MissMode miss) {
		switch (miss) {
		case DROP:
			if (x >= 0 && y >= 0 && x < width && y < height) {
				setPixel(x, y, color);
			}

			break;

		case MOVE_INSIDE:
			if (x < 0) {
				x = 0;
			}

			if (y < 0) {
				y = 0;
			}

			if (x >= width) {
				x = width - 1;
			}

			if (y >= height) {
				y = height - 1;
			}

			setPixel(x, y, color);
			break;

		case REPEAT:
			x %= width;
			y %= height;

			if (x < 0) {
				x += width;
			}

			if (y < 0) {
				y += height;
			}

			setPixel(x, y, color);
			break;

		default:
			throw new RuntimeException("Unknown MissMode: " + miss);
		}
	}
}