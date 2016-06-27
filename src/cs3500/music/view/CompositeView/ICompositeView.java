package cs3500.music.view.CompositeView;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Collection;

import cs3500.music.model.IComposition;
import cs3500.music.model.INote;
import cs3500.music.util.EndPair;
import cs3500.music.view.midi.IMidiView;

/**
 * Created by David on 6/23/2016.
 */
public interface ICompositeView {
  /**
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
   * Stops playing the composition of IComposition
   */
  public void stop();

  /**
   * Starts playing from specified beat
   */
  public void resume(long position);

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
   * Takes a key listener and adds it to applicable areas within this view.
   * @param keyListener Key listener to be added to this view.
   */
  void addKListener(KeyListener keyListener);

  /**
   * Takes an action listener and adds it to the applicable areas within this view.
   * @param actionListener Action listener to be added to this view.
   */
  void addAListener(ActionListener actionListener);

  /**
   * Takes a mouse listener and adds it to the applicable areas within this view.
   * @param mouseListener Mouse listener to be added to this view.
   */
  void addMListener(MouseListener mouseListener);

  /**
   * Returns the appropriate note given x,y values from the position clicked in the note display.
   * @param x Mouse x coordinate.
   * @param y Mouse y coordinate.
   * @return Note that was clicked on.
   */
  int[] getNoteFromClick(int x, int y);

  /**
   * Fills in the appropriate text fields with the note that was clicked on's values.
   * @param noteValue Note values to be added.
   */
  void fieldsFromClick(int[] noteValue);

  /**
   * Returns a file name as a string that was entered into the text field.
   * @return File name as a String.
   */
  String getFileFromField();

  /**
   * Scrolls the note display panel to the left.
   */
  void scrollLeft();

  /**
   * Scrolls the note display panel to the right.
   */
  void scrollRight();

  /**
   * Scrolls the note display panel up.
   */
  void scrollUp();

  /**
   * Scrolls the note display panel down.
   */
  void scrollDown();

  /**
   * Moves the beat bar to the given beat position.
   * @param beat Beat position as an integer.
   */
  void updateBeat(int beat);

  /**
   * Scrolls to the end of the note display panel.
   */
  void scrollToEnd();

  /**
   * Scrolls to the start of the note display panel.
   */
  void scrollToStart();

  /**
   * Populates the JList with the collection of notes given.
   * @param notes Notes to be added.
   */
  void populateNoteList(Collection<INote> notes);

  /**
   * Updates the GuiView representation with the given notes and note data.
   * @param notes New lsit of notes to draw.
   * @param spread New note stats.
   */
  void updateNotes(Collection<INote> notes, int[] spread);

  /**
   * Updates the current composition.
   * @param sheet Composition to be added.
   */
  void updateMidiComp(IComposition sheet);

  /**
   * Displays this view.
   */
  void display();

  /**
   * Gives focus to this frame.
   */
  void giveFocus();

  /**
   * Adds given ending.
   * @param end Ending to be added.
   */
  void addEnding(EndPair end);

  /**
   * Removes given ending.
   */
  void removeEnding(EndPair end) throws IllegalArgumentException;

  int[] getRepeats();

  void addRepeat(int beat);

  void addInvertRepeat(int beat);

  void removeRepeat(int beat);

  void removeInvertRepeat(int beat);


}
