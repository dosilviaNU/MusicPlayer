package cs3500.music.model;

import java.util.Collection;

/**
 * Interface describing operations for a sheet of music as well as basic operations that can be
 * preformed on IMusicSheets. Implemented with a version of INote N to allow it to operate.
 *
 *
 * Created by Jake on 6/9/2016.
 */
public interface IMusicSheet<N> {
  /**
   * Adds a note to an IMusicSheet. Dupicate notes should remain on the sheet
   * @param n INote to be added
   */
  void addNote(N n);

  /**
   * Removes an INote from an IMusicSheet will return
   * true if the note was successfully removed from the sheet. False if the note does not exits
   * in the music sheet.
   * @param n INote to be removed
   */
  boolean removeNote(N n);

  /**
   * Adds a collection of INotes to the Music Sheet
   * @param notes Collection of INotes to be added
   */
  void addNotes(Collection<N> notes);

  /**
   * Merges two sheets by adding all the notes from one sheet to another
   * @param sheet IMusicSheet to be added
   */
  void mergeSheets(IMusicSheet<N> sheet);

  /**
   * Adds a second sheet to the end of the first. The second sheet will begin when the tone of
   * the last note on this sheet ends.
   * @param sheet
   */
  void consecutiveSheets(IMusicSheet<N> sheet);

  /**
   * get all the notes in the IMusicSheet as a collection
   */
  Collection<N> getNotes();


  /**
   * get all the notes at the specified beat
   * @param beat beat to retreive notes from
   */
  Collection<N> getNotes(int beat);

  /**
   * Returns a String containing a textual representation of an IMusicSheet
   * @return String containing a textual representation of this IMusicSheet
   */
  String printSheet();

  /**
   * Returns the length of a music sheet in beats
   * @return integer for the size of a music sheet
   */
  int size();

  /**
   * Edits the given note on a MusicSheet. Will only modify the note given if the oldNote can
   * be found in the IMusicSheet.
   * @return true on a successful operation
   */
  boolean edit(N oldNote, N newNote);

  /**
   * Creates a copy of this music sheet
   * @return clone of this current music sheet
   */
  IMusicSheet<N> clone();

  int[] getSpread(Collection<MidiNote> notes);

}
