package utility;

import java.io.File;

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
			audio = AudioSystem.getAudioInputStream(new File(dir));
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
