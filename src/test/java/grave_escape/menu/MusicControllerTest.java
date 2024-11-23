package grave_escape.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import static org.mockito.Mockito.*;

public class MusicControllerTest {

    @Mock
    private Clip mockClip;

    @Mock
    private FloatControl mockVolumeControl;

    @InjectMocks
    private MusicController musicController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockClip.getControl(FloatControl.Type.MASTER_GAIN)).thenReturn(mockVolumeControl);
    }

    @Test
    public void testSetVolume() {
        when(mockVolumeControl.getMinimum()).thenReturn(-80.0f);
        when(mockVolumeControl.getMaximum()).thenReturn(6.0f);
        musicController.setVolume(50);
        verify(mockVolumeControl, times(1)).setValue(anyFloat());
    }

    @Test
    public void testStopMusic() {
        when(mockClip.isRunning()).thenReturn(true);
        musicController.stopMusic();
        verify(mockClip, times(1)).stop();
        verify(mockClip, times(1)).close();
    }
}