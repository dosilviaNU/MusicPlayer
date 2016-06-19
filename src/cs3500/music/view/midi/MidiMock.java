package cs3500.music.view.midi;

import java.util.Collection;
import java.util.Formatter;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.ShortMessage;

import cs3500.music.model.IComposition;
import cs3500.music.model.MidiComposition;
import cs3500.music.model.MidiNote;

/**
 * Created by Jake on 6/17/2016.
 */
public class MidiMock implements IMidiView<MidiComposition> {
  IComposition<MidiNote> comp;
  Formatter debug;
  MidiObject synth;
  Receiver rec;

  public MidiMock(IComposition composition, Appendable ap) {
    debug = new Formatter(ap);
    this.comp = composition;
    try {
      //add synthesiser
      synth = new MidiObject();
      debug.format("MidiObject created!\n");
      rec = synth.getReceiver();
      debug.format("Receiver obtained!\n");
      /*
      this.synth = MidiSystem.getSynthesizer();
      this.synth.open();
      this.receiver = synth.getReceiver();
      mc = synth.getChannels();
      instr = synth.getDefaultSoundbank().getInstruments();
      song = MidiSystem.getSequencer();
       */
    } catch (Exception e1) {
      //if something went wrong
    }
  }

  @Override
  public long getBeat() {
    //no synthesiser in this mock
    return 0;
  }


  private long getBeatMock(long time) {
    return (time / comp.getTempo()) - 1;
  }

  @Override
  public void display() {
    long totalNotes = 0;
    long totalLength = 0;
    long numBeats = 0;
    /*
    MidiMessage start;
    MidiMessage stop;*/

    String startStr = "";
    String stopStr = "";
    int delay = this.comp.getTempo();
    try {
      //get the notes from the comp
      Collection<MidiNote> notes = comp.getNotes();
      //add each note to the "receiver"
      for (MidiNote n : notes) {
        try {
          /*
          start = new ShortMessage(ShortMessage.NOTE_ON, n.getChannel(), n.getValue(),
                  n.getVolume());
          stop = new ShortMessage(ShortMessage.NOTE_OFF, n.getChannel(), n.getValue(),
                  n.getVolume());*/
          startStr = String.format("Message NoteOn\tChan: %d\tValue: %d\tVelocity: %d",
                  n.getChannel(), n.getValue(), n.getVolume());
          debug.format(startStr + "\n");
          stopStr = String.format("Message NoteOff\tChan: %d\tValue: %d\tVelocity: %d",
                  n.getChannel(), n.getValue(), n.getVolume());
          debug.format(stopStr + "\n");
          /*
          this.receiver.send(start, (1 + n.getStart()) * delay);
          this.receiver.send(stop, (1 + n.getStart() + n.getDuration()) * delay);
          */
          //add the messages to debugger
          rec.send(startStr, 400000 + (n.getStart()) * delay);
          rec.send(stopStr, 400000 + (n.getStart() + n.getDuration()) * delay);
          //update overall piece information
          totalNotes++;
          numBeats = (n.getStart() + n.getDuration() > numBeats ? n.getStart() + n.getDuration
                  () : numBeats);
        } catch (Exception e) {
        }
      }
    } catch (Exception e) {
    }
    debug.format("Done adding notes!\n");
    debug.format("Total notes: " + totalNotes + "\n");
    debug.format("Total length (beats): " + numBeats + "\n");
    debug.format("Total length (s): " + numBeats * comp.getTempo() / 1000000 + "\n");
  }

  class Receiver {
    void send(String message, long time) {
      debug.format("Receiver: " + message + "At time:" + time + "\n");
    }

  }

  class MidiObject {
    Receiver rec;

    MidiObject() {
      rec = new Receiver();
    }

    Receiver getReceiver() {
      return rec;
    }

  }
}
