package cs3500.music.view;

import cs3500.music.model.IMusicSheet;
import cs3500.music.model.MidiNote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * Created by David on 6/12/2016.
 */
public class GuiView extends JFrame implements IMusicView<JPanel> {
    IMusicSheet sheet;
    JPanel displayPanel;

    public static void main(String[] args){
        new GuiView();
    }

    public GuiView(){
        super();
        this.displayPanel = new NoteViewPanel(new MidiNote(30, 40, 1000));
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(displayPanel);
        this.setSize(new Dimension(400, 300));
        //this.pack();
        this.setVisible(true);
    }

    @Override
    public JPanel display() {
        return null;
    }
}
