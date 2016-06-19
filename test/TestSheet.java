import cs3500.music.model.IMusicSheet;
import cs3500.music.model.INote;
import cs3500.music.model.MidiNote;
import cs3500.music.model.MidiSheet;

/**
 * Created by David on 6/16/2016.
 */
public class TestSheet {
  public TestSheet() {
  }

  public static IMusicSheet getSheet() {
    IMusicSheet sheet3 = new MidiSheet();
    sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 0, 5));
    sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 2, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.C, 4, 4, 7));
    sheet3.addNote(new MidiNote(INote.Pitch.C, 4, 5, 7));
    sheet3.addNote(new MidiNote(INote.Pitch.C, 4, 6, 7));
    sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 6, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 8, 5));
    sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 10, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 12, 3));
    sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 16, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 18, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 20, 4));
    sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 24, 2));
    sheet3.addNote(new MidiNote(INote.Pitch.G, 4, 26, 8));
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
    return sheet3;
  }
}
