package ua.edu.tntu._121_se.midipiano.ui;

import javax.swing.*;
import java.awt.*;

public class ControlsLayout extends JPanel {
    public static final int WIDTH = 150;
    public static final int HEIGHT = 400;

    private static final int TEXT_FONT_SIZE = 14;
    private static final Color COLOR_TEXT = Color.WHITE;
    private static final String TEXT_FONT = "Arial";

    // AI-assisted code: ChatGPT - рефакторинг назви змінної для підвищення читабельності
    private final JLabel controlsText;

    public ControlsLayout() {

        Dimension size = new Dimension(WIDTH, HEIGHT);

        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        Font font = new Font(TEXT_FONT, Font.BOLD, TEXT_FONT_SIZE);

        controlsText = new JLabel("<html><b>Controls:</b><br>" +
                "Switch octaves: <u>C</u>, <u>V</u><br>" +
                "Change instrument: <u>I</u><br>" +
                "Record: <u>R</u><br>" +
                "Playback: <u>P</u></html>", SwingConstants.CENTER);

        controlsText.setFont(font);
        controlsText.setForeground(COLOR_TEXT);
        add(controlsText);
    }

}
