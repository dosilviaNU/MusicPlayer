package cs3500.music.model;

import java.util.Collection;
import java.util.List;

/**
 * Interface for a multichannel audio file.
 * Created by Jake on 6/14/2016.
 */
public interface IComposition<N> extends IMusicSheet<N> {

  int getTempo();
  void setTempo(int tempo);
  void setInstrument(int instrument, int channel);
  int getInstrument(int channel);

}
