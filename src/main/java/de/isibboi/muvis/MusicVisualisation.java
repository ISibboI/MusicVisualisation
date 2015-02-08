package de.isibboi.muvis;

public class MusicVisualisation {
	public static void main(String[] args) {
		VideoFrame video = new VideoFrame(800, 600);
		video.setVideoGenerator(new LinesVideoGenerator());
		
		Thread t = new Thread(video);
		t.start();
	}
}