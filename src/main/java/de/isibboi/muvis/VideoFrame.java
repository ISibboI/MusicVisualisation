package de.isibboi.muvis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.isibboi.muvis.generator.VideoGenerator;

@SuppressWarnings("serial")
public class VideoFrame extends JFrame implements Runnable {
	private static final int IMAGE_TYPE = BufferedImage.TYPE_INT_ARGB;

	private final JPanel content;

	private BufferedImage image;
	private VideoGenerator generator;
	private double beatsPerMinute = 90;

	private volatile boolean stop;

	public VideoFrame(Dimension d) {
		image = new BufferedImage(d.width, d.height, IMAGE_TYPE);
		makeBlack();

		content = new JPanel();
		content.setPreferredSize(d);
		content.setBackground(Color.BLACK);
		setContentPane(content);

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		createBufferStrategy(2);
	}

	public VideoFrame(int width, int height) {
		this(new Dimension(width, height));
	}

	public void stop() {
		stop = true;
	}

	@Override
	public void run() {
		stop = false;
		double beat = 0;
		long nanoTime = System.nanoTime();

		while (!stop) {
			long deltaTime = System.nanoTime() - nanoTime;
			nanoTime += deltaTime;

			beat += ((double) deltaTime) / 60e9 * beatsPerMinute;
			
			if (deltaTime > 50_000_000) {
				System.out.println("WARNING: Frame took longer than 50ms!");
			}
			
			if (generator != null) {
				generator.generateFrame(image, beat);
			} else {
				makeBlack();
			}

			BufferStrategy bs = getBufferStrategy();

			if (bs == null) {
				createBufferStrategy(2);
				bs = getBufferStrategy();

				if (bs == null) {
					throw new RuntimeException(
							"Cannot create bufferstrategy. Always getting null.");
				}
			}
			
			Graphics g = null;
			
			try {
				g = bs.getDrawGraphics();
				g.drawImage(image, getInsets().left, getInsets().top, null);
			} finally {
				g.dispose();
			}
			
			bs.show();
			Toolkit.getDefaultToolkit().sync();

			resizeImageIfNecessary();
		}
	}

	private void resizeImageIfNecessary() {
		if (image.getWidth() != content.getWidth()
				|| image.getHeight() != content.getHeight()) {
			BufferedImage newImage = new BufferedImage(content.getWidth(),
					content.getHeight(), IMAGE_TYPE);
			Graphics g = newImage.getGraphics();
			g.drawImage(image, 0, 0, newImage.getWidth(), newImage.getHeight(),
					0, 0, image.getWidth(), image.getHeight(), null);
			g.dispose();
			image = newImage;
		}
	}

	private void makeBlack() {
		Graphics g = image.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
		g.dispose();
	}

	public double getBeatsPerMinute() {
		return beatsPerMinute;
	}

	public void setBeatsPerMinute(double beatsPerMinute) {
		this.beatsPerMinute = beatsPerMinute;
	}

	public VideoGenerator getGenerator() {
		return generator;
	}

	public void setVideoGenerator(VideoGenerator generator) {
		this.generator = generator;
	}
}