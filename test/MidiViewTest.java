import org.junit.Test;

import cs3500.music.model.INote;
import cs3500.music.model.MidiComposition;
import cs3500.music.model.MidiNote;
import cs3500.music.view.midi.MidiView;

/**
 * Created by Jake on 6/14/2016.
 */
public class MidiViewTest {
  MidiComposition comp;
  
  public void initData() {
    comp = new MidiComposition();
    buildMary();
  }

  public void buildMary() {
    comp.addNote(new MidiNote(INote.Pitch.G, 3, 0, 7));
    comp.addNote(new MidiNote(INote.Pitch.G, 3, 8, 7));
    comp.addNote(new MidiNote(INote.Pitch.G, 3, 16, 8));
    comp.addNote(new MidiNote(INote.Pitch.G, 3, 24, 2));
    comp.addNote(new MidiNote(INote.Pitch.G, 3, 32, 7));
    comp.addNote(new MidiNote(INote.Pitch.G, 3, 40, 8));
    comp.addNote(new MidiNote(INote.Pitch.G, 3, 48, 8));
    comp.addNote(new MidiNote(INote.Pitch.E, 3, 56, 8));
    comp.addNote(new MidiNote(INote.Pitch.E, 4, 0, 2));
    comp.addNote(new MidiNote(INote.Pitch.D, 4, 2, 2));
    comp.addNote(new MidiNote(INote.Pitch.C, 4, 4, 2));
    comp.addNote(new MidiNote(INote.Pitch.D, 4, 6, 2));
    comp.addNote(new MidiNote(INote.Pitch.E, 4, 8, 2));
    comp.addNote(new MidiNote(INote.Pitch.E, 4, 10, 2));
    comp.addNote(new MidiNote(INote.Pitch.E, 4, 12, 3));
    comp.addNote(new MidiNote(INote.Pitch.D, 4, 16, 2));
    comp.addNote(new MidiNote(INote.Pitch.D, 4, 18, 2));
    comp.addNote(new MidiNote(INote.Pitch.D, 4, 20, 4));
    comp.addNote(new MidiNote(INote.Pitch.E, 4, 24, 2));
    comp.addNote(new MidiNote(INote.Pitch.G, 4, 26, 2));
    comp.addNote(new MidiNote(INote.Pitch.G, 4, 28, 4));
    comp.addNote(new MidiNote(INote.Pitch.E, 4, 32, 2));
    comp.addNote(new MidiNote(INote.Pitch.D, 4, 34, 2));
    comp.addNote(new MidiNote(INote.Pitch.C, 4, 36, 2));
    comp.addNote(new MidiNote(INote.Pitch.D, 4, 38, 2));
    comp.addNote(new MidiNote(INote.Pitch.E, 4, 40, 2));
    comp.addNote(new MidiNote(INote.Pitch.E, 4, 42, 2));
    comp.addNote(new MidiNote(INote.Pitch.E, 4, 44, 2));
    comp.addNote(new MidiNote(INote.Pitch.E, 4, 46, 2));
    comp.addNote(new MidiNote(INote.Pitch.D, 4, 48, 2));
    comp.addNote(new MidiNote(INote.Pitch.D, 4, 50, 2));
    comp.addNote(new MidiNote(INote.Pitch.E, 4, 52, 2));
    comp.addNote(new MidiNote(INote.Pitch.D, 4, 54, 2));
    comp.addNote(new MidiNote(INote.Pitch.C, 4, 56, 8));
  }

  @Test
  //testing Midi player
  public void testMidi() {
    initData();

    /*MidiView player = new MidiView(comp);
    new Thread(player).start();
    player.playComp();*/
    comp.setTempo(200);
    MidiView player = new MidiView(comp);
    player.playComp();
    try {
    Thread.sleep(1000); }
    catch(Exception e) {}
  }


}