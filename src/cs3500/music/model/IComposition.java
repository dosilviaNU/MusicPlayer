package cs3500.music.model;

import java.util.Collection;
import java.util.List;

/**
 * Extension of an IMusicSheet interface for a multichannel audio file with settable instruments.
 * Created by Jake on 6/14/2016.
 */
public interface IComposition<N> extends IMusicSheet<N> {

  /**
   * Gets the tempo of this composition, in microseconds
   *
   * @return tempo per peat in microseconds
   */
  int getTempo();

  /**
   * Sets the tempo of this composition, in microseconds
   *
   * @param tempo tempo value to be set
   */
  void setTempo(int tempo);

  /**
   * Sets an instrument for a specific channel in this composition
   *
   * @param instrument instrument value
   * @param channel    channel to set instrument to
   */
  void setInstrument(int instrument, int channel);

  /**
   * Get the current instrument value at the given channel
   *
   * @param channel channel to get instrument value from
   * @return instrument
   */
  int getInstrument(int channel);

}
