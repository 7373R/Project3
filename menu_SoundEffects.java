package project3;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class menu_SoundEffects {
    
    private Clip musicClip;
    private FloatControl volumeControl;
    
    menu_SoundEffects(String fileName) {
        playAudio(fileName);
    }

    public void stop() {
        musicClip.stop();
    }
    
    private void playAudio(String fileName) {
        try {
            File musicFile = new File( fileName );
            if (!musicFile.exists()) {
                System.out.println("Audio file not found!");
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            musicClip = AudioSystem.getClip();
            musicClip.open(audioStream);

            // Get volume control
            if (musicClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                volumeControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
                setVolume(100); // Set default volume to 50% initially
            }

            musicClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop music
            musicClip.start(); // Start playback
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    public void switchAudio(String newFileName) {
        if (musicClip != null && musicClip.isOpen()) {
            musicClip.stop(); // Stop the current music
            musicClip.close(); // Close the current clip
        }

        playAudio(newFileName); // Play the new audio file
    }
    
    public void setVolume(int sliderValue) {
        if (volumeControl != null) {
            // Adjusted decibel range
            float adjustedMin = -30.0f; // Raise the minimum volume (e.g., from -80 dB to -20 dB)
            float adjustedMax = -13.0f; // Decrease the maximum volume (e.g., from 6 dB to -10 dB)

            // Map slider value (0-100) to adjusted decibel range
            float volume = adjustedMin + (sliderValue / 100f) * (adjustedMax - adjustedMin);

            if (volume <= adjustedMin) 
                volume = -80.0f; // Mute (set to -80 dB or lower)
            
            volumeControl.setValue(volume);
        }
    }
}
