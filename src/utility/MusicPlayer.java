package utility;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer extends Thread {
	
	public MusicPlayer(String dir) {
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(new File(dir));
			Clip clip = AudioSystem.getClip();
			clip.open(audio);
			clip.start();

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
