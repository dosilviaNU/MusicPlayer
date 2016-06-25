package cs3500.music.view.CompositeView;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Collection;

import cs3500.music.model.IComposition;
import cs3500.music.model.INote;
import cs3500.music.view.GuiView.GuiView;
import cs3500.music.view.GuiView.IGuiView;
import cs3500.music.view.midi.IMidiView;
import cs3500.music.view.midi.MidiView;

/**
 * Created by David on 6/23/2016.
 */
public class CompositeView implements ICompositeView {
  IMidiView player;
  IGuiView viewer;
  boolean playing;

  public CompositeView(IComposition sheet){
    this.player = new MidiView(sheet);
    player.run();
    this.viewer = new GuiView(sheet);
    playing = false;

  }


  @Override
  public int getBeat() {
    return player.getBeat();
  }

  @Override
  public void play() { player.play();
    playing = true; }

  @Override
  public void stop() {
    player.stop();
    playing = false;
  }

  @Override
  public void resume(long position) {
    playing = true;
    player.resume(position); }

  @Override
  public INote getNoteFromFields() {
    return viewer.getNoteFromFields();
  }

  @Override
  public INote getNoteFromList() {
    return viewer.getNoteFromList();
  }

  @Override
  public void addKListener(KeyListener keyListener) {
    viewer.addKListener(keyListener);
  }

  @Override
  public void addAListener(ActionListener actionListener) {
    viewer.addAListener(actionListener);
  }

  @Override
  public void addMListener(MouseListener mouseListener) {
    viewer.addMListener(mouseListener);
  }

  @Override
  public int[] getNoteFromClick(int x, int y) {
    return viewer.getNoteFromClick(x, y);
  }

  @Override
  public void fieldsFromClick(int[] noteValue) {
    viewer.fieldsFromClick(noteValue);
  }

  @Override
  public String getFileFromField() {
    return viewer.getFileFromField();
  }

  @Override
  public void scrollLeft() {
    viewer.scrollLeft();
  }

  @Override
  public void scrollRight() {
    viewer.scrollRight();
  }

  @Override
  public void scrollUp() {
    viewer.scrollUp();
  }

  @Override
  public void scrollDown() {
    viewer.scrollDown();
  }

  @Override
  public void updateBeat(int beat) {
    viewer.updateBeat(beat);
  }

  @Override
  public void scrollToEnd() {
    viewer.scrollToEnd();
  }

  @Override
  public void scrollToStart() {
    viewer.scrollToStart();
  }

  @Override
  public void populateNoteList(Collection<INote> notes) {
    viewer.populateNoteList(notes);
  }

  @Override
  public void updateNotes(Collection<INote> notes, int[] spread) {
    viewer.updateNotes(notes, spread);
  }

  @Override
  public void updateMidiComp(IComposition sheet){
    player.loadComp(sheet);
    viewer.updateBeat(0);
  }

  @Override
  public void display(){
    viewer.display();
  }

  @Override
  public void giveFocus() {
    viewer.giveFocus();
  }
}
