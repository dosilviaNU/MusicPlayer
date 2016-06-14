package cs3500.music.view;

import java.util.Collection;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import cs3500.music.model.MidiNote;
import cs3500.music.model.MidiComposition;

/**
 * Created by Jake on 6/14/2016.
 */
public class MidiView implements IMusicView<MidiComposition>, Runnable {
  MidiComposition comp;
  Synthesizer synth;
  MidiChannel[] mc;
  Instrument[] instr;


  public MidiView(MidiComposition composition) {
    this.comp = composition;
    try {
      synth = MidiSystem.getSynthesizer();
      synth.open();
      mc = synth.getChannels();
      instr = synth.getDefaultSoundbank().getInstruments();
    } catch (MidiUnavailableException e1) {
      e1.printStackTrace();
    }
  }


  @Override
  public MidiComposition display() {
    return null;
  }

  @Override
  public void run() {
    this.setInstruments();
    this.playComp();
  }

  private void setInstruments() {
    for (int i = 0; i < 16; i++) {
      mc[i].programChange(0, comp.getInstrument(i));
    }
  }

  public void play(int channel, int note, int volume, boolean play) throws
          InterruptedException {
    if (play) {
      mc[channel].noteOn(note, volume);
    } else {
      mc[channel].noteOff(note, volume);
    }

  }

  public void playComp() {
    int delay = this.comp.getTempo();
    try {
      for (int beat = 0; beat < comp.size(); beat++) {
        Collection<MidiNote> notes = comp.getNotes(beat);
        for (MidiNote n : notes) {
          if (n.getStart() == beat) {
            this.play(n.getChannel(), n.getValue(), n.getVolume(), true);
          }
        }
        Thread.sleep(delay);
        for (MidiNote n : notes) {
          if (n.getStart() + n.getDuration() == beat) {
            this.play(n.getChannel(), n.getValue(), n.getVolume(), false);
          }
        }
      }
    } catch (Exception e) {
    }
  }


}
