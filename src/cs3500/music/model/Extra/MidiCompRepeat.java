package cs3500.music.model.Extra;

import java.util.ArrayList;
import java.util.List;

import cs3500.music.model.MidiComposition;
import cs3500.music.util.MidiCompBuilder;

/**
 * Created by Jake on 6/22/2016.
 */
public class MidiCompRepeat extends MidiComposition implements ICompRepeat {
  ArrayList<IRepeat> repeats;

  MidiCompRepeat() {
    super();
    repeats = new ArrayList<IRepeat>();
  }

  MidiCompRepeat(MidiComposition comp) {
    addNotes(comp.getNotes());
    this.setTempo(comp.getTempo());
    repeats = new ArrayList<IRepeat>();
  }

  @Override
  public List<List<Integer>> getJumps() {
    return null;
  }

  @Override
  public void addRepeat(IRepeat repeat) {

  }

  @Override
  public boolean removeRepeat(IRepeat repeat) {
    return false;
  }
}
