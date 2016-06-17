import org.junit.Test;

import cs3500.music.model.IComposition;
import cs3500.music.model.MidiNote;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MidiCompBuilder;

import static org.junit.Assert.*;

/**
 * Created by Jake on 6/17/2016.
 */
public abstract class CompBuilderTest {
  abstract CompositionBuilder makeCompBuilder();
  CompositionBuilder compB1;
  CompositionBuilder compB2;
  
  public void initData() {
    compB1 = makeCompBuilder();
    compB2 = makeCompBuilder();

    compB2.addNote(0, 7, 1, 53, 60);
    compB2.addNote(8, 15, 2, 53, 60);
    compB2.addNote(16, 24, 3, 53, 60);
    compB2.addNote(24, 31, 4, 53, 60);
    compB2.addNote(32, 39, 4, 53, 60);
    compB2.addNote(40, 48, 1, 53, 60);
    compB2.addNote(56, 64, 1, 52, 60);
    compB2.addNote(0, 2, 1, 64, 60);
  }

  @Test
  //test that build produces a valid composition
  public void testBuild() {
    initData();
    IComposition test = (IComposition<MidiNote>)compB2.build();
    assertEquals(test.getTempo(), 250);
    assertEquals(test.size(), 65);
    assertEquals(test.getSpread(test.getNotes())[0], 52);
    assertEquals(test.getSpread(test.getNotes())[1], 64);
    assertEquals(test.getNotes().size(), 8);
  }

  @Test
  //test that tempo sets the tempo of piece
  public void setTempo() {
    initData();
    IComposition test = (IComposition<MidiNote>)compB2.build();
    assertEquals(test.getTempo(), 250);
    compB2.setTempo(200000);
    test = (IComposition<MidiNote>)compB2.build();
    assertEquals(test.getTempo(), 200000);
  }

  @Test(expected = IllegalArgumentException.class)
  //test that tempo sets the tempo of piece exception
  public void setTempoExcept() {
    initData();
    IComposition test = (IComposition<MidiNote>)compB2.build();
    assertEquals(test.getTempo(), 250);
    compB2.setTempo(-1);
    test = (IComposition<MidiNote>)compB2.build();
    assertEquals(test.getTempo(), 200000);
  }

  @Test
  //test that tempo sets the tempo of piece
  public void setTempoExceptCatch() {
    initData();
    IComposition test = (IComposition<MidiNote>)compB2.build();
    assertEquals(test.getTempo(), 250);
    try { compB2.setTempo(-1); }
    catch (Exception e) { }
    assertEquals(test.getTempo(), 250);
    compB2.setTempo(200000);
    test = (IComposition<MidiNote>)compB2.build();
    assertEquals(test.getTempo(), 200000);
  }

  @Test
  //randomize notes in compB2, add them and ensure compositions created match
  public void addNote() {
    initData();
    compB1.addNote(40, 48, 1, 53, 60);
    compB1.addNote(0, 7, 1, 53, 60);
    compB1.addNote(8, 15, 2, 53, 60);
    compB1.addNote(16, 24, 3, 53, 60);
    compB1.addNote(24, 31, 4, 53, 60);
    compB1.addNote(32, 39, 4, 53, 60);
    compB1.addNote(56, 64, 1, 52, 60);
    compB1.addNote(0, 2, 1, 64, 60); //test note

    IComposition test = (IComposition<MidiNote>)compB2.build();
    IComposition test2 = (IComposition<MidiNote>)compB1.build();

    //to the compositions contain the same notes?
    assertTrue(test2.getNotes().containsAll(test.getNotes()));

    //does test2 contain a midinote that was added
    assertTrue(test2.getNotes().contains(new MidiNote(64, 0, 3, 60, 0)));
  }

  @Test
  //randomize notes in compB2, add them and ensure compositions created match
  public void addNoteCatch() {
    initData();
    compB1.addNote(40, 48, 1, 53, 60);
    compB1.addNote(0, 7, 1, 53, 60);
    compB1.addNote(8, 15, 2, 53, 60);
    try {
      compB1.addNote(8, 15, 2, 53, 604);}
    catch(Exception e) {}
    //continue adding
    compB1.addNote(16, 24, 3, 53, 60);
    compB1.addNote(24, 31, 4, 53, 60);
    compB1.addNote(32, 39, 4, 53, 60);
    compB1.addNote(56, 64, 1, 52, 60);
    compB1.addNote(0, 2, 1, 64, 60); //test note

    IComposition test = (IComposition<MidiNote>)compB2.build();
    IComposition test2 = (IComposition<MidiNote>)compB1.build();

    //to the compositions contain the same notes?
    assertTrue(test2.getNotes().containsAll(test.getNotes()));

    //does test2 contain a midinote that was added
    assertTrue(test2.getNotes().contains(new MidiNote(64, 0, 3, 60, 0)));
  }
  
  public static final class MidiCompBuilderTest extends CompBuilderTest {
    @Override
    CompositionBuilder makeCompBuilder() {
      return new MidiCompBuilder();
    }
  }

}