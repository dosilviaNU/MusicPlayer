package cs3500.music.view;

import cs3500.music.model.IMusicSheet;
import cs3500.music.model.INote;
import cs3500.music.model.MidiNote;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

/**
 * Created by David on 6/14/2016.
 */

public class NotePanel extends JPanel {
    IMusicSheet sheet;
    int middleNote;
    int[] spread;

    int area;
    private static final int gridWidth = GuiView.GUI_WIDTH - 50;
    private static final int gridHeight = GuiView.GUI_HEIGHT;


    public NotePanel(IMusicSheet sheet) {
        this.sheet = sheet;

        System.out.print(area);
        this.spread = sheet.getSpread(sheet.getNotes());
        middleNote = (spread[0] + spread[1]) / 2;


        setPreferredSize(new Dimension(gridWidth, gridHeight));

        setLayout(new FlowLayout());

        GridBagConstraints gc = new GridBagConstraints();


        setVisible(true);
        repaint();

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Collection<MidiNote> notes = sheet.getNotes();
        //Paint notes
        for (INote note : notes) {

            int x = note.getStart() * GuiView.BEAT_WIDTH;
            int y = calcY(note.getValue());
            int width = note.getDuration() * GuiView.BEAT_WIDTH;


            g2.setColor(Color.green);
            g2.fillRect(x, y, width, GuiView.BEAT_HEIGHT);
            g2.setColor(Color.black);
            g2.fillRect(x, y, GuiView.BEAT_WIDTH, GuiView.BEAT_HEIGHT);


        }

        //Paint vert gridlines.
        g2.setColor(Color.black);
        for (int i = 0; i < gridWidth; i += 4 * GuiView.BEAT_WIDTH) {
            g2.setStroke(new BasicStroke(2));
            g.drawLine(i, 0, i, GuiView.GUI_HEIGHT);

        }

        //Paint horizontal gridlines.
        for (int i = 0; i <= gridHeight; i += GuiView.BEAT_HEIGHT) {
            g2.setStroke(new BasicStroke(2));
            if (i == 4) {
                g2.setStroke(new BasicStroke(5));
            }
            g.drawLine(0, i, gridWidth, i);
        }

        System.out.println(this.getHeight());

    }


    private int calcY(int noteValue) {
        int reset = noteValue-spread[0];
        int mod = (reset*2)+1;
        int y = (reset+GuiView.NOTE_COUNT-mod)*GuiView.BEAT_HEIGHT;
        return y;
    }
}
