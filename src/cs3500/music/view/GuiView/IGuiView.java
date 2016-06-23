package cs3500.music.view.GuiView;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Collection;

import cs3500.music.model.INote;
import cs3500.music.view.IMusicView;

/**
 * Created by Jake on 6/21/2016.
 */
public interface IGuiView extends IMusicView {
  /**
   * Returns the note from the 5 Text fields in the editor pane
   *
   * @return Note being newly created
   */
  INote getNoteFromFields();

  /**
   * Returns the currently selected note in the JList of notes
   *
   * @return Currently Selected Note
   */
  INote getNoteFromList();

  void redraw();

  void addKListener(KeyListener keyListener);

  void addAListener(ActionListener actionListener);

  void addMListener(MouseListener mouseListener);

  int[] getNoteFromClick(int x, int y);

  void fieldsFromClick(int[] noteValue);

  void remove();

  String getFileFromField();

  void scrollLeft();

  void scrollRight();

  void scrollUp();

  void scrollDown();

  void updateBeat(int beat);

  void scrollToEnd();

  void scrollToStart();

  void populateNoteList(Collection<INote> notes);

  void updateNotes(Collection<INote> notes, int[] spread);
}
