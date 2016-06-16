package cs3500.music.view;

import java.awt.*;
import java.util.Collection;

import javax.swing.*;

import cs3500.music.model.INote;

/**
 * Created by David on 6/15/2016.
 */
public class EditorMenu extends JDialog{
  JList contents;
  public EditorMenu(){
    super();
    setLayout(new FlowLayout());
    contents=new JList();
    contents.setVisible(true);
    contents.setPreferredSize(new Dimension(100,100));

    setPreferredSize(new Dimension(100,100));
    setVisible(true);


  }

  public  void addList(Collection<INote> notes){
    for(INote note:notes){
      contents.add(new JLabel(note.toString()));
    }
    add(contents);
  }
}
