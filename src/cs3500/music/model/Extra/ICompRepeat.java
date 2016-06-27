package cs3500.music.model.Extra;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 6/22/2016.
 */
public interface ICompRepeat {
  /**
   * produces a list of the required jumps (in beats) for the repeats in a piece to play
   * @return List
   */
  public List<List<Integer>> getJumps();
  public void addRepeat(IRepeat repeat);
  public boolean removeRepeat(IRepeat repeat);
}
