package ua.edu.tntu._121_se.midipiano.ui;

import ua.edu.tntu._121_se.midipiano.piano.PianoKey;

import java.awt.event.KeyEvent;

public class PianoKeyLayouts {

    public static final PianoKeyLayout NumberRowLayout = new PianoKeyLayout()
            .add(KeyEvent.VK_1, PianoKey.C)
            .add(KeyEvent.VK_2, PianoKey.Cs)
            .add(KeyEvent.VK_3, PianoKey.D)
            .add(KeyEvent.VK_4, PianoKey.Ds)
            .add(KeyEvent.VK_5, PianoKey.E)
            .add(KeyEvent.VK_6, PianoKey.F)
            .add(KeyEvent.VK_7, PianoKey.Fs)
            .add(KeyEvent.VK_8, PianoKey.G)
            .add(KeyEvent.VK_9, PianoKey.Gs)
            .add(KeyEvent.VK_0, PianoKey.A)
            .add(KeyEvent.VK_MINUS, PianoKey.As)
            .add(KeyEvent.VK_EQUALS, PianoKey.B)
            .add(KeyEvent.VK_BACK_SPACE, PianoKey.C_Far);

    public static final PianoKeyLayout TraditionalLayout = new PianoKeyLayout()
            .add(KeyEvent.VK_Q, PianoKey.C)
            .add(KeyEvent.VK_2, PianoKey.Cs)
            .add(KeyEvent.VK_W, PianoKey.D)
            .add(KeyEvent.VK_3, PianoKey.Ds)
            .add(KeyEvent.VK_E, PianoKey.E)
            .add(KeyEvent.VK_R, PianoKey.F)
            .add(KeyEvent.VK_5, PianoKey.Fs)
            .add(KeyEvent.VK_T, PianoKey.G)
            .add(KeyEvent.VK_6, PianoKey.Gs)
            .add(KeyEvent.VK_Y, PianoKey.A)
            .add(KeyEvent.VK_7, PianoKey.As)
            .add(KeyEvent.VK_U, PianoKey.B)
            .add(KeyEvent.VK_I, PianoKey.C_Far);

//    public static final HashMap<Integer, Pitch> NUMBER_ROW_LAYOUT = new HashMap<>() {{
//        put(KeyEvent.VK_1, Pitch.C);
//        put(KeyEvent.VK_2, Pitch.Cs);
//        put(KeyEvent.VK_3, Pitch.D);
//        put(KeyEvent.VK_4, Pitch.Ds);
//        put(KeyEvent.VK_5, Pitch.E);
//        put(KeyEvent.VK_6, Pitch.F);
//        put(KeyEvent.VK_7, Pitch.Fs);
//        put(KeyEvent.VK_8, Pitch.G);
//        put(KeyEvent.VK_9, Pitch.Gs);
//        put(KeyEvent.VK_0, Pitch.A);
//        put(KeyEvent.VK_MINUS, Pitch.As);
//        put(KeyEvent.VK_EQUALS, Pitch.B);
//        put(KeyEvent.VK_BACK_SPACE, Pitch.C.transpose(Pitch.OCTAVE));
//    }};
//
//    public static final HashMap<Integer, Pitch> TRADITIONAL_LAYOUT = new HashMap<>() {{
//        put(KeyEvent.VK_Q, Pitch.C);
//        put(KeyEvent.VK_2, Pitch.Cs);
//        put(KeyEvent.VK_W, Pitch.D);
//        put(KeyEvent.VK_3, Pitch.Ds);
//        put(KeyEvent.VK_E, Pitch.E);
//        put(KeyEvent.VK_R, Pitch.F);
//        put(KeyEvent.VK_5, Pitch.Fs);
//        put(KeyEvent.VK_T, Pitch.G);
//        put(KeyEvent.VK_6, Pitch.Gs);
//        put(KeyEvent.VK_Y, Pitch.A);
//        put(KeyEvent.VK_7, Pitch.As);
//        put(KeyEvent.VK_U, Pitch.B);
//        put(KeyEvent.VK_I, Pitch.C.transpose(Pitch.OCTAVE));
//    }};
}
