package grave_escape.game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * This class contains helper functions for playing audio files
 */
public class AudioUtils {
    /**
     * Default constructor for the Audio class. The class provides static utility methods and does not require
     * instantiation.
     */
    public AudioUtils(){
        // Default constructor, no instantiation required
    }

    /**
     * This helper function plays a single audio file.
     * @param filePath: The file path for the audio file
     */
    public static void playAudio(String filePath){
        try{
            URL audioURL = AudioUtils.class.getResource(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioURL);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace();
        }
    }
}
