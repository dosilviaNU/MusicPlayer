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
        super("Macaroni");
        setLayout(new BorderLayout());
        this.displayPanel = new NotePanel();
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        add(displayPanel,BorderLayout.CENTER);

        //add(new TextArea(), BorderLayout.WEST);
        //add(new TextArea(), BorderLayout.NORTH);
        this.setSize(new Dimension(1000, 400));
        System.out.print(getContentPane());
        setResizable(false);
        //this.pack();
        this.setVisible(true);


        //displayPanel.add(new NoteDisplay(200));

        repaint();

    }

    @Override
    public JPanel display() {
        return null;
    }
}
