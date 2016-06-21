package cs3500.music.view.console;

import java.util.Arrays;
import java.util.Collection;
import java.util.Formatter;

import cs3500.music.model.IComposition;
import cs3500.music.model.INote;
import cs3500.music.view.IMusicView;

/**
 * Mock for the console view to print to an appendable
 * Created by Jake on 6/19/2016.
 */
public class ConsoleMock implements IMusicView<IComposition> {
  private IComposition sheet;
  Formatter debug;

  public ConsoleMock(IComposition givenSheet, Appendable ap) {
    this.sheet = givenSheet;
    debug = new Formatter(ap);
  }

  @Override
  public void display() {
    Collection<INote> notes = sheet.getNotes();
    int[] stats = sheet.getSpread(notes);
    if (stats[0] == 128 || stats[1] == -1 || stats[2] == 0) {
      System.out.println("No music in sheet!");
      return;
    }
    StringBuilder results = new StringBuilder("");
    int size = (int) Math.round(Math.log10(stats[2]) + .5);
    results.append(printNoteLine(size, stats));
    results.append("\n");
    for (int i = 0; i < stats[2] - 1; i++) {
      results.append(printBeatNumber(i, size));
      results.append(beatToString(i, stats));
      results.append("\n");
    }
    debug.format(results.toString());
  }


  /**
   * prints a line of notes
   *
   * @param size  size buffer space
   * @param stats array with min and max note
   * @return built note line
   */
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

  /**
   * Converts line of beats to the correct spacing of x's and |'s
   */
  private StringBuilder beatToString(int beat, int[] stats) {
    StringBuilder result = new StringBuilder();
    Collection<INote> notes = sheet.getNotes(beat);
    String[] text = new String[128];
    Arrays.fill(text, "     ");
    if (notes != null) {
      for (INote n : notes) {
        int startBeat = n.getStart();
        int endBeat = n.getStart() + n.getDuration() - 1;
        if (endBeat == beat) {
        } else if (startBeat == beat) {
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

  /**
   * Converts a pitch/octave value to a 5 char string
   */
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


  /**
   * Pads a beat with space to the correct size
   *
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
}