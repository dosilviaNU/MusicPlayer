package cs3500.music.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.awt.event.KeyEvent.VK_END;
import static java.awt.event.KeyEvent.VK_HOME;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_P;
import static java.awt.event.KeyEvent.VK_RIGHT;

import cs3500.music.model.IComposition;
import cs3500.music.model.INote;
import cs3500.music.model.MidiComposition;

import cs3500.music.util.MidiCompBuilder;
import cs3500.music.util.MusicReader;

import cs3500.music.view.CompositeView.CompositeView;
import cs3500.music.view.CompositeView.ICompositeView;



/**
 * Concrete Class implementing the IController Interface Created by Jake on 6/21/2016.
 */
public class MidiController implements IController {
  IComposition sheet;
  ICompositeView viewer;
  SwingActionListener actionListener;
  SwingKeyboardListener keyListener;
  SwingMouseListener mouseListener;
  Map<String, Runnable> actionMap;
  Map<Integer, Runnable> keyMap;
  boolean play;


  /**
   * Class to represent an CompositeView controller.
   * @param musicSheet Sheet of musive to be passed to composite view.
   */
  public MidiController(IComposition musicSheet) {
    this.viewer = new CompositeView(musicSheet);
    this.sheet = musicSheet;

    buildActionMap();
    buildKeyMap();

    actionListener = new SwingActionListener(this);
    keyListener = new SwingKeyboardListener(this);
    mouseListener = new SwingMouseListener(this);

    viewer.addAListener(actionListener);
    viewer.addKListener(keyListener);
    viewer.addMListener(mouseListener);
    viewer.display();
  }

  /**
   * Build the action listener map of action commands -> Runnable.
   */
  private void buildActionMap(){
    actionMap = new HashMap<String, Runnable>();
    actionMap.put("add", new AddNote());
    actionMap.put("remove", new RemoveNote());
    actionMap.put("edit", new EditNote());
    actionMap.put("open", new OpenFile());
  }

  /**
   * Build the key listener map of key inputs -> Runnable.
   */
  private void buildKeyMap(){
    keyMap = new HashMap<Integer, Runnable>();
    keyMap.put(VK_LEFT, new ScrollLeft());
    keyMap.put(VK_RIGHT, new ScrollRight());
    keyMap.put(VK_P, new StartPlay());
    keyMap.put(VK_HOME, new ScrollHome());
    keyMap.put(VK_END, new ScrollEnd());
  }

  @Override
  public void keyboardRunnable(int key) {
    Runnable action = keyMap.get(key);
    action.run();
  }

  @Override
  public void actionRunnable(String key) {
    Runnable action = actionMap.get(key);
    action.run();
  }

  @Override
  public void mouseRunnable(int x, int y) {
    int[] noteValues = viewer.getNoteFromClick(x, y);
    Collection<INote> notes = sheet.getNotes(noteValues[0],noteValues[1]);
    if(notes.size() == 0){
    viewer.fieldsFromClick(noteValues);
    }
    viewer.populateNoteList(notes);
  }

  /**
   * Runnable function object for adding a note to the composite view.
   */
  class AddNote implements Runnable {
    @Override
    public void run() {
      INote add = viewer.getNoteFromFields();
      sheet.addNote(add);
      viewer.updateNotes(sheet.getNotes(), sheet.getSpread(sheet.getNotes()));
    }
  }

  /**
   * Runnable function object for removing a note from the composite view.
   */
  class RemoveNote implements Runnable {
    @Override
    public void run() {
      INote remove = viewer.getNoteFromFields();
      sheet.removeNote(remove);
      viewer.updateNotes(sheet.getNotes(), sheet.getSpread(sheet.getNotes()));
    }
  }

  /**
   * Runnable function object for editting a note in the composite view.
   */
  class EditNote implements Runnable {
    @Override
    public void run() {
      INote edit = viewer.getNoteFromList();
      INote editTo = viewer.getNoteFromFields();
      sheet.edit(edit, editTo);
      viewer.updateNotes(sheet.getNotes(), sheet.getSpread(sheet.getNotes()));
    }
  }

  /**
   * Runnable function object for opening a file in the composite view.
   */
  class OpenFile implements Runnable{
    @Override
    public void run(){
      File musicFile = new File(viewer.getFileFromField());
      //Parse and build given music text file.
      FileReader music = null;
      try {
        music = new FileReader(musicFile);
      } catch (FileNotFoundException err) {
        throw new IllegalArgumentException("Invalid File.");
      }
      MusicReader mr = new MusicReader();
      MidiCompBuilder mcb = new MidiCompBuilder();
      MidiComposition comp = null;
      try {
        comp = mr.parseFile(music, mcb);
      } catch (IllegalArgumentException err) {
        err.getMessage();
      }
      sheet = comp;
      viewer.updateMidiComp(sheet);
      viewer.updateNotes(sheet.getNotes(),sheet.getSpread(sheet.getNotes()));
    }
  }

  /**
   * Runnable function object for scrollign the composite view to the left.
   */
  class ScrollLeft implements Runnable{
    @Override
    public void run(){
      viewer.scrollLeft();
    }
  }

  /**
   * Runnable function object for scrolling the composite view to the right.
   */
  class ScrollRight implements Runnable{
    @Override
    public void run(){
      viewer.scrollRight();
    }
  }

  /**
   * Runnable function object for scrolling to home.
   */
  class ScrollHome implements Runnable{
    @Override
    public void run() {
      viewer.scrollToStart();
    }
  }

  /**
   * Runnable function object for scrolling to the end.
   */
  class ScrollEnd implements Runnable{
    @Override
    public void run(){
      viewer.scrollToEnd();
    }
  }

  /**
   * Starts playing current IComposition
   */
  //TODO Needs work, beat being passed is not correct in the beginning.
  class StartPlay implements Runnable {
    @Override
    public void run() {
      play=true;
      Play play1 = new Play();
      Thread bar = (new Thread(new UpdateBar(play1)));
      Thread player = new Thread(play1);
      bar.start();
      player.start();
      if(!player.isAlive()){
        play = false;
        viewer.scrollToStart();
      }
    }
  }

  /**
   * Updates the position of the beat bar in the composite view.
   * Synchronized with Play.
   */
  class UpdateBar implements Runnable {
    Play sp;
    public UpdateBar(Play sp) {
      this.sp = sp;
    }
    public void run() {
      synchronized (sp) {
        while (play) {
          viewer.updateBeat(viewer.getBeat());

        }
      }
    }
  }


  /**
   * Plays the music sheet currently loaded in the composite view.
   */
  class Play implements Runnable{
    public void run(){
      viewer.play();
    }
  }


  /**
   * sets the position to the current song beat
   */
  class SetPosition implements Runnable {
    @Override
    public void run() {

    }
  }
  /**
   * Gets the current beat of a playing IComposition
   */
  class GetBeat implements Runnable {
    @Override
    public void run() {

    }
  }

  /**
   * Stops playing current IComposition
   */
  class StopPlay implements Runnable {
    @Override
    public void run() {
      play=false;
    }
  }

  /**
   * Resumes playing current IComposition
   */
  class ResumePlay implements Runnable {
    @Override
    public void run() {

    }
  }

}
