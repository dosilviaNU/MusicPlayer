package cs3500.music.model.Extra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by Jake on 6/22/2016.
 */
public class AltEndRepeat implements IRepeat {
  private int start;
  private ArrayList<Integer> ends;

  public AltEndRepeat(int start, List<Integer> ends) {
    System.out.println("BuildingAltEnd" + start + "," + ends.toString());
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
    this.ends = new ArrayList<Integer>();
    this.ends.addAll(ends);
    Collections.sort(ends);
  }


  @Override
  public ArrayList<ArrayList<Integer>> buildJumps() {
    ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
    System.out.println(ends.size());
    if (ends.size() == 1) {
      results.add(new ArrayList<Integer>(Arrays.asList(ends.get(0), start)));
    } else {
      results.add(new ArrayList<Integer>(Arrays.asList(ends.get(1), start)));
      for (int i = 1; i < ends.size() - 1; i++) {
        results.add(new ArrayList<Integer>(Arrays.asList(ends.get(0), ends.get(i))));
        //do not add the last jump to begining in the final ending
        if (i+1 != ends.size()-1) {
          results.add(new ArrayList<Integer>(Arrays.asList(ends.get(i + 1), start)));
        }
      }
    }
    System.out.println("buildjumps for altend");
    System.out.println(results);
    return results;
  }

  @Override
  public int getBeginning() {
    return start;
  }

  @Override
  public int getEnding() {
    int max = 0;
    for (Integer i : ends) {
      if (i > max) {
        max = i;
      }
    }
    return max;
  }

  @Override
  public boolean conflictWith(IRepeat that) {
    int max = this.getEnding();
    return (that.getBeginning() >= this.getBeginning() && that.getBeginning() <= max) ||
            (that.getEnding() >= this.getBeginning() && that.getEnding() <= max) ||
            (this.getBeginning() >= that.getBeginning() &&
                    this.getBeginning() <= that.getEnding()) ||
            (max >= that.getBeginning() && max <= that.getEnding());
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o instanceof AltEndRepeat) {
      AltEndRepeat r = (AltEndRepeat) o;
      return r.getBeginning() == this.getBeginning() && r.getEnding() == this.getEnding() && r
              .ends.size() == this.ends.size();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.start, this.ends);
  }
}
