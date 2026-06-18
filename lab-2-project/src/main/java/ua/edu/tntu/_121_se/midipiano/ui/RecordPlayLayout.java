package ua.edu.tntu._121_se.midipiano.ui;

import javax.swing.*;
import java.awt.*;

public class RecordPlayLayout extends JPanel{


    class RecordIndicator extends JPanel {
        private Color color = Color.RED.darker();

        public RecordIndicator() {
            setOpaque(false);
        }
        private void setColor(Color newColor) {
            color = newColor;
            repaint();
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(this.color);
            g.fillOval(7, 0, 50, 30);
        }
        public void on() { this.setColor(Color.RED); }
        public void off() { this.setColor(Color.RED.darker()); }
    }
    class PlayIndicator extends JPanel {
        private Color color = Color.RED.darker();
        public PlayIndicator() {
            setOpaque(false);
        }
        private void setColor(Color newColor) {
            color = newColor;
            repaint();
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(this.color);
            g.fillOval(10, 0, 50, 30);
        }
        public void on() { this.setColor(Color.RED); }
        public void off() { this.setColor(Color.RED.darker()); }
    }
     public static final int WIDTH = 150;
    public static final int HEIGHT = 120;

    private static final int TEXT_FONT_SIZE = 12;
    private static final Color COLOR_TEXT = Color.WHITE;
    private static final String TEXT_FONT = "Arial";

    private final JLabel recordLabel;
    private final JLabel playLabel;

    private final PlayIndicator playIndicator;
    private final RecordIndicator recordIndicator;

    // AI-assisted code: ChatGPT - перейменування булевих полів для підвищення читабельності
    private boolean playingIndicatorOn = false;
    private boolean recordingIndicatorOn = false;

    public RecordPlayLayout() {
        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setLayout(new GridLayout(2, 2));
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        Font font = new Font(TEXT_FONT, Font.BOLD, TEXT_FONT_SIZE);

        recordLabel =  new JLabel("RECORD", SwingConstants.CENTER);
        playLabel =  new JLabel("PLAY", SwingConstants.CENTER);
        recordIndicator = new RecordIndicator();
        playIndicator = new PlayIndicator();

        recordLabel.setForeground(COLOR_TEXT);
        playLabel.setForeground(COLOR_TEXT);
        add(recordLabel);
        add(playLabel);
        add(recordIndicator);
        add(playIndicator);
    }
    public void play(){
        playingIndicatorOn = !playingIndicatorOn;
        if (playingIndicatorOn) {
            playIndicator.on();
        } else {
            playIndicator.off();
        }
    }
    public void record(){
        recordingIndicatorOn = !recordingIndicatorOn;
        if (recordingIndicatorOn) {
            recordIndicator.on();
        } else {
            recordIndicator.off();
        }
    }
}
