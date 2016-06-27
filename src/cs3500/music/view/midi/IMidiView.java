package cs3500.music.view.midi;

import cs3500.music.model.IComposition;
import cs3500.music.model.MidiComposition;
import cs3500.music.model.MidiNote;
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
   * Loads and plays the song
   *
   * @return beat
   */
  public void run();
  /**
   * Gets the current beat that is being played
   *
   * @return beat
   */
  public void loadComp(IComposition<MidiNote> comp);

  /**
   * Starts playing from begining of IComposition
   */
  public void play();

  /**
   * Starts playing from specified beat
   */
  public void stop();

  /**
   * Starts playing from given beat
   */
  public void resume(long beat);

  /**
   * Starts playing from given beat
   */
  public boolean isPlaying();
}
