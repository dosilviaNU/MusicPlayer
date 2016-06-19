package cs3500.music.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Interface for a composition of a 16 channel midi audio file. Created by Jake on 6/14/2016.
 */
public class MidiComposition extends MidiSheet implements IComposition<MidiNote> {
  private int[] instrument;
  private int tempo;

  public MidiComposition() {
    super();
    instrument = new int[16];
    Arrays.fill(instrument, 0);
    tempo = 250000;
  }


  @Override
  public int getTempo() {
    return this.tempo;
  }

  @Override
  public void setTempo(int tempo) {
    this.tempo = validateTemp(tempo);
  }

  private int validateTemp(int tempo) {
    if (tempo > 0) {
      return tempo;
    }
    throw new IllegalArgumentException("Invalid Tempo");
  }

  /**
   * Validates the channel is between 0-15
   *
   * @param channel channel to be validated
   * @return channel number if valid
   */
  private int validateChannel(int channel) {
    if (channel >= 0 && channel < 16) {
      return channel;
    }
    throw new IllegalArgumentException("Invalid Channel");
  }

  /**
   * Validates teh instrument is a valid instrument number (0-127)
   *
   * @param instrument instrument to be validated
   * @return instrument number if valid
   */
  private int validateInstrument(int instrument) {
    if (instrument < 128 && instrument >= 0) {
      return instrument;
    }
    throw new IllegalArgumentException("Invalid Instrument");
  }

  @Override
  public void setInstrument(int instrument, int channel) {
    this.instrument[channel] = validateInstrument(validateInstrument(instrument));
  }

  @Override
  public int getInstrument(int channel) {
    return this.instrument[validateChannel(channel)];
  }
}
