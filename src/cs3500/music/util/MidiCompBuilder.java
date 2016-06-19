package cs3500.music.util;

import cs3500.music.model.MidiComposition;
import cs3500.music.model.MidiNote;

/**
 * Implementation for Composition build that produces MidiCompositions<MidiNotes> This
 * implementation will throw all errors in notes or tempos that are being built with this
 * CompositionBuilder caused by invalid data being entered. Created by Jake on 6/14/2016.
 */
public class MidiCompBuilder implements CompositionBuilder<MidiComposition> {
  MidiComposition comp;

  /**
   * Default constructor, creates a new MidiCompBuilder
   */
  public MidiCompBuilder() {
    comp = new MidiComposition();
  }

  @Override
  public MidiComposition build() {
    return this.comp;
  }

  @Override
  public CompositionBuilder<MidiComposition> setTempo(int tempo) throws IllegalArgumentException {
    comp.setTempo(tempo);
    return this;
  }

  @Override
  public CompositionBuilder<MidiComposition> addNote(int start, int end, int instrument,
                                                     int pitch, int volume)
          throws IllegalArgumentException {
    try {
      comp.addNote(new MidiNote(pitch, start, end + 1 - start, volume, instrument - 1));
    } catch (Exception e) {
      throw new IllegalArgumentException("Error in MidiCompBuilder: " + e.getMessage());
    }
    return this;
  }
}
