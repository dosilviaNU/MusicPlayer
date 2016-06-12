package cs3500.music.model;

import java.util.Objects;

/**
 * Representation of an INote for Midi format Represents notes pitches from C-1 - G9 (0-127) Can
 * represent notes to a minimum resolution of a beat, with a trailing length. All
 * mutators will throw Exceptions if they will make the MidiNote invalid.
 * Created by Jake on 6/9/2016.
 */
public final class MidiNote implements INote, Comparable<MidiNote> {
  private int midiNote; //invariant: once set, must be in range 0-127
  private int beatStart; //invariant: once set, must be >=0
  private int length; //invariant: once set, will always be >= 1

  /**
   * Constructor that takes midi value of note
   *
   * @param value     midiNote value
   * @param beatStart start of note
   * @param length    length of note
   */
  public MidiNote(int value, int beatStart, int length) throws IllegalArgumentException {
    this.midiNote = validateNote(value);
    this.beatStart = validateStart(beatStart);
    this.length = validateLength(length);
  }

  /**
   * Constructor that takes in a pitch and octave, note start, and lenght
   *
   * @param p         Pitch of the note
   * @param octave    Octave of the note (-1 - 9 (G9))
   * @param beatStart beat number of the start of the note, 0 indexed
   * @param length    length of the note, in whole beats
   */
  public MidiNote(INote.Pitch p, int octave, int beatStart, int length)
          throws IllegalArgumentException {
    int value = p.ordinal() + ((octave + 1) * 12);
    this.midiNote = validateNote(value);
    this.beatStart = validateStart(beatStart);
    this.length = validateLength(length);
  }

  /**
   * creates a clone of the MidiNote
   *
   * @param n MidiNote to be cloned
   */
  public MidiNote(MidiNote n) throws IllegalArgumentException {
    this.midiNote = validateNote(n.midiNote);
    this.beatStart = validateStart(n.beatStart);
    this.length = validateLength(n.length);
  }

  /**
   * ensure length meats value ranges
   *
   * @param length length of note
   * @return length of note if valid
   * @throws IllegalArgumentException if note is an invalid length
   */
  private int validateLength(int length) throws IllegalArgumentException {
    if (length >= 1) {
      return length;
    } else {
      throw new IllegalArgumentException("Length of note must be at least 1");
    }
  }

  /**
   * ensures the beat starts at a valid location (> 0)
   *
   * @param beatStart where does the beat start
   * @return beat start position if valid
   * @throws IllegalArgumentException if beat position is invalid
   */
  private int validateStart(int beatStart) throws IllegalArgumentException {
    if (beatStart >= 0) {
      return beatStart;
    } else {
      throw new IllegalArgumentException("Beat start must be greater than 0");
    }
  }

  /**
   * Validiates the note is within midiNote range (0-127)
   */
  private int validateNote(int value) throws IllegalArgumentException {
    if (value >= 0 && value <= 127) {
      return value;
    } else {
      throw new IllegalArgumentException("Invalid value (0 to 127)");
    }
  }

  @Override
  public void pitchUp(int value) {
    this.midiNote = validateNote(midiNote + value);
  }

  @Override
  public void pitchDown(int value) {
    this.midiNote = validateNote(midiNote - value);
  }

  @Override
  public void extend(int value) {
    this.length = validateLength(this.length + value);
  }

  @Override
  public void shorten(int value) {
    this.length = validateLength(this.length - value);

  }

  @Override
  public void shiftLeft(int value) {
    this.beatStart = validateStart(this.beatStart - value);

  }

  @Override
  public void shiftRight(int value) {
    this.beatStart = validateStart(this.beatStart + value);

  }

  @Override
  public String toString() {
    return INote.Pitch.fromValue(midiNote).toString() + ((midiNote / 12) - 1);
  }

  @Override
  public String getString() {
    return this.toString();
  }

  @Override
  public int getStart() {
    return this.beatStart;
  }

  @Override
  public int getDuration() {
    return this.length;
  }

  @Override
  public int getValue() {
    return this.midiNote;
  }

  @Override
  public int compareTo(MidiNote o) {
    if (this.beatStart - o.beatStart != 0) {
      return this.beatStart - o.beatStart;
    } else if (this.length - o.length != 0) {
      return this.length - o.length;
    }
    return this.midiNote - o.midiNote;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MidiNote)) {
      return false;
    }
    MidiNote n = (MidiNote) o;
    return this.midiNote == n.midiNote &&
            this.beatStart == n.beatStart &&
            this.length == n.length;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.beatStart, this.length, this.midiNote);
  }
}
