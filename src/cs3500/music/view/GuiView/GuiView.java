package cs3500.music.view.GuiView;

import cs3500.music.TestSheet;
import cs3500.music.model.IMusicSheet;
import cs3500.music.view.IMusicView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by David on 6/12/2016.
 */

/**
 * Displays an IMusicSheet using Java Swing components.
 */
public class GuiView extends JFrame implements IMusicView {
    private static JComponent centerPanel;

    public static final int GUI_WIDTH = 1500;
    public static final int GUI_HEIGHT =300;
    public static final int BEAT_WIDTH = 40;
    public static final int BEAT_HEIGHT=20;

    public static void main(String[] args){
        new GuiView(TestSheet.getSheet()).display();

    }

  /**
   * Default constructor for a GuiView
   * Takes in an IMusicSheet to represent.
   * @param sheet IMusicShhet this GuiView will display.
   */
  public GuiView(IMusicSheet sheet){
        super("Music Editor OOD Summer 1");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(GuiView.GUI_WIDTH, GUI_HEIGHT));

        //Set center panel, as scrollpane.
        centerPanel = new NoteDisplay(sheet);
        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setLayout(new ScrollPaneLayout());
        add(scrollPane);
    }

    @Override
    public void display() {
        this.setVisible(true);
    }
}
