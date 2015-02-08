package de.isibboi.muvis;

import de.isibboi.muvis.generator.BlurVideoGenerator;

public class MusicVisualisation {
	public static void main(String[] args) {
		VideoFrame video = new VideoFrame(800, 600);
		video.setVideoGenerator(new BlurVideoGenerator());
		
		Thread t = new Thread(video);
		t.start();
	}
}