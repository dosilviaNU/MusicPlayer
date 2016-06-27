package cs3500.music.model.Extra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jake on 6/22/2016.
 */
public class AltEndRepeat implements IRepeat {
  private int start;
  private ArrayList<Integer> ends;

  AltEndRepeat(int start, List<Integer> ends) {
    if (start < 0) {
      throw new IllegalArgumentException("Start can't be less than 0!");
    }
    if (ends.size() == 0) {
      throw new IllegalArgumentException("Must have at least one ending point");
    }
    for (Integer i : ends) {
      if (i <= start) {
        throw new IllegalArgumentException("Endings can't be before repeat start");
      }
    }
    this.start = start;
    ends = new ArrayList<Integer>();
    ends.addAll(ends);
    Collections.sort(ends);
  }


  @Override
  public ArrayList<ArrayList<Integer>> buildJumps() {
    if (ends.size() == 1) {
      ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
      results.add(new ArrayList<Integer>(Arrays.asList(ends.get(0), start)));
    }
    else {}
    return null;
  }

  @Override
  public int getBegining() {
    return start;
  }

  @Override
  public int getEnding() {
    int max = 0;
    for (Integer i: ends) {
      if ( i > max) { max = i; }
    }
    return max;
  }

  @Override
  public boolean conflictWith(IRepeat that) {
    int max = this.getEnding();
    return (that.getBegining() >= this.getBegining() && that.getBegining() <= max) ||
            (that.getEnding() >= this.getBegining() && that.getEnding() <= max) ||
            (this.getBegining() >= that.getBegining() &&
                    this.getBegining() <= that.getEnding()) ||
            (max >= that.getBegining() && max <= that.getEnding());
  }

}
