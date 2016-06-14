package cs3500.music.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by David on 6/14/2016.
 */
public class NoteSpread extends JPanel {
    private int[] stats;
    public NoteSpread(int[] stats){
        this.stats = stats;
        setPreferredSize(new Dimension(40, 400));
        JPanel noteSpread = new JPanel();
        setLayout(new FlowLayout());
        add(new JLabel("Hello"));
        add(new JLabel("World"));



    }
}
