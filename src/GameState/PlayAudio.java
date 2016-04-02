package GameState;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlayAudio
{
	private final static int BUFFER_SIZE = 128000;

	public PlayAudio(int State) throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		String gongFile;
		if(State==1){
			gongFile = "resources/death.wav";
		}
		else{
			gongFile = "resources/civvy.wav";
		}
	    
	  
		File soundFile = new File(gongFile);
	    AudioInputStream wavFile = AudioSystem.getAudioInputStream(soundFile);
	    AudioFormat waveFileProperties = wavFile.getFormat();
	    DataLine.Info info = new DataLine.Info(SourceDataLine.class,
	waveFileProperties);
	    SourceDataLine speakers = (SourceDataLine) AudioSystem.getLine(info);
	    speakers.open(waveFileProperties);
	    speakers.start();
	
	    int nBytesRead = 0;
	    byte[] abData = new byte[BUFFER_SIZE];
	    while (nBytesRead != -1) {
	        try {
	            nBytesRead = wavFile.read(abData, 0, abData.length);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        if (nBytesRead >= 0) {
	            @SuppressWarnings("unused")
	            int nBytesWritten = speakers.write(abData, 0, nBytesRead);
	        }
	    }

	    speakers.drain();
	    speakers.close();
  }
}
