package cs3500.music.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Model for a Sheet of Midi notes.
 * This Model represents a sheet of music from C-1 to G9 on the musical scale.
 *
 * In this model, notes can be added on top of one another, as well as duplicated over one
 * another. The model will allow you to do this. Removing will only remove one instance of the
 * note from the sheet, however.
 *
 * Getting the MidiNotes from the sheet will only return one copy of the note if duplicates
 * exist on the sheet.
 *
 * All MidiNotes are stored and returned as clones of the argument passed in.
 * MidiSheets will never throw exceptions except when a heap overflow occurs on extremely large
 * sheets.
 *
 * Created by Jake on 6/9/2016.
 */
public class MidiSheet implements IMusicSheet<MidiNote> {
  private final HashMap<Integer, ArrayList<MidiNote>> sheet;
  //invariant: MidiSheets are the collection of all notes added, minus the ones removed.
  // Duplicate notes can exist. Blank MidiSheets can only be obtained by constructing a new
  // MidiSheet, or removing everything from an existing one.

  /**
   * Default constructor creates an empty MidiSheet
   */
  public MidiSheet() {
    sheet = new HashMap<Integer, ArrayList<MidiNote>>();
  }

  @Override
  public void addNote(MidiNote midiNote) {
    this._add(new MidiNote(midiNote));
  }

  /**
   * Adds a note to the MidiSheet
   * @param n clone of note to be added
   */
  private void _add(MidiNote n) {
    for (int i = n.getStart(); i < n.getStart() + n.getDuration(); i++) {
      if (sheet.containsKey(i)) {
        ArrayList<MidiNote> value = sheet.remove(i);
        value.add(n);
        sheet.put(i, value);
      } else {
        ArrayList<MidiNote> temp = new ArrayList<MidiNote>(1);
        temp.add(n);
        sheet.put(i, temp);
      }
    }
  }

  @Override
  public boolean removeNote(MidiNote midiNote) {
    return _remove(new MidiNote(midiNote));
  }

  /**
   * removes a note from a MidiSheet
   * @param n clone of note to be removed
   * @return was a note successfully removed
   */
  private boolean _remove(MidiNote n) {
    boolean found = false;
    for (int i = 0; i < n.getDuration(); i++) {
      if (sheet.containsKey(n.getStart() + i)) {
        int index = n.getStart() + i;
        ArrayList<MidiNote> value = sheet.remove(index);
        if (value.contains(n)) {
          found = (found ? found : !found);
          value.remove(n);
        }
        sheet.put(index, value);
      }
    }
    return found;
  }

  /**
   * private method for adding notes
   * @param notes notes to be added
   */
  private void _addNotes(Collection<MidiNote> notes) {
    for (MidiNote n : notes) {
      _add(new MidiNote(n));
    }
  }

  @Override
  public void addNotes(Collection<MidiNote> notes) {
    this._addNotes(notes);
  }

  @Override
  public void mergeSheets(IMusicSheet sheet) {
    this._addNotes(sheet.getNotes());
  }

  @Override
  public void consecutiveSheets(IMusicSheet sheet) {
    Collection<MidiNote> notes = this.getNotes();
    int[] stats = getStats(notes);
    Collection<MidiNote> newNotes = sheet.getNotes();
    for (MidiNote n : newNotes) {
      this._add(new MidiNote(n.getValue(), (n.getStart() + stats[2]), n.getDuration()));
    }
  }

  /**
   * {@inheritDoc}
   * Note: this method will only show unique {@link MidiNote}, not duplicates. If notes have
   * the same pitch and starting beat, but different lengths, they will show as unique, even if
   * one is contained in another.
   */
  public Collection<MidiNote> getNotes() {
    Set<MidiNote> results = new HashSet<MidiNote>();
    Collection<ArrayList<MidiNote>> notes = sheet.values();
    for (ArrayList<MidiNote> arrNote : notes) {
      for (MidiNote n: arrNote) {
        results.add(new MidiNote(n));
      }
    }
    return results;
  }

   /**
   * {@inheritDoc}
   * Note: this method will only show unique {@link MidiNote}, not duplicates. If notes have
   * the same pitch and starting beat, but different lengths, they will show as unique, even if
   * one is contained in another.
   **/
  public Collection<MidiNote> getNotes(int beat) {
    Set<MidiNote> result = new HashSet<MidiNote>();
    ArrayList<MidiNote> notes = sheet.get(beat);
    if (beat < 0 || notes == null) { return result;}
    for (MidiNote n: sheet.get(beat)) {
      result.add(new MidiNote(n));
    }
    return result;
  }

  @Override
  public String printSheet() {

    Collection<MidiNote> notes = getNotes();
    int[] stats = getStats(notes);
    if (stats[0] == 128 || stats[1] == -1 || stats[2] == 0) {
      return "No music in sheet!";
    }
    StringBuilder results = new StringBuilder("");
    int size = (int) Math.round(Math.log10(stats[2]) + .5);
    results.append(printNoteLine(size, stats));
    results.append("\n");
    for (int i = 0; i < stats[2]; i++) {
      results.append(printBeatNumber(i, size));
      results.append(beatToString(i, stats));
      results.append("\n");
    }
    return results.toString();
  }

  /**
   * Pads a beat with space to the correct size
   * @param beat number of the beat
   * @param size correct size of the string to return
   * @return padded StringBuilder containing the beat.
   */
  private StringBuilder printBeatNumber(int beat, int size) {
    StringBuilder result = new StringBuilder(Integer.toString(beat));
    int beatNumLen = result.length();
    for (int i = 0; i < size - beatNumLen; i++) {
      result.insert(0, " ");
    }
    return result;
  }

  @Override
  public int size() {
    if (this.getNotes().size() == 0) {
      return 0;
    }
    return this.getStats(this.getNotes())[2];
  }

  @Override
  public boolean edit(MidiNote oldNote, MidiNote newNote) {
    if (this._remove(oldNote)) {
      this._add(newNote);
      return true;
    }
    else {
      return false;
    }
  }


  private StringBuilder printNoteLine(int size, int[] stats) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < size; i++) {
      result.append(" ");
    }
    for (int i = stats[0]; i <= stats[1]; i++) {
      result.append(noteToString(i));
    }
    return result;
  }

  private StringBuilder beatToString(int beat, int[] stats) {
    StringBuilder result = new StringBuilder();
    ArrayList<MidiNote> notes = sheet.get(beat);
    String[] text = new String[128];
    Arrays.fill(text, "     ");
    if (notes != null) {
      for (MidiNote n : notes) {
        int startBeat = n.getStart();
        if (startBeat == beat) {
          text[n.getValue()] = "  X  ";
        } else if (text[n.getValue()].equals("     ")) {
          text[n.getValue()] = "  |  ";
        }
      }
    }
    for (int i = stats[0]; i <= stats[1]; i++) {
      result.append(text[i]);
    }
    return result;
  }


  private String noteToString(int value) {
    StringBuilder result = new StringBuilder();
    result.append(" ");
    result.append(INote.Pitch.fromValue(value % 12).toString());
    result.append((value / 12) - 1);
    if (result.length() == 3) {
      result.insert(0, " ");
      result.append(" ");
    } else if (result.length() == 4) {
      result.append(" ");
    }
    return result.toString();

  }

  private String printFormattedLine(int size, int line, int[] stats) {
    return "";
  }

  private int[] getStats(Collection<MidiNote> notes) {
    int minPitch = 128;
    int maxPitch = -1;
    int length = 0;
    for (MidiNote n : notes) {
      if (n.getValue() < minPitch) {
        minPitch = n.getValue();
      }
      if (n.getValue() > maxPitch) {
        maxPitch = n.getValue();
      }
      if (n.getStart() + n.getDuration() - 1 >= length) {
        length = n.getStart() + n.getDuration();
      }
    }
    int[] results = {minPitch, maxPitch, length};
    return results;
  }
}
