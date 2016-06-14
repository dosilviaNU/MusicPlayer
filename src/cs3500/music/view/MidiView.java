package cs3500.music.view;

import java.util.Collection;
import java.util.List;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import cs3500.music.model.MidiNote;
import cs3500.music.model.MidiSheet;
import cs3500.music.model.MidiComposition;

/**
 * Created by Jake on 6/14/2016.
 */
public class MidiView implements IMusicView<MidiComposition>, Runnable {
  MidiComposition comp;

  MidiView(MidiComposition composition) {
    this.comp = composition;
  }
  @Override
  public MidiComposition display() {
    return null;
  }

  @Override
  public void run() {

  }


}
