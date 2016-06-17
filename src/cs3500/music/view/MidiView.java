package cs3500.music.view;

import java.util.Collection;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

import cs3500.music.model.MidiComposition;
import cs3500.music.model.MidiNote;
import cs3500.music.view.IMusicView;

public class MidiView implements IMusicView, Runnable {
  MidiComposition comp;
  Sequencer song;
  Synthesizer synth;
  Receiver receiver;
  MidiChannel[] mc;
  Instrument[] instr;


  public MidiView(MidiComposition composition) {
    this.comp = composition;
    try {
      this.synth = MidiSystem.getSynthesizer();
      this.synth.open();
      this.receiver = synth.getReceiver();
      mc = synth.getChannels();
      instr = synth.getDefaultSoundbank().getInstruments();
      song = MidiSystem.getSequencer();
    } catch (MidiUnavailableException e1) {
      e1.printStackTrace();
    }
  }


  @Override
  public void display() {

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
    MidiMessage start;
    MidiMessage stop;
    int delay = this.comp.getTempo();
    try {
      Collection<MidiNote> notes = comp.getNotes();
      for (MidiNote n : notes) {
        try {
          start = new ShortMessage(ShortMessage.NOTE_ON, n.getChannel(), n.getValue(),
                  n.getVolume());
          stop = new ShortMessage(ShortMessage.NOTE_OFF, n.getChannel(), n.getValue(),
                  n.getVolume());
          this.receiver.send(start, (1 + n.getStart()) * delay);
          this.receiver.send(stop, (1 + n.getStart() + n.getDuration()) * delay);
        } catch (Exception e) {
        }
      }
    } catch (Exception e) {
    }
  }
}