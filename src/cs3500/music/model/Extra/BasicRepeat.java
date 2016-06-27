package cs3500.music.model.Extra;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jake on 6/22/2016.
 */
public class BasicRepeat implements IRepeat {
  private int start;
  private int end;

  BasicRepeat(int start, int end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public ArrayList<ArrayList<Integer>> buildJumps() {
    ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
    results.add(new ArrayList<Integer>(Arrays.asList(end, start)));
    return results;
  }

  @Override
  public int getBeginning() {
    return start;
  }

  @Override
  public int getEnding() {
    return end;
  }

  @Override
  public boolean conflictWith(IRepeat that) {
    return (that.getBeginning() >= this.getBeginning() && that.getBeginning() <= this.getEnding()) ||
            (that.getEnding() >= this.getBeginning() && that.getEnding() <= this.getEnding()) ||
            (this.getBeginning() >= that.getBeginning() &&
                    this.getBeginning() <= that.getEnding()) ||
            (this.getEnding() >= that.getBeginning() && this.getEnding() <= that.getEnding());
  }
}
