package cs3500.music.model.Extra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by Jake on 6/22/2016.
 */
public class BasicRepeat implements IRepeat {
  private int start;
  private int end;

  public BasicRepeat(int start, int end) {
    this.start = start;
    this.end = end;
    System.out.println("BuildingBasic" + start + "," + end);
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {return true;}
    if (o instanceof BasicRepeat) {
      BasicRepeat r = (BasicRepeat) o;
      return r.start == this.start && r.end == this.end;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.start, this.end);
  }
}
