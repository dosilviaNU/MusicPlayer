package cs3500.music.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import cs3500.music.model.IComposition;
import cs3500.music.model.INote;
import cs3500.music.model.MidiComposition;
import cs3500.music.util.MidiCompBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.CompositeView.CompositeView;
import cs3500.music.view.CompositeView.ICompositeView;
import cs3500.music.view.GuiView.GuiView;
import cs3500.music.view.GuiView.IGuiView;
import cs3500.music.view.midi.IMidiView;
import cs3500.music.view.midi.MidiView;

import static java.awt.event.KeyEvent.VK_END;
import static java.awt.event.KeyEvent.VK_HOME;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_P;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.MouseEvent.BUTTON1;

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
    this.play = false;
  }

  public static void main(String[] args){
    if (args.length < 1) {
      System.exit(0);
    }
    File musicFile = new File(args[0]);

    //Parse and build given music text file.
    FileReader music = null;
    try {
      music = new FileReader(musicFile);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Invalid File.");
    }
    MusicReader mr = new MusicReader();
    MidiCompBuilder mcb = new MidiCompBuilder();
    MidiComposition comp = null;
    try {
      comp = mr.parseFile(music, mcb);
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }
    MidiController cont = new MidiController(comp);

    while(cont.play()){
      cont.updateBeat();
    }
  }

  public boolean play(){
    return play;
  }

  public void startPlay(){
    play = !play;
    while (play){
      updateBeat();
    }

  }

  public void updateBeat(){
    viewer.updateBeat(viewer.getBeat());
  }

  /**
   * Build the action listener map of action commands -> Runnable.
   */
  private void buildActionMap(){
    actionMap = new HashMap<String, Runnable>();
    actionMap.put("add", new addNote());
    actionMap.put("remove", new removeNote());
    actionMap.put("edit", new editNote());
    actionMap.put("open", new openFile());
  }

  private void buildKeyMap(){
    keyMap = new HashMap<Integer, Runnable>();
    keyMap.put(VK_LEFT, new scrollLeft());
    keyMap.put(VK_RIGHT, new scrollRight());
    keyMap.put(VK_P, new startPlay());
    keyMap.put(VK_HOME, new scrollHome());
    keyMap.put(VK_END, new scrollEnd());
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
   * ACTION LISTENER RUNNABLES PAST THIS POINT!
   */
  /**
   * Add a note to a IComposition
   */
  //KEY WILL BE "add"
  class addNote implements Runnable {
    @Override
    // TODO: 6/22/2016  MAKE SURE NEW NOT GETS DRAWN IN GUI VIEW
    public void run() {
      INote add = viewer.getNoteFromFields();
      sheet.addNote(add);
      viewer.updateNotes(sheet.getNotes(), sheet.getSpread(sheet.getNotes()));

    }
  }

  /**
   * Remove note from IComposition
   */
  //KEY WILL BE "remove".
  class removeNote implements Runnable {
    @Override
    public void run() {
      INote remove = viewer.getNoteFromFields();
      sheet.removeNote(remove);
      viewer.updateNotes(sheet.getNotes(), sheet.getSpread(sheet.getNotes()));
    }
  }


  /**
   * Edits an existing note in an IComposiiton
   */
  //KEY WILL BE "edit".
  class editNote implements Runnable {
    @Override
    public void run() {
      INote edit = viewer.getNoteFromList();
      INote editTo = viewer.getNoteFromFields();
      sheet.edit(edit, editTo);
      viewer.redraw();
    }
  }

  /**
   * Opens file passed into GUIVIEW
   * Key will be "open".
   */
  class openFile implements Runnable{
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
   * KEYBOARD LISTENER RUNNABLES PAST HERE!!
   */
  class scrollLeft implements Runnable{
    @Override
    public void run(){
      viewer.scrollLeft();
    }
  }

  class scrollRight implements Runnable{
    @Override
    public void run(){
      viewer.scrollRight();
    }
  }

  class scrollHome implements Runnable{
    @Override
    public void run() {
      viewer.scrollToStart();
    }
  }

  class scrollEnd implements Runnable{
    @Override
    public void run(){
      viewer.scrollToEnd();
    }
  }

  /**
   * sets the position to the current song beat
   */
  class setPosition implements Runnable {
    @Override
    public void run() {

    }
  }
  /**
   * Gets the current beat of a playing IComposition
   */
  class getBeat implements Runnable {
    @Override
    public void run() {

    }
  }

  /**
   * Starts playing current IComposition
   */
  class startPlay implements Runnable {
    @Override
    public void run() {
      play=true;
      Thread bar = (new Thread(new updateBar()));
      Thread player = new Thread(new play());
      bar.start();
      player.start();
      if(!player.isAlive()){
        play = false;
      }
    }
  }

  class updateBar implements Runnable{
    public void run(){
       while(play){
         viewer.updateBeat(viewer.getBeat());
       }
      }
    }


  class play implements Runnable{
    public void run(){
      viewer.play();
    }
  }

  /**
   * Stops playing current IComposition
   */
  class stopPlay implements Runnable {
    @Override
    public void run() {
      play=false;
    }
  }

  /**
   * Resumes playing current IComposition
   */
  class resumePlay implements Runnable {
    @Override
    public void run() {

    }
  }

}
