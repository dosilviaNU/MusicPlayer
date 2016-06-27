package cs3500.music.model.Extra;

import java.util.ArrayList;
import java.util.List;

import cs3500.music.model.IComposition;
import cs3500.music.model.INote;
import cs3500.music.model.MidiNote;

/**
 * Created by Jake on 6/22/2016.
 */
public interface ICompRepeat<N> extends IComposition<N> {
  /**
   * produces a list of the required jumps (in beats) for the repeats in a piece to play
   * @return List
   */
  public List<List<Integer>> getJumps();

  /**
   * Adds a repeat to a composition
   * @param repeat IRepeat to add
   */
  public void addRepeat(IRepeat repeat);

  /**
   * Removes a repeat from a composition
   * @param repeat repeat to remove
   * @return true if remove successful
   */
  public boolean removeRepeat(IRepeat repeat);
}
