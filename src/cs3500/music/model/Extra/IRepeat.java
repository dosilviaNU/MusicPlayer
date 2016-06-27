package cs3500.music.model.Extra;

import java.util.ArrayList;

import javafx.util.Pair;

/**
 * Interface representing a repeating patterns
 * Created by Jake on 6/22/2016.
 */
public interface IRepeat {
  /**
   * Builds a list of jumps required to complete the repeat pattern this will be in the form
   * (Beat to start jump from, Beat to jump to)
   * @return Arraylist of pairs of integers required to complete the repeat pattern
   */
  public ArrayList<ArrayList<Integer>> buildJumps();

  /**
   * @return returns starting point for the repeat (Lowest beat in repeat)
   */
  public int getBegining();

  /**
   * @return returns final beat for the repeat (Highest beat in repeat)
   */
  public int getEnding();

  /**
   * Determines if this repeat conflicts with another IRepeat (either they overlap or one is
   * contained in another
   * @param that IRepeat to be compaired
   * @return true if the IRepeats conflict
   */
  public boolean conflictWith(IRepeat that);
}
