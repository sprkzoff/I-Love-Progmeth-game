package utility;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer extends Thread {
	private String dir;
	private Clip clip;
	private AudioInputStream audio;

	public MusicPlayer(String dir) {
		this.dir = dir;
	}

	@Override
	public void start() {
		try {
			URL url = getClass().getClassLoader().getResource(dir);
			audio = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audio);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doStop() {
		clip.stop();
	}
}
