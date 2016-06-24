package cs3500.music.view.midi;

import java.lang.management.PlatformLoggingMXBean;
import java.util.Collection;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;

import cs3500.music.model.IComposition;
import cs3500.music.model.MidiComposition;
import cs3500.music.model.MidiNote;

/**
 *
 */
public class MidiView implements IMidiView<IComposition>, Runnable {
  IComposition<MidiNote> comp;
  Sequencer song;
  Sequence sequence;
  Synthesizer synth;
  Receiver receiver;
  MidiChannel[] mc;
  Instrument[] instr;


  public MidiView(IComposition composition) {
    this.comp = composition;
    try {
      this.synth = MidiSystem.getSynthesizer();
      this.synth.open();
      this.receiver = synth.getReceiver();
      mc = synth.getChannels();
      sequence = null;
      try {
        sequence = new Sequence(Sequence.PPQ, comp.getTempo());
      } catch (Exception e) {
      }

      instr = synth.getDefaultSoundbank().getInstruments();
      song = MidiSystem.getSequencer();
      song.open();
    } catch (MidiUnavailableException e1) {
      e1.printStackTrace();
    }
  }


  @Override
  public void display() {
    this.run();
  }

  @Override
  public void run() {
    this.playComp();
  }

  private void setInstruments() {
    for (int i = 0; i < 16; i++) {
      mc[i].programChange(0, comp.getInstrument(i));
    }
  }

  public void playComp() {
    MidiMessage start;
    MidiMessage stop;
    int delay = this.comp.getTempo();
    Track track = sequence.createTrack();
    try {
      song.setSequence(sequence);
    } catch (Exception e) {
    }


    Collection<MidiNote> notes = comp.getNotes();

    for (MidiNote n : notes) {
      start = null;
      stop = null;
      try {
        start = new ShortMessage(ShortMessage.NOTE_ON, n.getChannel(), n.getValue(),
                n.getVolume());
        stop = new ShortMessage(ShortMessage.NOTE_OFF, n.getChannel(), n.getValue(),
                n.getVolume());
      } catch (Exception e) {
      }
      MidiEvent me = new MidiEvent(start, n.getStart() * comp.getTempo());
      MidiEvent me2 = new MidiEvent(stop, (n.getStart() + n.getDuration()) * comp.getTempo());
      track.add(me);
      track.add(me2);
    }
    song.setTempoInMPQ(comp.getTempo());
    song.start();
    System.out.print(sequence.getMicrosecondLength());
  }

  @Override
  public int getBeat() {
    int result = (int)Math.ceil((song.getMicrosecondPosition()/ this.comp.getTempo()));
    //return 0 if piece hasn't started yet.
    System.out.println(result);
    return  (result < 0 ? 0 : result);
  }

  @Override
  public void play() {
    this.run();
  }

  @Override
  public void resumePlay(int beat) {
    throw new UnsupportedOperationException("Invalid Operation");
  }
}