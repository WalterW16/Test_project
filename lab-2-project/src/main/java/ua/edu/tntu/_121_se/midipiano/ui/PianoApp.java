package ua.edu.tntu._121_se.midipiano.ui;

import ua.edu.tntu._121_se.midipiano.ui.pianokeys.PianoKeysUI;
import ua.edu.tntu._121_se.midipiano.piano.PianoKey;
import ua.edu.tntu._121_se.midipiano.piano.PianoMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

public class PianoApp extends JFrame {
    private final int KEY_SHIFT_PITCH_DOWN = KeyEvent.VK_C;
    private final int KEY_SHIFT_PITCH_UP = KeyEvent.VK_V;
    private final int KEY_REVERB = KeyEvent.VK_SPACE;
    private final int KEY_SWITCH_INSTRUMENT = KeyEvent.VK_I;
    private final int KEY_RECORD = KeyEvent.VK_R;
    private final int KEY_PLAY = KeyEvent.VK_P;

    private final PianoKeyLayout pianoKeyLayout = PianoKeyLayouts.NumberRowLayout;
    private final HashSet<Integer> keysPressed = new HashSet<>();
    private final PianoMachine piano;
    private final PianoKeysUI keysUI;
    private final InstrumentOctaveScreen screen;
    private final ControlsLayout controls;
    private final RecordPlayLayout recordPlayLayout;
    private boolean reverbEnabled = false;

    //AI-Assisted code: Gemini Pro - Constructor of frame to combine all layouts with correct parameters
    public PianoApp(PianoMachine piano) {
        this.piano = piano;

        setTitle("Midi Piano");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        controls = new ControlsLayout();
        controls.setBackground(Color.DARK_GRAY);
        add(controls, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        recordPlayLayout = new RecordPlayLayout();
        recordPlayLayout.setBackground(Color.DARK_GRAY);
        rightPanel.setBackground(Color.DARK_GRAY);
        rightPanel.add(recordPlayLayout, BorderLayout.EAST);
        add(rightPanel, BorderLayout.EAST);

        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(Color.DARK_GRAY);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        topPanel.setBackground(Color.DARK_GRAY);
        screen = new InstrumentOctaveScreen();
        topPanel.add(screen);
        mainContent.add(topPanel, BorderLayout.NORTH);

        JPanel pianoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        keysUI = new PianoKeysUI(pianoKeyLayout);
        pianoPanel.setBackground(Color.DARK_GRAY);
        pianoPanel.add(keysUI);
        mainContent.add(pianoPanel, BorderLayout.SOUTH);

        add(mainContent, BorderLayout.CENTER);

        int totalWidth = pianoPanel.getPreferredSize().width + (controls.getPreferredSize().width * 2);
        setSize(totalWidth, 450);

        getContentPane().setBackground(Color.DARK_GRAY);
        setUpKeyListeners();
    }

    public void beginNote(PianoKey note) {
        piano.beginNote(note.getPitch());
        keysUI.pressKey(note);
    }

    public void endNote(PianoKey note) {
        piano.endNote(note.getPitch());
        keysUI.releaseKey(note);
    }

    public void enableReverb() {
        reverbEnabled = true;
    }

    public void switchInstrument() {
        piano.changeInstrument();
        screen.setInstrument((piano.getInstrument()).name());
    }

    public void disableReverb() {
        reverbEnabled = false;
    }

    public void shiftScaleUp() {
        piano.shiftUp();
        screen.setOctave(piano.getOctaveOffset());
    }

    public void shiftScaleDown() {
        piano.shiftDown();
        screen.setOctave(piano.getOctaveOffset());
    }

    public void toggleRecording(){
        piano.toggleRecording();
        recordPlayLayout.record();
    }

    public void playback(){
        recordPlayLayout.play();
        piano.playback(() -> {
            //AI-Assisted code: Gemini Pro - to properly call method after playback is over
            SwingUtilities.invokeLater(() -> {
                recordPlayLayout.play();
            });
        });
    }

    private void setUpKeyListeners() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                // Apply reverb
                if (keyCode == KEY_REVERB && !reverbEnabled) {
                    enableReverb();
                }

                // Check if key is already pressed
                if (keysPressed.contains(keyCode)) {
                    return;
                }
                keysPressed.add(keyCode);

                PianoKey note = pianoKeyLayout.getPianoKey(keyCode);
                if (note != null) {
                    beginNote(note);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();

                keysPressed.remove(keyCode);

                if (keyCode == KEY_SHIFT_PITCH_DOWN) {  // Shift octave down
                    shiftScaleDown();
                } else if (keyCode == KEY_SHIFT_PITCH_UP) { // Shift octave up
                    shiftScaleUp();
                } else if (keyCode == KEY_SWITCH_INSTRUMENT) { //Switch to the next instrument
                    switchInstrument();
                }
                else if (keyCode == KEY_REVERB && reverbEnabled) { // Release reverb
                    disableReverb();
                }else if(keyCode == KEY_RECORD){ //Toggle recording
                    toggleRecording();
                }else if(keyCode ==KEY_PLAY){ //Play recording
                    playback();
                }

                // Release note
                PianoKey note = pianoKeyLayout.getPianoKey(keyCode);
                if (note != null) {
                    endNote(note);
                }
            }
        });
    }
}
