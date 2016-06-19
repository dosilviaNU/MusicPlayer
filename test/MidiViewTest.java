import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;

import cs3500.music.model.IComposition;
import cs3500.music.model.INote;
import cs3500.music.model.MidiComposition;
import cs3500.music.model.MidiNote;
import cs3500.music.model.ROMidiComposition;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MidiCompBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.IMusicView;
import cs3500.music.view.midi.MidiMock;
import cs3500.music.view.midi.MidiView;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Jake on 6/14/2016.
 */
public abstract class MidiViewTest {
  IComposition comp;
  IMusicView<MidiComposition> mock;
  StringBuilder builder;

  /**
   * Returns a version of the IComposition appropriate for testing.
   * @return IComposition object appropriate for testing
   */
  public abstract IComposition<MidiNote> getComp(IComposition<MidiNote> comp);
  
  public void initData() {
    comp = new MidiComposition();
    buildMary();
    builder = new StringBuilder();
    comp = getComp(comp);

  }

  public void buildMary() {
    comp.setTempo(200000);
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
    mock = new MidiMock(comp, builder);
    mock.display();
    //tests for mary had a little lamb
    //assertEquals(builder.toString(), "");
    assertEquals(comp.size(), 64);
    assertEquals(comp.getNotes().size(), 34);

    assertTrue(builder.toString().contains("MidiObject created!"));
    assertTrue(builder.toString().contains("Receiver obtained"));
    assertTrue(builder.toString().contains("Total notes: 34"));
    assertTrue(builder.toString().contains("Total length (beats): 64"));
    assertTrue(builder.toString().contains("Total length (s): 12"));

    //test some notes
    assertTrue(builder.toString().contains("Message NoteOn\tChan: 0\tValue: 62\tVelocity: 60\n" +
            "Message NoteOff\tChan: 0\tValue: 62\tVelocity: 60\n" +
            "Receiver: Message NoteOn\tChan: 0\tValue: 62\tVelocity: 60At time:7200000\n" +
            "Receiver: Message NoteOff\tChan: 0\tValue: 62\tVelocity: 60At time:7600000"));
  }

  @Test
  //test df-ttfaf
  public void testTtfaf() throws Exception {
    initData();
    CompositionBuilder<MidiComposition> mcb = new MidiCompBuilder();
    File file1 = new File("df-ttfaf.txt");
    FileReader music = new FileReader(file1);
    Assert.assertTrue(music instanceof Readable);
    MusicReader mr = new MusicReader();
    IComposition comp2 = mr.parseFile(music, mcb);
    comp2 = getComp(comp2);
    mock = new MidiMock(comp2, builder);
    mock.display();

    //assertEquals(builder.toString(), "");
    assertEquals(comp2.size(), 34657);
    assertEquals(comp2.getNotes().size(), 20722);

    assertTrue(builder.toString().contains("MidiObject created!"));
    assertTrue(builder.toString().contains("Receiver obtained"));
    assertTrue(builder.toString().contains("Total notes: " + comp2.getNotes().size()));
    assertTrue(builder.toString().contains("Total length (beats): " + comp2.size()));
    assertTrue(builder.toString().contains("Total length (s): " +
            comp2.size() * comp2.getTempo() / 1000000));











  }

  public static final class testMidiComp extends MidiViewTest {
    @Override
    public IComposition<MidiNote> getComp(IComposition<MidiNote> comp) {
      return comp;
    }
  }

  public static final class testROMidiComp extends MidiViewTest {
    @Override
    public IComposition<MidiNote> getComp(IComposition<MidiNote> comp) {
      return new ROMidiComposition((MidiComposition)comp);
    }
  }


}