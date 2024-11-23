package grave_escape.menu;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Provides functionalities to play, loop, and control the volume of an audio file using the Java Sound API.
 * Supports loading an audio file, playing it in a continuous loop, adjusting the playback volume within a 0 to 100 percentage range, and stopping the playback.
 */
public class MusicController {

    private static MusicController musicController;

    private Clip audioClip;
    private FloatControl volumeControl;

    /**
     * The singleton MusicController constructor
     */
    private MusicController() {

    }

    /**
     * The singleton MusicController
     * @return instance of the music controller
     */
    public static MusicController getInstance() {
        if(musicController == null) {
            musicController = new MusicController();
        }
        return musicController;
    }

    /**
     * Loads the specified audio file from a URL and starts playback in a continuous loop.
     * Initializes the audio clip, sets it to loop continuously, and begins playback.
     * Also sets up a listener to release resources when playback stops.
     *
     * @param audioUrl the URL of the audio file to be played
     * @throws IllegalArgumentException if the URL is null
     * @throws RuntimeException         if an error occurs during audio playback
     */
    public void playMusic(URL audioUrl) {
        if (audioUrl == null) {
            throw new IllegalArgumentException("Audio URL cannot be null.");
        }
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioUrl);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);

            // Set loop
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);

            // Get volume control
            volumeControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);

            // Start playback
            audioClip.start();

            // Add listener to release resources
            audioClip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    audioClip.close();
                    try {
                        audioStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException("Error playing the audio file.", e);
        }
    }

    /**
     * Adjusts the playback volume to the specified level.
     * The volume is set based on a percentage value between 0 and 100,
     * where 0% represents the minimum volume (mute) and 100% represents
     * the maximum volume. The method maps this percentage to the decibel
     * scale used by the FloatControl.
     *
     * @param volumePercentage the desired volume level as a percentage (0 to 100)
     * @throws IllegalArgumentException if volumePercentage is outside the range 0 to 100
     */
    public void setVolume(int volumePercentage) {
        if (volumePercentage < 0 || volumePercentage > 100) {
            throw new IllegalArgumentException("Volume percentage must be between 0 and 100.");
        }
        if (volumeControl != null) {
            float min = volumeControl.getMinimum(); // Typically -80.0 dB
            float max = volumeControl.getMaximum(); // Typically 6.0 dB
            float range = max - min;
            float gain = (range * volumePercentage / 100.0f) + min;
            volumeControl.setValue(gain);
        }
    }

    /**
     * Stops the audio playback if it is currently running.
     * Stops the playback of the audio clip and releases any
     * system resources associated with it.
     */
    public void stopMusic() {
        if (audioClip != null && audioClip.isRunning()) {
            audioClip.stop();
            audioClip.close();
        }
    }
}