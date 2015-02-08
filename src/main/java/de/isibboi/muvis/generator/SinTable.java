package de.isibboi.muvis.generator;

public class SinTable {
	private final float[] sin;

	public SinTable(int size) {
		sin = new float[size];
		fill();
	}

	private void fill() {
		for (int i = 0; i < sin.length; i++) {
			sin[i] = (float) Math.sin(i * 2 * Math.PI / sin.length);
		}
	}

	public float sin(float f) {
		if (!Float.isFinite(f)) {
			return Float.NaN;
		}

		f %= 2 * Math.PI;

		if (f < 0) {
			f += 2 * Math.PI;
		}

		f /= Math.PI * 2;
		f *= sin.length;
		
		return sin[(int) f];
	}

	public float cos(float f) {
		return sin((float) (f + Math.PI / 2));
	}
}