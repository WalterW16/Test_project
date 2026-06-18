package ua.edu.tntu._121_se.midipiano.ui;

import javax.swing.*;
import java.awt.*;

public class InstrumentOctaveScreen extends JPanel {

    public static final int SCREEN_WIDTH = 250;
    public static final int SCREEN_HEIGHT = 80;

    private static final int TEXT_FONT_SIZE = 18;
    private static final Color COLOR = Color.LIGHT_GRAY;
    private static final Color COLOR_TEXT = Color.BLACK;
    private static final String TEXT_FONT = "Arial";

    private final JLabel instrumentLabel;
    private final JLabel octaveLabel;

    public InstrumentOctaveScreen() {

        Dimension size = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);

        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        setBackground(COLOR);
        setLayout(new GridLayout(2, 1));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        Font font = new Font(TEXT_FONT, Font.BOLD, TEXT_FONT_SIZE);

        instrumentLabel = new JLabel("Piano", SwingConstants.CENTER);
        instrumentLabel.setFont(font);
        instrumentLabel.setForeground(COLOR_TEXT);

        octaveLabel = new JLabel("Octave: 0", SwingConstants.CENTER);
        octaveLabel.setFont(font);
        octaveLabel.setForeground(COLOR_TEXT);

        add(instrumentLabel);
        add(octaveLabel);
    }

    public void setInstrument(String instrument) {
        instrumentLabel.setText(convertStringToTitle(instrument));
    }

    public void setOctave(int octave) {
        octaveLabel.setText("Octave: " + octave);
    }

    //AI-Assisted code: Gemini Pro - method to convert to Title case
    private String convertStringToTitle(String input){
        if (input == null || input.isEmpty()) {
            return input;
        }
        StringBuilder titleCase = new StringBuilder();
        boolean nextCharShouldBeCapital = true;

        for (char c : input.toCharArray()) {
            if (Character.isWhitespace(c)) {
                nextCharShouldBeCapital = true;
                titleCase.append(c);
            } else if (nextCharShouldBeCapital) {
                titleCase.append(Character.toUpperCase(c));
                nextCharShouldBeCapital = false;
            } else {
                titleCase.append(Character.toLowerCase(c));
            }
        }
        return titleCase.toString().replace('_', ' ');
    }
}