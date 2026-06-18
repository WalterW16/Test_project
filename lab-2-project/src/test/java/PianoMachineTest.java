import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import javax.sound.midi.MidiUnavailableException;
import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import ua.edu.tntu._121_se.midipiano.midi.Instrument;
import ua.edu.tntu._121_se.midipiano.midi.Midi;
import ua.edu.tntu._121_se.midipiano.music.Pitch;
import ua.edu.tntu._121_se.midipiano.piano.PianoMachine;
import ua.edu.tntu._121_se.midipiano.music.NoteEvent;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PianoMachineTest {

    PianoMachine pm;

    @Test
    public void singleNoteTest() throws MidiUnavailableException {
        // Arrange
        pm = new PianoMachine();
        String expected0 = "on(62,PIANO) wait(100) off(62,PIANO)";
        Midi midi = Midi.getInstance();
        midi.clearHistory();

        // Act
        pm.beginNote(new Pitch(2));
        Midi.wait(100);
        pm.endNote(new Pitch(2));

        // Assert
        assertEquals(expected0, midi.history());
    }

    @Test
    public void multipleNotesWithDelayTest() throws MidiUnavailableException {
        // Arrange
        pm = new PianoMachine();
        String expected = "on(61,PIANO) wait(500) on(62,PIANO) wait(500) off(61,PIANO) wait(500) off(62,PIANO)";
        Midi midi = Midi.getInstance();
        midi.clearHistory();

        // Act
        pm.beginNote(new Pitch(1));
        Midi.wait(500);

        pm.beginNote(new Pitch(2));
        Midi.wait(500);

        pm.endNote(new Pitch(1));
        Midi.wait(500);

        pm.endNote(new Pitch(2));

        // Assert
        assertEquals(expected, midi.history());
    }

    @Test
    public void changeInstrumentTest() throws MidiUnavailableException {
        // Arrange
        pm = new PianoMachine();
        String defaultExpected = "on(62,PIANO) wait(100) off(62,PIANO)";
        Midi midi = Midi.getInstance();
        midi.clearHistory();

        // Act
        pm.changeInstrument();
        pm.beginNote(new Pitch(2));
        Midi.wait(50);
        pm.endNote(new Pitch(2));

        // Assert
       org.junit.jupiter.api.Assertions.assertNotEquals(defaultExpected, midi.history());
    }

    @Test
    void shiftUpTest() throws MidiUnavailableException{
        // Arrange
        pm = new PianoMachine();
        String expected0 = "on(73,PIANO) wait(100) off(73,PIANO)";
        Midi midi = Midi.getInstance();
        midi.clearHistory();

        // Act
        pm.shiftUp();
        pm.beginNote(new Pitch(1));
        Midi.wait(100);
        pm.endNote(new Pitch(1));

        // Assert
        assertEquals(expected0, midi.history());
    }

    @Test
    void shiftDownTest() throws MidiUnavailableException{
        // Arrange
        pm = new PianoMachine();
        String expected0 = "on(49,PIANO) wait(100) off(49,PIANO)";
        Midi midi = Midi.getInstance();
        midi.clearHistory();

        // Act
        pm.shiftDown();
        pm.beginNote(new Pitch(1));
        Midi.wait(100);
        pm.endNote(new Pitch(1));

        // Assert
        assertEquals(expected0, midi.history());
    }

    @Test
    void shiftingLimitTest() throws MidiUnavailableException{
        // Arrange
        pm = new PianoMachine();
        String expected0 = "on(37,PIANO) wait(100) off(37,PIANO)";
        Midi midi = Midi.getInstance();
        midi.clearHistory();

        // Act
        pm.shiftDown();
        pm.shiftDown();
        pm.shiftDown();
        pm.beginNote(new Pitch(1));
        Midi.wait(100);
        pm.endNote(new Pitch(1));

        // Assert
        assertEquals(expected0, midi.history());
    }

    @Test
    void toggleRecordingTest() {
        //Arrange
        pm = new PianoMachine();
        NoteEvent.Kind expected1 = NoteEvent.Kind.start;
        NoteEvent.Kind expected2 = NoteEvent.Kind.stop;

        //Act
        pm.toggleRecording();
        pm.beginNote(new Pitch(1));
        Midi.wait(100);
        pm.endNote(new Pitch(1));
        pm.toggleRecording();

        LinkedList<NoteEvent> actual = pm.getRecorded();
        NoteEvent startEvent = actual.get(0);
        NoteEvent stopEvent = actual.get(1);

        // Assert
        assertEquals(2, actual.size());
        assertEquals(expected1, startEvent.getKind());
        assertEquals(expected2, stopEvent.getKind());
        assertTrue(stopEvent.getTime() >= 1000);
        assertTrue(stopEvent.getTime() < 1050);
    }
    //AI-Assisted code: Gemini Pro - Test for method with thread logic
    @Test
    void playbackTest() throws MidiUnavailableException, InterruptedException{
        //Arrange
        Midi midi = Midi.getInstance();
        midi.clearHistory();
        PianoMachine pm = new PianoMachine();
        Pitch middleC = new Pitch(0);
        //Act
        pm.toggleRecording();
        pm.beginNote(middleC);
        // AI-assisted code: ChatGPT - детермінізація часу в тесті playbackTest
        Midi.wait(100);
        pm.endNote(middleC);
        pm.toggleRecording();
        LinkedList<NoteEvent> recordedEvents = pm.getRecorded();
        // Assert
        assertEquals(2, recordedEvents.size());
        // AI-assisted code: ChatGPT - використання latch для очікування завершення відтворення
        CountDownLatch latch = new CountDownLatch(1);
        assertDoesNotThrow(() -> pm.playback(latch::countDown));
        assertTrue(latch.await(2, TimeUnit.SECONDS));
        
        assertTrue(midi.history().isEmpty());
    }}
