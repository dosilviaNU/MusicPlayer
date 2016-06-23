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
    this.viewer = new GuiView(sheet);
    playing = true;

  }


  @Override
  public int getBeat() {
    return player.getBeat();
  }

  @Override
  public void play() {
    player.play();
  }

  @Override
  public void resumePlay(int beat) {
    player.resumePlay(beat);
  }

  @Override
  public INote getNoteFromFields() {
    return viewer.getNoteFromFields();
  }

  @Override
  public INote getNoteFromList() {
    return viewer.getNoteFromList();
  }

  @Override
  public void redraw() {
    viewer.redraw();
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
  public void remove() {
    viewer.remove();
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

  public void updateMidiComp(IComposition sheet){
    this.player = new MidiView(sheet);
  }

  public void display(){
    viewer.display();
  }

  public void startBeat(){
    while (playing){
      viewer.updateBeat(player.getBeat());
    }
  }
}
