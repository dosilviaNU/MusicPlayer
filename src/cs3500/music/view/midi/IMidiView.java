package cs3500.music.view.midi;

import cs3500.music.model.MidiComposition;
import cs3500.music.view.IMusicView;

/**
 * Will be for getting the beat from a midiView in HW07 Created by Jake on 6/17/2016.
 */
public interface IMidiView<T> extends IMusicView<T> {
  /**
   * Gets the current beat that is being played
   *
   * @return beat
   */
  public int getBeat();

  /**
   * Starts playing from begining of IComposition
   */
  public void play();

  /**
   * Starts playing from specified beat
   */
  public void resumePlay(int beat);
}
