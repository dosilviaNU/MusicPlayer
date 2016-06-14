package cs3500.music.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by David on 6/14/2016.
 */
public class NoteDisplay extends JButton {
    public int duration;
    public NoteDisplay(int duration){
        this.duration = duration;
        setVisible(true);
        setPreferredSize(new Dimension(duration,20));


    }

    public void paintComponent(Graphics g){
        g.drawRect(0,0,duration,20);
        g.setColor(Color.green);
        g.fillRect(0,0,duration,20);

    }
}
