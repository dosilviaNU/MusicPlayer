package cs3500.music.model;

import java.util.Collection;

/**
 * Read only version of a MidiComposition.
 * The only supported operations are getNotes(), getNotes(int beat), getTempo(), getInstrument
 * (), size(), and getSpread(), clone().
 * Throws unsupported operations exceptions for any other operation
 * Created by Jake on 6/15/2016.
 */
public class ROMidiComposition implements IComposition<MidiNote> {
  MidiComposition comp;

  public ROMidiComposition(MidiComposition comp) {
    this.comp = comp;
  }

  @Override
  public int getTempo() {
    return comp.getTempo();
  }

  @Override
  public void setTempo(int tempo) {
    throw new UnsupportedOperationException("Read Only");
  }

  @Override
  public void setInstrument(int instrument, int channel) {
    throw new UnsupportedOperationException("Read Only");
  }

  @Override
  public int getInstrument(int channel) {
    return comp.getInstrument(channel);
  }

  @Override
  public void addNote(MidiNote midiNote) {
    throw new UnsupportedOperationException("Read Only");
  }

  @Override
  public boolean removeNote(MidiNote midiNote) {
    throw new UnsupportedOperationException("Read Only");
  }

  @Override
  public void addNotes(Collection<MidiNote> notes) {
    throw new UnsupportedOperationException("Read Only");
  }

  @Override
  public void mergeSheets(IMusicSheet<MidiNote> sheet) {
    throw new UnsupportedOperationException("Read Only");
  }

  @Override
  public void consecutiveSheets(IMusicSheet<MidiNote> sheet) {
    throw new UnsupportedOperationException("Read Only");
  }

  @Override
  public Collection<MidiNote> getNotes() {
    return comp.getNotes();
  }

  @Override
  public Collection<MidiNote> getNotes(int beat) {
    return comp.getNotes(beat);
  }

  @Override
  public String printSheet() {
    return comp.printSheet();
  }

  @Override
  public int size() {
    return comp.size();
  }

  @Override
  public boolean edit(MidiNote oldNote, MidiNote newNote) {
    throw new UnsupportedOperationException("Read Only");
  }

  @Override
  public IMusicSheet<MidiNote> clone() {
    throw new UnsupportedOperationException("Read Only");
  }

  @Override
  public int[] getSpread(Collection<MidiNote> notes) {
    return comp.getSpread(notes);
  }
}
