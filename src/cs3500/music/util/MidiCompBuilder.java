package cs3500.music.util;

import cs3500.music.model.MidiComposition;
import cs3500.music.model.MidiNote;

/**
 * Created by Jake on 6/14/2016.
 */
public class MidiCompBuilder implements CompositionBuilder<MidiComposition> {
  MidiComposition comp;

  public MidiCompBuilder() {
    comp = new MidiComposition();
  }

  @Override
  public MidiComposition build() {
    return this.comp;
  }

  @Override
  public CompositionBuilder<MidiComposition> setTempo(int tempo) {
    comp.setTempo(tempo);
    return this;
  }

  @Override
  public CompositionBuilder<MidiComposition> addNote(int start, int end, int instrument,
                                                     int pitch, int volume) {
    comp.addNote(new MidiNote(pitch, start, end + 1 - start, volume, instrument - 1));
    return this;
  }
}
