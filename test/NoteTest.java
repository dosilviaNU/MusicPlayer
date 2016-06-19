import org.junit.Test;
import org.junit.Assert.*;

import java.util.Random;

import cs3500.music.model.INote;
import cs3500.music.model.MidiNote;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Jake on 6/9/2016.
 */
public abstract class NoteTest {
  abstract INote makeNote(INote.Pitch p, int octave, int start, int length);
  INote middleNote;
  INote lowNote;
  INote highNote;
  INote earlyNote;
  INote shortNote;
  INote bNote;
  INote eNote;

  public void initData() {
    middleNote = makeNote(INote.Pitch.C, 4, 10, 4);
    lowNote = makeNote(INote.Pitch.C, -1, 6, 2);
    highNote = makeNote(INote.Pitch.G, 9, 3, 11);
    earlyNote = makeNote(INote.Pitch.C, 4, 0, 3);
    shortNote = makeNote(INote.Pitch.C, 4, 23, 1);
    bNote = makeNote(INote.Pitch.B, 4, 1, 7);
    eNote = makeNote(INote.Pitch.E, 4, 9, 12);
  }

  //Tests validity of constructor used
  
  @Test(expected = IllegalArgumentException.class)
  //tests constructor errors
  public void testLowOctave() {
    initData();
    INote note = makeNote(INote.Pitch.C, -2, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  //tests constructor errors
  public void testHighOctave() {
    initData();
    INote note = makeNote(INote.Pitch.C, 10, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  //tests constructor errors
  public void testLowStart() {
    initData();
    INote note = makeNote(INote.Pitch.C, 4, -1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  //tests constructor errors
  public void testZeroLength() {
    initData();
    INote note = makeNote(INote.Pitch.C, 4, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  //tests pitch up exception
  public void testPitchUpExcept() {
    initData();
    highNote.pitchUp(1);
  }

  @Test
  //tests pitch up
  public void testPitchUp() {
    initData();
    assertEquals(middleNote.toString(), "C4");
    assertEquals(lowNote.toString(), "C-1");
    assertEquals(bNote.toString(), "B4");
    assertEquals(eNote.toString(), "E4");
    middleNote.pitchUp(1);
    lowNote.pitchUp(3);
    bNote.pitchUp(5);
    eNote.pitchUp(1);
    assertEquals(middleNote.toString(), "C#4");
    assertEquals(lowNote.toString(), "D#-1");
    assertEquals(bNote.toString(), "E5");
    assertEquals(eNote.toString(), "F4");
  }

  @Test(expected = IllegalArgumentException.class)
  //tests pitch down exception
  public void testPitchDownExcept() {
    initData();
    lowNote.pitchDown(1);
  }

  @Test
  //tests pitch down
  public void testPitchDown() {
    initData();
    assertEquals(middleNote.toString(), "C4");
    assertEquals(highNote.toString(), "G9");
    assertEquals(bNote.toString(), "B4");
    assertEquals(eNote.toString(), "E4");
    middleNote.pitchDown(1);
    highNote.pitchDown(3);
    bNote.pitchDown(1);
    eNote.pitchDown(4);
    assertEquals(middleNote.toString(), "B3");
    assertEquals(highNote.toString(), "E9");
    assertEquals(bNote.toString(), "A#4");
    assertEquals(eNote.toString(), "C4");
  }

  @Test
  //test extend
  public void testExtend() {
    initData();
    assertEquals(middleNote.getDuration(), 4);
    assertEquals(highNote.getDuration(), 11);
    assertEquals(bNote.getDuration(), 7);
    assertEquals(eNote.getDuration(), 12);
    middleNote.extend(1);
    highNote.extend(5);
    bNote.extend(4);
    eNote.extend(-1);
    assertEquals(middleNote.getDuration(), 5);
    assertEquals(highNote.getDuration(), 16);
    assertEquals(bNote.getDuration(), 11);
    assertEquals(eNote.getDuration(), 11);
  }

  @Test
  //test shorten
  public void testShorten() {
    initData();
    assertEquals(middleNote.getDuration(), 4);
    assertEquals(highNote.getDuration(), 11);
    assertEquals(bNote.getDuration(), 7);
    assertEquals(eNote.getDuration(), 12);
    middleNote.shorten(1);
    highNote.shorten(4);
    bNote.shorten(-3);
    eNote.shorten(5);
    assertEquals(middleNote.getDuration(), 3);
    assertEquals(highNote.getDuration(), 7);
    assertEquals(bNote.getDuration(), 10);
    assertEquals(eNote.getDuration(), 7);

  }

  @Test(expected = IllegalArgumentException.class)
  //tests shorten exception
  public void testShortenExcept() {
    initData();
    shortNote.shorten(1);
  }

  @Test
  //test shifting notes
  public void testShiftLeft() {
    initData();
    assertEquals(middleNote.getStart(), 10);
    assertEquals(highNote.getStart(), 3);
    assertEquals(bNote.getStart(), 1);
    assertEquals(eNote.getStart(), 9);
    middleNote.shiftLeft(4);
    highNote.shiftLeft(-4);
    bNote.shiftLeft(1);
    eNote.shiftLeft(4);
    assertEquals(middleNote.getStart(), 6);
    assertEquals(highNote.getStart(), 7);
    assertEquals(bNote.getStart(), 0);
    assertEquals(eNote.getStart(), 5);

  }

  @Test(expected = IllegalArgumentException.class)
  //tests exception for left shifting note
  public void testShiftLeftExcept() {
    initData();
    earlyNote.shiftLeft(1);
  }

  @Test
  //test shifting right
  public void testShiftRight() {
    initData();
    assertEquals(middleNote.getStart(), 10);
    assertEquals(highNote.getStart(), 3);
    assertEquals(bNote.getStart(), 1);
    assertEquals(eNote.getStart(), 9);
    middleNote.shiftRight(4);
    highNote.shiftRight(-3);
    bNote.shiftRight(23);
    eNote.shiftRight(2);
    assertEquals(middleNote.getStart(), 14);
    assertEquals(highNote.getStart(), 0);
    assertEquals(bNote.getStart(), 24);
    assertEquals(eNote.getStart(), 11);

  }

  @Test
  //tests 10 randomly created notes for equality (equals and hashCode) changes each field
  // slightly to check expected result (failed equality)
  public void testEqualsAndHash() {
    initData();
    Random rd = new Random();
    for (int i = 0; i < 10; i++) {
      int random = rd.nextInt(119) + 1;
      int oct = (random / 12) - 1;
      //same notes
      INote note1 = makeNote(INote.Pitch.fromValue(random), oct, random, random);
      INote note2 = makeNote(INote.Pitch.fromValue(random), oct, random, random);
      //4 instances of notes with slightly modifies parameters (one pitch, octave, start beat,
      // length
      INote note3 = makeNote(INote.Pitch.fromValue(random),
              (oct == -1 ? oct + 1 :  oct - 1), random, random);
      INote note4 = makeNote(INote.Pitch.fromValue(random),  oct, random + 1, random);
      INote note5 = makeNote(INote.Pitch.fromValue(random),  oct, random, random + 1);
      INote note6 = makeNote(INote.Pitch.fromValue(
              (random == 0 ? random + 1 : random - 1)),  oct, random, random);
      //are notes 1 and 2 the same
      assertEquals(note1, note2);
      assertEquals(note1.hashCode(), note2.hashCode());
      //are all the other ones false
      assertFalse(note1.equals(note3));
      assertFalse(note1.hashCode() == note3.hashCode());
      assertFalse(note1.equals(note4));
      assertFalse(note1.hashCode() == note4.hashCode());
      assertFalse(note1.equals(note5));
      assertFalse(note1.hashCode() == note5.hashCode());
      assertFalse(note1.equals(note6));
      assertFalse(note1.hashCode() == note6.hashCode());
    }

  }


  public static final class MidiNoteTest extends NoteTest {
    INote normalNote;
    INote lowChan;
    INote hiChan;
    INote loVelocity;
    INote hiVelocity;
    public INote makeNote(INote.Pitch p, int octave, int start, int length) {
      return new MidiNote(p, octave, start, length);
    }

    public void initData2 () {
      normalNote = new MidiNote(60, 0, 1, 60, 1);
      lowChan = new MidiNote(60, 0, 1, 60, 0);
      hiChan = new MidiNote(60, 0, 1, 60, 15);
      loVelocity = new MidiNote(60, 0, 1, 0, 1);
      hiVelocity = new MidiNote(60, 0, 1, 127, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    //test channel getters
    public void testChannelExcep1() {
      new MidiNote(60, 0, 1, -1, 60);
    }

    @Test(expected = IllegalArgumentException.class)
    //test channel getters
    public void testChannelExcep2() {

      new MidiNote(60, 0, 1, 16, 60);
    }

    @Test(expected = IllegalArgumentException.class)
    //test channel getters
    public void testVelocityExcep() {
      new MidiNote(60, 0, 1, 1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    //test channel getters
    public void testVelocityExcep2() {
      new MidiNote(60, 0, 1, 1, 128);
    }

    @Test
    //test getChannel
    public void testGetChannel() {
      initData();
      initData2();
      assertEquals(lowChan.getChannel(), 0);
      assertEquals(hiChan.getChannel(), 15);
    }

    @Test
    //test getChannel
    public void testGetVolume() {
      initData();
      initData2();
      assertEquals(loVelocity.getVolume(), 0);
      assertEquals(hiVelocity.getVolume(), 127);
    }

    @Test
    public void getString() {
      INote note;
      for (int i = 0; i < 128; i++) {
        note = new MidiNote(i, 4, 4);
        assertEquals(note.getString(), INote.Pitch.fromValue(i).toString() + ((i / 12) - 1));
      }
      initData();
    }

    @Test(expected = IllegalArgumentException.class)
    //tests constructor errors
    public void testNegativeValue() {
      INote note = new MidiNote(-1, 0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    //tests constructor errors
    public void testHighValue() {
      INote note = new MidiNote(128, 0, 1);
    }
  }
}