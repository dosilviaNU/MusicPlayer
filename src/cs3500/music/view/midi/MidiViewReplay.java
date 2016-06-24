package cs3500.music.view.midi;

import java.util.Collection;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.ShortMessage;

import cs3500.music.model.IComposition;
import cs3500.music.model.MidiNote;

/**
 * MidiView extended to support playback Created by Jake on 6/21/2016.
 */
public class MidiViewReplay extends MidiView {

  public MidiViewReplay(IComposition composition) {
    super(composition);
  }

  /*
  public void resumePlay(int beat) {
    MidiMessage start;
    MidiMessage stop;
    int delay = this.comp.getTempo();
    Collection<MidiNote> notes = comp.getNotes();
    for (MidiNote n : notes) {
      if (n.getStart() >= beat) {
        try {
          start = new ShortMessage(ShortMessage.NOTE_ON, n.getChannel(), n.getValue(),
                  n.getVolume());
          stop = new ShortMessage(ShortMessage.NOTE_OFF, n.getChannel(), n.getValue(),
                  n.getVolume());
          this.receiver.send(start, 400000 + (n.getStart() - beat) * delay);
          this.receiver.send(stop, 400000 + (n.getStart() + n.getDuration() - beat) * delay);
        } catch (Exception e) {
        }
      }
    }
  }*/


}
