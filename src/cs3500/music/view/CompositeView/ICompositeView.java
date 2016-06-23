package cs3500.music.view.CompositeView;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Collection;

import cs3500.music.model.IComposition;
import cs3500.music.model.INote;

/**
 * Created by David on 6/23/2016.
 */
public interface ICompositeView {  /**
 * Gets the current beat that is being played
 *
 * @return beat
 */
public int getBeat();

  /**
   * Starts playing from begining of IComposition
   */
  public void play();

  /**
   * Starts playing from specified beat
   */
  public void resumePlay(int beat);

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

  void updateMidiComp(IComposition sheet);

  void display();

  void startBeat();

}
