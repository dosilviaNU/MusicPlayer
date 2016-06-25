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

  /**
   * Repaints the GuiView.
   */
  void redraw();

  /**
   * Adds a key listener to the GuiView.
   * @param keyListener
   */
  void addKListener(KeyListener keyListener);

  /**
   * Adds an action listener.
   * @param actionListener
   */
  void addAListener(ActionListener actionListener);

  /**
   * Adds a mouse listener.
   * @param mouseListener
   */
  void addMListener(MouseListener mouseListener);

  /**
   * Returns an integer array containing the note value in index 0 and the beat in index 1
   * corresponding to the array clicked.
   * @param x Mouse x value.
   * @param y Mouse y value.
   * @return int[] containing note info.
   */
  int[] getNoteFromClick(int x, int y);

  /**
   * Fills in the appropriate fields in the editor menu based on a mouse click.
   * @param noteValue int[] containing note information.
   */
  void fieldsFromClick(int[] noteValue);

  /**
   * Gets the File string containing in the Open File text field.
   * @return File location as a String.
   */
  String getFileFromField();

  /**
   * Scrolls left.
   */
  void scrollLeft();

  /**
   * Scrolls right.
   */
  void scrollRight();

  /**
   * Scrolls up.
   */
  void scrollUp();

  /**
   * Scrolls down.
   */
  void scrollDown();

  /**
   * Updates the position of the beat bar.
   * @param beat beat position.
   */
  void updateBeat(int beat);

  /**
   * Scroll to the ned of the composition.
   */
  void scrollToEnd();

  /**
   * Scroll to the beginning of the composition.
   */
  void scrollToStart();

  /**
   * Fills the editor menu's JList with note values.
   * @param notes Notes at position clicked.
   */
  void populateNoteList(Collection<INote> notes);

  /**
   * Updates and redraws the note display based on an edited music sheet.
   * @param notes new notes.
   * @param spread new spread.
   */
  void updateNotes(Collection<INote> notes, int[] spread);

  /**
   * Gives focus back to the JFrame to await keyboard commands
   */
  void giveFocus();
}
