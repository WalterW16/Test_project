package ua.edu.tntu._121_se.midipiano.piano;

import javax.sound.midi.MidiUnavailableException;

import ua.edu.tntu._121_se.midipiano.midi.Instrument;
import ua.edu.tntu._121_se.midipiano.midi.Midi;
import ua.edu.tntu._121_se.midipiano.music.NoteEvent;
import ua.edu.tntu._121_se.midipiano.music.Pitch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PianoMachine {

    private static final int maxOctavesOffset = 2;
    private Midi midi;
    private Instrument instrument = Instrument.PIANO;
    private int octaveOffset = 0;
    private boolean recording = false;
    private long recordingTime = 0;
    private LinkedList<NoteEvent> recorded = new LinkedList<>();

    /**
     * constructor for PianoMachine.
     * initialize midi device and any other state that we're storing.
     */
    public PianoMachine() {
        try {
            midi = Midi.getInstance();
        } catch (MidiUnavailableException e1) {
            System.err.println("Could not initialize midi device");
            e1.printStackTrace();
        }
    }

    /**
     * Begin playing a note in the current octave
     * @param rawPitch a pitch to be played
     */
    public void beginNote(Pitch rawPitch) {
        Pitch actualPitch = getPitch(rawPitch);

        midi.beginNote(actualPitch.toMidiFrequency(), instrument);
        if (recording) {
            long time = System.currentTimeMillis() - recordingTime;
            recorded.add(new NoteEvent(actualPitch, time, instrument, NoteEvent.Kind.start));
        }
    }

    /**
     * Stop playing a note in the current octave
     * @param rawPitch a pitch to stop playing
     */
    public void endNote(Pitch rawPitch) {
        Pitch actualPitch = getPitch(rawPitch);
        midi.endNote(actualPitch.toMidiFrequency(), instrument);
        if (recording) {
            long time = System.currentTimeMillis() - recordingTime;
            recorded.add(new NoteEvent(actualPitch, time, instrument, NoteEvent.Kind.stop));
        }
    }

    /**
     * Switch the instrument played by the machine to the next one available
     */
    public void changeInstrument() {
        instrument = instrument.next();
    }

    /**
     * Shift the PianoMachine note scale up by one octave
     */
    public void shiftUp() {
        if (octaveOffset < maxOctavesOffset) {
            octaveOffset++;
        }
    }

    /**
     * Shift the PianoMachine note scale down by one octave
     */
    public void shiftDown() {
        if (octaveOffset > -maxOctavesOffset) {
            octaveOffset--;
        }
    }

    // AI-assisted code: ChatGPT - виправлення орфографії в документації
    /**
     * Change recording state when called,
     * if status is positive, clear recorded event list and
     * mark current time as start of recording
     * @return state of recording
     */
    public boolean toggleRecording() {
        recording = !recording;
        if(recording){
            recorded.clear();
            recordingTime = System.currentTimeMillis();
        }
        return recording;
    }

    /**
     *Play recording when called,
     *using saved NoteEvent list
     */
    public void playback( Runnable onFinished) {
        new Thread(() -> {
            long lastTime = 0;

            for (NoteEvent event : recorded) {
                try {
                    Thread.sleep(event.getTime() - lastTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (event.getKind() == NoteEvent.Kind.start) {
                    midi.beginNote(event.getPitch().toMidiFrequency(), event.getInstr());
                } else {
                    midi.endNote(event.getPitch().toMidiFrequency(), event.getInstr());
                }

                lastTime = event.getTime();
            }
            //AI-Assisted code: Gemini Pro - fragment to execute callback
            if (onFinished != null) {
                onFinished.run();
            }
        }).start();
    }

    /**
     * Calculating the actual pitch based on the raw pitch
     * and the current octave offset
     * @param rawPitch the base pitch of the key pressed
     * @return the transposed pitch according to the current octave offset
     */
    private Pitch getPitch(Pitch rawPitch) {
        return rawPitch.transpose(octaveOffset * Pitch.OCTAVE);
    }

    /**
     * Serve to provide current instrument
     * @return the current instrument
     */
    public Instrument getInstrument(){
        return instrument;
    }
    /**
     * Serve to provide current octave offset
     * @return the current octave offset
     */
    public int getOctaveOffset(){
        return octaveOffset;
    }
    /**
     * Serve to provide recorded list of NoteEvent
     * @return the recorded list of NoteEvent
     */
    // AI-assisted code: ChatGPT - захист від небезпечної мутації внутрішнього списку записів
    public LinkedList<NoteEvent> getRecorded(){
        return new LinkedList<>(recorded);
    }
}
