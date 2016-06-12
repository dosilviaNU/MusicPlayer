import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import cs3500.music.model.IMusicSheet;
import cs3500.music.model.INote;
import cs3500.music.model.MidiNote;
import cs3500.music.model.MidiSheet;

import static org.junit.Assert.*;

/**
 * Created by Jake on 6/9/2016.
 */
public abstract class IMusicSheetTest {
  IMusicSheet sheet1;
  IMusicSheet sheet2;
  IMusicSheet sheet3;
  IMusicSheet sheet4;
  IMusicSheet sheet5;

  public abstract IMusicSheet makeSheet();

  public void initData() {
    sheet1 = makeSheet();
    sheet2 = makeSheet();
    sheet3 = makeSheet();
    sheet4 = makeSheet();
    sheet5 = makeSheet();

    //builds Mary Had a Little Lamb example from assignment
    buildMaryBeat();
    buildMaryMel();

    sheet5.addNote(new MidiNote(60, 0, 2));
  }

  public void buildMaryBeat() {
    sheet2.addNote(new MidiNote(INote.Pitch.G, 3, 0, 7));
    sheet2.addNote(new MidiNote(INote.Pitch.G, 3, 8, 7));
    sheet2.addNote(new MidiNote(INote.Pitch.G, 3, 16, 8));
    sheet2.addNote(new MidiNote(INote.Pitch.G, 3, 24, 2));
    sheet2.addNote(new MidiNote(INote.Pitch.G, 3, 32, 7));
    sheet2.addNote(new MidiNote(INote.Pitch.G, 3, 40, 8));
    sheet2.addNote(new MidiNote(INote.Pitch.G, 3, 48, 8));
    sheet2.addNote(new MidiNote(INote.Pitch.E, 3, 56, 8));
  }

  public void buildMaryMel() {
    sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 0, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 2, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.C, 4, 4, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 6, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 8, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 10, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 12, 3));
    sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 16, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 18, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 20, 4));
    sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 24, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.G, 4, 26, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.G, 4, 28, 4));
    sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 32, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 34, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.C, 4, 36, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 38, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 40, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 42, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 44, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 46, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 48, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 50, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 52, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 54, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.C, 4, 56, 8));
  }

  @Test
  //tests the effect of adding a note
  public void testAddNoteLength() {
    initData();
    assertEquals(sheet1.size(), 0);
    assertEquals(sheet1.printSheet(), "No music in sheet!");
    sheet1.addNote(new MidiNote(60, 0, 1));
    assertEquals(sheet1.size(), 1);
    assertEquals(sheet1.printSheet(), "   C4 \n0  X  \n");

  }

  @Test(expected = IllegalArgumentException.class)
  //tests the effect of adding an illegal note
  public void testAddNoteExcep() {
    initData();
    sheet1.addNote(new MidiNote(128, 2, 5));
  }

  @Test(expected = IllegalArgumentException.class)
  //tests the effect of adding an illegal note
  public void testRemNoteExcep() {
    initData();
    sheet1.removeNote(new MidiNote(128, 2, 5));
  }

  @Test
  //tests the effect of catching an illegal note
  public void testAddNoteExcepCatch() {
    initData();
    assertEquals(sheet1.size(), 0);
    assertEquals(sheet1.printSheet(), "No music in sheet!");
    try { sheet1.addNote(new MidiNote(128, 2, 5)); }
    catch(Exception e) {}
    assertEquals(sheet1.size(), 0);
    assertEquals(sheet1.printSheet(), "No music in sheet!");

    sheet1.addNote(new MidiNote(60, 0, 1));
    assertEquals(sheet1.size(), 1);
    assertEquals(sheet1.printSheet(), "   C4 \n0  X  \n");
  }

  @Test
  //tests the effect of removing an illegal note (catching)
  public void testRemNoteExcepCatch() {
    initData();
    assertEquals(sheet1.size(), 0);
    assertEquals(sheet1.printSheet(), "No music in sheet!");
    try { sheet1.addNote(new MidiNote(128, 2, 5)); }
    catch(Exception e) {}
    assertEquals(sheet1.size(), 0);
    assertEquals(sheet1.printSheet(), "No music in sheet!");
    assertFalse(sheet1.removeNote(new MidiNote(60, 0, 1)));
    sheet1.addNote(new MidiNote(60, 0, 1));
    assertEquals(sheet1.size(), 1);
    assertEquals(sheet1.printSheet(), "   C4 \n0  X  \n");
  }

  @Test
  //tests the removeNote method by removing notes and testing side effects
  public void removeNote() {
    initData();
    assertEquals(sheet2.getNotes().size(), 8);
    assertEquals(sheet2.size(), 64);
    assertTrue(sheet2.removeNote(new MidiNote(INote.Pitch.G, 3, 0, 7)));
    assertEquals(sheet2.getNotes().size(), 7);
    assertEquals(sheet2.size(), 64);
    assertTrue(sheet2.removeNote(new MidiNote(INote.Pitch.G, 3, 8, 7)));
    assertEquals(sheet2.getNotes().size(), 6);
    assertEquals(sheet2.size(), 64);
    assertTrue(sheet2.removeNote(new MidiNote(INote.Pitch.G, 3, 16, 8)));
    assertEquals(sheet2.getNotes().size(), 5);
    assertEquals(sheet2.size(), 64);
  }

  @Test
  public void addNotes() {
    initData();
    ArrayList<MidiNote> notes = new ArrayList<MidiNote>();
    notes.add(new MidiNote(INote.Pitch.G, 3, 0, 7));
    notes.add(new MidiNote(INote.Pitch.G, 3, 8, 7));
    notes.add(new MidiNote(INote.Pitch.G, 3, 16, 8));
    notes.add(new MidiNote(INote.Pitch.G, 3, 24, 2));
    notes.add(new MidiNote(INote.Pitch.G, 3, 32, 7));
    notes.add(new MidiNote(INote.Pitch.G, 3, 40, 8));
    notes.add(new MidiNote(INote.Pitch.G, 3, 48, 8));
    notes.add(new MidiNote(INote.Pitch.E, 3, 56, 8));
    sheet4.addNotes(notes);
    assertEquals(sheet2.size(), sheet4.size());
    Collection<MidiNote> s2 = sheet2.getNotes();
    Collection<MidiNote> s4 = sheet4.getNotes();
    assertTrue(s2.containsAll(s4));

  }

  @Test
  //test size of and number of notes in a merged sheet
  public void testMergeSheets() {
    initData();
    assertEquals(sheet2.size(), 64);
    assertEquals(sheet2.getNotes().size(), 8);
    assertEquals(sheet3.size(), 64);
    assertEquals(sheet3.getNotes().size(), 26);
    sheet2.mergeSheets(sheet3);
    assertEquals(sheet2.size(), 64);
    assertEquals(sheet2.getNotes().size(), 34);
  }

  @Test
  //test size of and number of notes in a consecutive sheet
  public void testConsecutiveSheets() {
    initData();
    assertEquals(sheet2.size(), 64);
    assertEquals(sheet2.getNotes().size(), 8);
    assertEquals(sheet3.size(), 64);
    assertEquals(sheet3.getNotes().size(), 26);
    sheet2.consecutiveSheets(sheet3);
    assertEquals(sheet2.size(), 128);
    assertEquals(sheet2.getNotes().size(), 34);

  }

  @Test
  //tests adding and removing multiple notes of different pitches
  public void testGetNotesMultiple() {
    initData();
    assertEquals(sheet1.getNotes().size(), 0);
    MidiNote n1 = new MidiNote(60, 0, 1);
    sheet1.addNote(n1);
    assertEquals(sheet1.getNotes().size(), 1);
    assertEquals(sheet1.getNotes().contains(n1), true);
    MidiNote n2 = new MidiNote(40, 5, 2);
    sheet1.addNote(n2);
    assertEquals(sheet1.getNotes().size(), 2);
    assertEquals(sheet1.getNotes().contains(n1), true);
    assertEquals(sheet1.getNotes().contains(n2), true);
    MidiNote n3 = new MidiNote(80, 91, 3);
    sheet1.addNote(n3);
    assertEquals(sheet1.getNotes().size(), 3);
    assertEquals(sheet1.getNotes().contains(n1), true);
    assertEquals(sheet1.getNotes().contains(n2), true);
    assertEquals(sheet1.getNotes().contains(n3), true);
    assertTrue(sheet1.removeNote(n1));
    assertEquals(sheet1.getNotes().size(), 2);
    assertEquals(sheet1.getNotes().contains(n1), false);
    assertEquals(sheet1.getNotes().contains(n2), true);
    assertEquals(sheet1.getNotes().contains(n3), true);
    assertTrue(sheet1.removeNote(n2));
    assertEquals(sheet1.getNotes().size(), 1);
    assertEquals(sheet1.getNotes().contains(n1), false);
    assertEquals(sheet1.getNotes().contains(n2), false);
    assertEquals(sheet1.getNotes().contains(n3), true);
    assertTrue(sheet1.removeNote(n3));
    assertEquals(sheet1.getNotes().size(), 0);
    assertEquals(sheet1.getNotes().contains(n1), false);
    assertEquals(sheet1.getNotes().contains(n2), false);
    assertEquals(sheet1.getNotes().contains(n3), false);
    assertEquals(sheet1.printSheet(), "No music in sheet!");
    assertFalse(sheet1.removeNote(n1));
    assertFalse(sheet1.removeNote(n2));
    assertFalse(sheet1.removeNote(n3));
    assertEquals(sheet1.printSheet(), "No music in sheet!");
  }

  @Test
  //tests adding and removing multiple notes of the same pitches
  public void testGetNotesSame() {
    initData();
    assertEquals(sheet1.getNotes().size(), 0);
    MidiNote n1 = new MidiNote(60, 0, 1);
    sheet1.addNote(n1);
    assertEquals(sheet1.getNotes().size(), 1);
    assertEquals(sheet1.getNotes().contains(n1), true);
    sheet1.addNote(n1);
    assertEquals(sheet1.getNotes().size(), 1);
    assertEquals(sheet1.getNotes().contains(n1), true);
    sheet1.addNote(n1);
    assertEquals(sheet1.getNotes().size(), 1);
    assertEquals(sheet1.getNotes().contains(n1), true);
    assertTrue(sheet1.removeNote(n1));
    assertEquals(sheet1.getNotes().size(), 1);
    assertEquals(sheet1.getNotes().contains(n1), true);
    assertTrue(sheet1.removeNote(n1));
    assertEquals(sheet1.getNotes().size(), 1);
    assertEquals(sheet1.getNotes().contains(n1), true);
    assertTrue(sheet1.removeNote(n1));
    assertEquals(sheet1.getNotes().size(), 0);
    assertEquals(sheet1.getNotes().contains(n1), false);
    assertFalse(sheet1.removeNote(n1));
    assertEquals(sheet1.getNotes().size(), 0);
    assertEquals(sheet1.getNotes().contains(n1), false);
  }

  @Test
  //tests adding and removing multiple notes of the same pitches
  public void testGetNotesSamePitchStart() {
    initData();
    assertEquals(sheet1.getNotes().size(), 0);
    MidiNote n1 = new MidiNote(60, 0, 1);
    sheet1.addNote(n1);
    assertEquals(sheet1.getNotes().size(), 1);
    assertEquals(sheet1.getNotes().contains(n1), true);
    MidiNote n2 = new MidiNote(60, 0, 2);
    sheet1.addNote(n2);
    assertEquals(sheet1.getNotes().size(), 2);
    assertEquals(sheet1.getNotes().contains(n1), true);
    assertEquals(sheet1.getNotes().contains(n2), true);
    MidiNote n3 = new MidiNote(60, 0, 3);
    sheet1.addNote(n3);
    assertEquals(sheet1.getNotes().size(), 3);
    assertEquals(sheet1.getNotes().contains(n1), true);
    assertEquals(sheet1.getNotes().contains(n2), true);
    assertEquals(sheet1.getNotes().contains(n3), true);
    assertTrue(sheet1.removeNote(n1));
    assertEquals(sheet1.getNotes().size(), 2);
    assertEquals(sheet1.getNotes().contains(n1), false);
    assertEquals(sheet1.getNotes().contains(n2), true);
    assertEquals(sheet1.getNotes().contains(n3), true);
    assertTrue(sheet1.removeNote(n2));
    assertEquals(sheet1.getNotes().size(), 1);
    assertEquals(sheet1.getNotes().contains(n1), false);
    assertEquals(sheet1.getNotes().contains(n2), false);
    assertEquals(sheet1.getNotes().contains(n3), true);
    assertTrue(sheet1.removeNote(n3));
    assertEquals(sheet1.getNotes().size(), 0);
    assertEquals(sheet1.getNotes().contains(n1), false);
    assertEquals(sheet1.getNotes().contains(n2), false);
    assertEquals(sheet1.getNotes().contains(n3), false);
    assertFalse(sheet1.removeNote(n1));
    assertFalse(sheet1.removeNote(n2));
    assertFalse(sheet1.removeNote(n3));
  }

  @Test
  //basic printSheet() tests
  public void testPrintSheet() {
    initData();
    assertEquals(sheet5.printSheet(), "   C4 \n0  X  \n1  |  \n");
    sheet5.addNote(new MidiNote(60, 0, 1));
    assertEquals(sheet5.printSheet(), "   C4 \n0  X  \n1  |  \n");
    sheet5.addNote(new MidiNote(60, 1, 2));
    assertEquals(sheet5.printSheet(), "   C4 \n0  X  \n1  X  \n2  |  \n");
    sheet5.addNote(new MidiNote(61, 1, 2));
    assertEquals(sheet5.printSheet(), "   C4  C#4 \n0  X       \n1  X    X  \n2  |    |  \n");
  }

  @Test
  //tested a sheet merged with itself to ensure it's print() remains unchanged
  public void testDoubledSheet() {
    initData();
    String expected = sheet3.printSheet();
    sheet3.mergeSheets(sheet3);
    assertEquals(sheet3.printSheet(), expected);
  }

  @Test
  //tests getNotes() from a specific beat
  public void testGetNotesBeat() {
    initData();
    assertTrue(sheet2.getNotes(0).size() == 1);
    assertTrue(sheet3.getNotes(0).size() == 1);//two sheets with differnt notes
    sheet2.mergeSheets(sheet3); //merge sheets
    assertTrue(sheet2.getNotes(0).size() == 2); //now there are two unique notes at beat 0
    sheet2.mergeSheets(sheet2); //duplicate notes on sheet2
    assertTrue(sheet2.getNotes(0).size() == 2); //still two unique notes at beat 0

  }

  @Test
  //tests getNotes() from a specific beat
  public void testGetNotesEdgeCases() {
    initData();
    assertTrue(sheet1.getNotes(0).size() == 0); //getting notes from a newly initialized sheet
    sheet1.addNote(new MidiNote(60, 0, 4));
    assertTrue(sheet1.getNotes(0).size() == 1); //bow there is one note
    sheet1.removeNote(new MidiNote(60, 0, 4));
    assertTrue(sheet1.getNotes(0).size() == 0); // back to 0
  }

  @Test
  //basic printSheet() tests with a header containing every note
  public void testPrintSheetLongHeader() {
    String longHeader = "  C-1  C#-1 D-1  D#-1 E-1  F-1  F#-1 G-1  G#-1 A-1  A#-1 B-1   C0  " +
            "C#0   D0  D#0   E0   F0  F#0   G0  G#0   A0  A#0   B0   C1  C#1   D1  D#1   E1   " +
            "F1  F#1   G1  G#1   A1  A#1   B1   C2  C#2   D2  D#2   E2   F2  F#2   G2  G#2   " +
            "A2  A#2   B2   C3  C#3   D3  D#3   E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  " +
            "C#4   D4  D#4   E4   F4  F#4   G4  G#4   A4  A#4   B4   C5  C#5   D5  D#5   E5   " +
            "F5  F#5   G5  G#5   A5  A#5   B5   C6  C#6   D6  D#6   E6   F6  F#6   G6  G#6   " +
            "A6  A#6   B6   C7  C#7   D7  D#7   E7   F7  F#7   G7  G#7   A7  A#7   B7   C8  " +
            "C#8   D8  D#8   E8   F8  F#8   G8  G#8   A8  A#8   B8   C9  C#9   D9  D#9   E9   " +
            "F9  F#9   G9 \n";
    initData();
    assertEquals(sheet5.printSheet(), "   C4 \n0  X  \n1  |  \n");
    sheet5.addNote(new MidiNote(0, 0, 1));
    sheet5.addNote(new MidiNote(127, 0, 1));
    assertTrue(sheet5.printSheet().contains(longHeader));
  }

  @Test
  // builds Mary Had a Little Lamb from examples tests lines to ensure correctness
  public void testMaryHad() {
    initData();
    String testHeader = "    E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   " +
            "F4  F#4   G4 ";
    String testLine0 = " 0                 X                                            X     " +
            "            ";
    String testLine20 = "20                 |                                  X              " +
            "             ";
    String testLine41 = "41                 |                                            |    " +
            "             ";
    String testLine63 = "63  |                                       |                        " +
            "             ";
    sheet2.mergeSheets(sheet3);
    assertTrue(sheet2.printSheet().contains(testHeader));
    assertTrue(sheet2.printSheet().contains(testLine0));
    assertTrue(sheet2.printSheet().contains(testLine20));
    assertTrue(sheet2.printSheet().contains(testLine41));
    assertTrue(sheet2.printSheet().contains(testLine63));
    assertEquals(sheet2.printSheet(), "");
  }

  @Test
  // builds Mary Had a Little Lamb then adds it to itself, essentially playing it twice
  public void testMaryHadconsecutive() {
    initData();
    String testHeader = "     E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   " +
            "F4  F#4   G4 ";
    String testLine0 = "  0                 X                                            X     " +
            "            ";
    String testLine20 = " 20                 |                                  X              " +
            "             ";
    String testLine41 = " 41                 |                                            |    " +
            "             ";
    String testLine63 = " 63  |                                       |                        " +
            "             ";
    String testLine64 = " 64                 X                                            X    " +
            " " +
            "            ";
    String testLine84 = " 84                 |                                  X              " +
            "             ";
    String testLine105 = "105                 |                                            |   " +
            " " +
            "             ";
    String testLine127 = "127  |                                       |                       " +
            " " +
            "             ";
    sheet2.mergeSheets(sheet3);
    sheet2.consecutiveSheets(sheet2);
    assertTrue(sheet2.printSheet().contains(testHeader));
    assertTrue(sheet2.printSheet().contains(testLine0));
    assertTrue(sheet2.printSheet().contains(testLine20));
    assertTrue(sheet2.printSheet().contains(testLine41));
    assertTrue(sheet2.printSheet().contains(testLine63));
    assertTrue(sheet2.printSheet().contains(testLine64));
    assertTrue(sheet2.printSheet().contains(testLine84));
    assertTrue(sheet2.printSheet().contains(testLine105));
    assertTrue(sheet2.printSheet().contains(testLine127));
  }

  @Test
  //tests a large (1000, 10000, 100000, 1000000 file for beat number formatting
  //Note: all these tests pass, but take extremely long to run at the end so they are commented
  // out
  public void testBeatSize100000Plus() {
    initData();
    String testHeader1000 = "      E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4 " +
            "  E4   F4  F#4   G4 ";
    String testLine1000 = " 127  |                                       |                   " +
            "                  ";
    String testHeader10000 = "       E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  " +
            "D#4   E4   F4  F#4   G4 ";
    String testLine10000 = "  127  |                                       |                 " +
            "                    ";
    String testHeader100000 = "       E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  " +
            "D#4   E4   F4  F#4   G4 ";
    String testLine100000 = "  127  |                                       |                " +
            "                     ";
    String testHeader1000000 = "       E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  " +
            "D#4   E4   F4  F#4   G4 ";
    String testLine1000000 = "  127  |                                       |               " +
            "                      ";
    sheet2.mergeSheets(sheet3);
    sheet2.consecutiveSheets(sheet2);
    assertEquals(sheet2.size(), 128);
    sheet2.consecutiveSheets(sheet2);
    assertEquals(sheet2.size(), 256);
    sheet2.consecutiveSheets(sheet2);
    assertEquals(sheet2.size(), 512);
    sheet2.consecutiveSheets(sheet2);
    assertEquals(sheet2.size(), 1024);
    sheet2.consecutiveSheets(sheet2);
    assertEquals(sheet2.size(), 2048);
    sheet2.consecutiveSheets(sheet2);
    assertEquals(sheet2.size(), 4096);
    sheet2.consecutiveSheets(sheet2);
    assertEquals(sheet2.size(), 8192);
    assertTrue(sheet2.printSheet().contains(testHeader1000));
    assertTrue(sheet2.printSheet().contains(testLine1000));
    assertTrue(sheet2.printSheet().contains("\n8191 "));
    //sheet2.consecutiveSheets(sheet2);
    ///assertEquals(sheet2.size(), 16384);
    //sheet2.consecutiveSheets(sheet2);
    //assertEquals(sheet2.size(), 32768);
    //sheet2.consecutiveSheets(sheet2);
    //assertEquals(sheet2.size(), 65536);
    //assertTrue(sheet2.printSheet().contains(testHeader10000));
    //assertTrue(sheet2.printSheet().contains(testLine10000));
    //sheet2.consecutiveSheets(sheet2);
    //assertEquals(sheet2.size(), 131072);
    //sheet2.consecutiveSheets(sheet2);
    //assertEquals(sheet2.size(), 262144);
    //sheet2.consecutiveSheets(sheet2);
    //assertEquals(sheet2.size(), 524288);
    //assertTrue(sheet2.printSheet().contains(testHeader100000));
    //assertTrue(sheet2.printSheet().contains(testLine100000));
    //sheet2.consecutiveSheets(sheet2);
    //assertEquals(sheet2.size(), 1048576);
    //assertTrue(sheet2.printSheet().contains(testHeader1000000));
    //assertTrue(sheet2.printSheet().contains(testLine1000000));
    //sheet2.consecutiveSheets(sheet2);
    //assertEquals(sheet2.size(), 2097152);
    //assertTrue(sheet2.printSheet().contains(testHeader1000000));
    //assertTrue(sheet2.printSheet().contains(testLine1000000));
    //sheet2.consecutiveSheets(sheet2);
    //assertEquals(sheet2.size(), 4194304);
    //assertTrue(sheet2.printSheet().contains(testHeader1000000));//heap overflow of stringbuilder
    //assertTrue(sheet2.printSheet().contains(testLine1000000));
    //sheet2.consecutiveSheets(sheet2);
    //assertEquals(sheet2.size(), 8388608); //44seconds w/o prints
    //sheet2.consecutiveSheets(sheet2);
    //assertEquals(sheet2.size(), 16777216); //heap overflow of hashmap w/o prints :(

  }

  public static final class MidiSheetTest extends IMusicSheetTest {
    public IMusicSheet makeSheet() {
      return new MidiSheet();
    }
  }

}