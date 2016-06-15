package cs3500.music.view;

import javax.swing.*;
import java.awt.*;

import cs3500.music.model.INote;

/**
 * Created by David on 6/14/2016.
 */
public class NoteSpread extends JPanel {
    private int[] stats;
    public NoteSpread(int[] stats){
        this.stats = stats;
        setPreferredSize(new Dimension(50, 400));
        JPanel noteSpread = new JPanel();
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        int weight = 1;
        gc.gridheight = GuiView.BEAT_HEIGHT;
        gc.gridwidth = 50;

        for(int i = stats[1];i >= stats[0];i--){
            StringBuilder result = new StringBuilder();
            result.append(" ");
            result.append(INote.Pitch.fromValue(i % 12).toString());
            result.append((i / 12) - 1);
            if (result.length() == 3) {
                result.insert(0, " ");
                result.append(" ");
            } else if (result.length() == 4) {
                result.append(" ");
            }
            JLabel temp = new JLabel(result.toString());
            temp.setFont(new Font("Serif", Font.BOLD, 20));
            gc.gridx=0;
            gc.gridy=gc.RELATIVE;
            gc.weighty=i;
            add(temp, gc);
        }







    }
}
