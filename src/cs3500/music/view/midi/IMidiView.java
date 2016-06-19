package cs3500.music.view.midi;

import cs3500.music.model.MidiComposition;
import cs3500.music.view.IMusicView;

/**
 * Created by Jake on 6/17/2016.
 */
public interface IMidiView<T> extends IMusicView<T> {
  public long getBeat();
}
