package cs3500.music.model;

/**
 * Interface for represented operations needed on notes in chromatic scale All INotes have a
 * pitch, octave, and length that represent the sound they make. All modification methods should
 * throw IllegalArgumentExceptions if the modification will take them out of the range allowed by
 * the INote Created by Jake on 6/9/2016.
 */
public interface INote {

  /**
   * enumeration to represent standard musical pitchs
   */
  public enum Pitch {
    C, CSHARP, D, DSHARP, E, F, FSHARP, G, GSHARP, A, ASHARP, B;

    /**
     * Returns a string representation of a pitch
     *
     * @return String representation of a bit
     */
    public String toString() {
      switch (this) {
        case C:
          return "C";
        case CSHARP:
          return "C#";
        case D:
          return "D";
        case DSHARP:
          return "D#";
        case E:
          return "E";
        case F:
          return "F";
        case FSHARP:
          return "F#";
        case G:
          return "G";
        case GSHARP:
          return "G#";
        case A:
          return "A";
        case ASHARP:
          return "A#";
        case B:
          return "B";
        default:
          return ""; //unreachable code
      }
    }

    /**
     * Converts a string representation of a pitch to enum type
     *
     * @param s string representation of a pit
     */
    public static Pitch fromString(String s) {
      switch (s) {
        case "C":
          return C;
        case "C#":
          return CSHARP;
        case "D":
          return D;
        case "D#":
          return DSHARP;
        case "E":
          return E;
        case "F":
          return F;
        case "F#":
          return FSHARP;
        case "G":
          return G;
        case "G#":
          return GSHARP;
        case "A":
          return A;
        case "A#":
          return ASHARP;
        case "B":
          return B;
        default:
          throw new IllegalArgumentException("Not a valid pitch");
      }
    }

    /**
     * returns a Pitch for the given value, starting with C as 0. numbers greater than 12 will
     * roll over to the appropriate pitch,
     *
     * @param value interger value representing pitch
     * @return Pitch enum
     */
    public static Pitch fromValue(int value) {
      INote.Pitch[] lookupValue = INote.Pitch.values();
      return lookupValue[value % 12];
    }
  }

  /**
   * increases this note's pitch by one, increasing octave if needed
   *
   * @throws IllegalArgumentException if note modifying note will make it unplayable
   */
  void pitchUp(int value) throws IllegalArgumentException;

  /**
   * decreases this notes's pitch by one, decreasing octaves as needed
   *
   * @throws IllegalArgumentException if note modifying note will make it unplayable
   */
  void pitchDown(int value) throws IllegalArgumentException;

  /**
   * extends the note by one beat
   */
  void extend(int value) throws IllegalArgumentException;

  /**
   * shortens the note's length by one beat
   *
   * @throws IllegalArgumentException if note would become length 0
   */
  void shorten(int value) throws IllegalArgumentException;

  /**
   * Modify the note so it begins one beat earlier
   *
   * @throws IllegalArgumentException if note would start at a beat less than 0
   */
  void shiftLeft(int value) throws IllegalArgumentException;

  /**
   * Modify the note so it begins one beat later
   */
  void shiftRight(int value);

  /**
   * creates a string representation of the note, such as "C#4"
   */
  String getString();

  /**
   * returns the start of the note indexed from 0
   */
  int getStart();

  /**
   * returns the length of the note in beats.
   */
  int getDuration();

  /**
   * returns the a number representing a notes sound (irrespective of duration and start).
   */
  int getValue();

  /**
   * returns the a number the volume (velocity) of the note.
   */
  int getVolume();

  /**
   * returns the channel the note is to be played on
   */
  int getChannel();
}
