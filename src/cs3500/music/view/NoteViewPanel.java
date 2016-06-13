package cs3500.music.view;

import cs3500.music.model.INote;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import javax.swing.*;

/**
 * A dummy view that simply draws a string 
 */
public class NoteViewPanel extends JPanel {
    INote note;
    new MouseE

    public NoteViewPanel(INote note){
        this.note = note;
        this.getListeners(new MouseEvent());
    }


  @Override
  public void paintComponent(Graphics g){
    // Handle the default painting
    super.paintComponent(g);
      g.setColor(Color.green);
    //g.drawRect(50, 50,note.getDuration(),20);
      g.fillRect(50, 50,note.getDuration(),20);
      g.setColor(Color.black);
      g.fillRect(50, 50, 50, 20);


  }


}
