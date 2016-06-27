package cs3500.music.view.midi;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

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
import cs3500.music.view.ErrorWindow.ErrorWindow;

/**
 *
 */
public class MidiView implements IMidiView<IComposition>, Runnable {
  private IComposition<MidiNote> comp;
  private Sequencer song;
  private Sequence sequence;
  private int beat;

  public MidiView(IComposition composition) {
    this.comp = composition;
    try {
      sequence = new Sequence(Sequence.PPQ, comp.getTempo());
      song = MidiSystem.getSequencer();
      song.open();
      } catch (Exception e) {

      }
    beat = (int) (song.getMicrosecondPosition()/comp.getTempo());
  }


  @Override
  public void display() {
    this.run();
  }

  @Override
  public void run() {
    this.setup();
  }

  private void setInstruments() {
    for (int i = 0; i < 16; i++) {
      //Not used
    }
  }

  private void setup() {
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
      try {
        //create Shortmessages
        start = new ShortMessage(ShortMessage.NOTE_ON, n.getChannel(), n.getValue(),
                n.getVolume());
        stop = new ShortMessage(ShortMessage.NOTE_OFF, n.getChannel(), n.getValue(),
                n.getVolume());
        //Turn into midiEvents
        MidiEvent me = new MidiEvent(start,(n.getStart()) * delay);
        MidiEvent me2 = new MidiEvent(stop,(n.getStart() + n.getDuration()) * delay);
        //add midievents to track
        track.add(me);
        track.add(me2);
      } catch (Exception e) {
        new ErrorWindow(e.getMessage(), "Sequencer Failed to Start!");
      }
    }
    song.setTempoInMPQ(delay);
    //System.out.print(song.getTempoFactor());
    System.out.println("Loaded");
  }

  @Override
  public int getBeat() {
    beat = (int) ((song.getTickPosition()) / (comp.getTempo()));
    return beat;
  }

  @Override
  public void loadComp(IComposition<MidiNote> comp) {
    this.comp = comp;
    try {
      sequence = new Sequence(Sequence.PPQ, comp.getTempo());
      song = MidiSystem.getSequencer();
      song.open();
    } catch (Exception e) {

    }
    beat = (int) (song.getMicrosecondPosition()/comp.getTempo());
    this.setup();
  }

  @Override
  public void play() {
    song.close();
    System.out.println("Playing");
    this.loadComp(comp);
    song.start();
    song.setTempoInMPQ(comp.getTempo());
  }

  @Override
  public void stop() {
    System.out.println("Stopping!");
    song.stop(); }

  @Override
  public void resume(long beat) {
    System.out.println("Going to beat: " + beat);
    song.setTickPosition(beat);
    song.setTempoInMPQ(comp.getTempo());
    song.start();
    song.setTempoInMPQ(comp.getTempo());}

}