package cs3500.music.model.Extra;

import java.util.ArrayList;
import java.util.List;

import cs3500.music.model.IComposition;
import cs3500.music.model.IMusicSheet;
import cs3500.music.model.INote;
import cs3500.music.model.MidiComposition;
import cs3500.music.model.MidiNote;
import cs3500.music.util.MidiCompBuilder;

/**
 * Created by Jake on 6/22/2016.
 */
public class MidiCompRepeat extends MidiComposition implements ICompRepeat<MidiNote> {
  ArrayList<IRepeat> repeats;

  public MidiCompRepeat() {
    super();
    repeats = new ArrayList<IRepeat>();
  }

  public MidiCompRepeat(IComposition comp) {
    addNotes(comp.getNotes());
    this.setTempo(comp.getTempo());
    repeats = new ArrayList<IRepeat>();
    if (comp instanceof MidiCompRepeat) {
      MidiCompRepeat temp = (MidiCompRepeat) comp;
      this.repeats = temp.repeats;
    }
  }

  @Override
  public List<List<Integer>> getJumps() {
    List<List<Integer>> jumps = new ArrayList<List<Integer>>();
    for (IRepeat r: repeats) {
      jumps.addAll(r.buildJumps());
    }
    System.out.println("Jumps in CompRepeat" + jumps);
    return jumps;
  }

  @Override
  public void addRepeat(IRepeat repeat) {
      repeats.add(repeat);
  }

  @Override
  public boolean removeRepeat(IRepeat repeat) {
    if (repeats.remove(repeat)) {
      return true;
    }
    return false;
  }
}
