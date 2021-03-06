package cs3500.music.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

import static java.awt.event.KeyEvent.VK_END;
import static java.awt.event.KeyEvent.VK_HOME;
import static java.awt.event.KeyEvent.VK_I;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_O;
import static java.awt.event.KeyEvent.VK_P;
import static java.awt.event.KeyEvent.VK_RIGHT;

import cs3500.music.model.IComposition;
import cs3500.music.model.INote;
import cs3500.music.model.MidiComposition;

import cs3500.music.util.MidiCompBuilder;
import cs3500.music.util.MusicReader;

import cs3500.music.view.CompositeView.CompositeView;
import cs3500.music.view.CompositeView.ICompositeView;
import cs3500.music.view.ErrorWindow.ErrorWindow;



/**
 * Created by Jake on 6/23/2016.
 */
public class MockController implements IController {
  public Formatter debug;
  public IComposition sheet;
  public ICompositeView viewer;
  public MockListeners listeners;
  public Map<String, Runnable> actionMap;
  public Map<Integer, Runnable> keyMap;
  private boolean play;
  private int position;


  /**
   * Class to represent an CompositeView controller.
   *
   * @param musicSheet Sheet of musive to be passed to composite view.
   */
  public MockController(IComposition musicSheet, Appendable app) {
    debug = new Formatter(app);
    this.viewer = new CompositeView(musicSheet);
    this.sheet = musicSheet;

    buildActionMap();
    buildKeyMap();

    listeners = new MockListeners(this, actionMap, keyMap);

    viewer.addAListener(listeners);
    viewer.addKListener(listeners);
    viewer.addMListener(listeners);
    viewer.display();
    int position = 0;
    debug.format("Controller Constructed\n");
  }

  /**
   * Build the action listener map of action commands -> Runnable.
   */
  private void buildActionMap() {
    actionMap = new HashMap<String, Runnable>();
    actionMap.put("add", new MockController.AddNote());
    actionMap.put("remove", new MockController.RemoveNote());
    actionMap.put("edit", new MockController.EditNote());
    actionMap.put("open", new MockController.OpenFile());
  }

  /**
   * Build the key listener map of key inputs -> Runnable.
   */
  private void buildKeyMap() {
    keyMap = new HashMap<Integer, Runnable>();
    keyMap.put(VK_LEFT, new MockController.ScrollLeft());
    keyMap.put(VK_RIGHT, new MockController.ScrollRight());
    keyMap.put(VK_P, new MockController.StartPlay());
    keyMap.put(VK_O, new MockController.StopPlay());
    keyMap.put(VK_I, new MockController.ResumePlay());
    keyMap.put(VK_HOME, new MockController.ScrollHome());
    keyMap.put(VK_END, new MockController.ScrollEnd());
  }


  public void mouseRunnable(int x, int y) {
    int[] noteValues = viewer.getNoteFromClick(x, y);
    Collection<INote> notes = sheet.getNotes(noteValues[0], noteValues[1]);
    if (notes.size() == 0) {
      viewer.fieldsFromClick(noteValues);
    }
    viewer.giveFocus();
    viewer.populateNoteList(notes);
  }

  @Override
  public Runnable addNote() {
    return new AddNote();
  }

  @Override
  public Runnable removeNote() {
    return new RemoveNote();
  }

  @Override
  public Runnable editNote() {
    return new EditNote();
  }

  @Override
  public Runnable openFile() {
    return new OpenFile();
  }

  @Override
  public Runnable scrollLeft() {
    return new ScrollLeft();
  }

  @Override
  public Runnable scrollRight() {
    return new ScrollRight();
  }

  @Override
  public Runnable scrollHome() {
    return new ScrollHome();
  }

  @Override
  public Runnable scrollEnd() {
    return new ScrollEnd();
  }

  @Override
  public Runnable startPlay() {
    return new StartPlay();
  }

  @Override
  public Runnable updateBar() {
    return new UpdateBar();
  }

  @Override
  public Runnable play() {
    return new Play();
  }

  @Override
  public Runnable stopPlay() {
    return new StopPlay();
  }

  @Override
  public Runnable resumePlay() {
    return new ResumePlay();
  }



  /**
   * Runnable function object for adding a note to the composite view.
   */
  class AddNote implements Runnable {
    @Override
    public void run() {
      debug.format("Attempting to add note\n");
      try { INote add = viewer.getNoteFromFields();
        sheet.addNote(add);
        viewer.updateNotes(sheet.getNotes(), sheet.getSpread(sheet.getNotes())); }
      catch (Exception e) {
        //new ErrorWindow(e.getMessage(), "Invalid Note");
      }

    }
  }

  /**
   * Runnable function object for removing a note from the composite view.
   */
  class RemoveNote implements Runnable {
    @Override
    public void run() {
      debug.format("Attempting to remove note\n");
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
      debug.format("Attempting to edit note\n");
      try {
        INote edit = viewer.getNoteFromList();
        INote editTo = viewer.getNoteFromFields();
        sheet.edit(edit, editTo);
        viewer.updateNotes(sheet.getNotes(), sheet.getSpread(sheet.getNotes()));
      } catch (Exception e) {
        //new ErrorWindow(e.getMessage(), "Edit Failed");
      }
    }
  }

  /**
   * Runnable function object for opening a file in the composite view.
   */
  class OpenFile implements Runnable {
    @Override
    public void run() {

      debug.format("Attempting to open file\n");
      File musicFile = new File(viewer.getFileFromField());
      //Parse and build given music text file.
      FileReader music = null;
      try {
        music = new FileReader(musicFile);
      } catch (FileNotFoundException err) {
        //new ErrorWindow(err.getMessage(), "Cannot find file!");
        viewer.giveFocus();
      }
      MusicReader mr = new MusicReader();
      MidiCompBuilder mcb = new MidiCompBuilder();
      MidiComposition comp = null;
      try {
        comp = mr.parseFile(music, mcb);
      } catch (IllegalArgumentException err) {
        //new ErrorWindow(err.getMessage(), "Cannot parse file!");
        viewer.giveFocus();
      }
      sheet = comp;
      viewer.updateMidiComp(sheet);
      viewer.updateNotes(sheet.getNotes(), sheet.getSpread(sheet.getNotes()));
      debug.format("Opened File");
    }
  }

  /**
   * Runnable function object for scrolling the composite view to the left.
   */
  class ScrollLeft implements Runnable {
    @Override
    public void run() {
      debug.format("scroll left");
      viewer.scrollLeft();
    }
  }

  /**
   * Runnable function object for scrolling the composite view to the right.
   */
  class ScrollRight implements Runnable {
    @Override
    public void run() {
      debug.format("scroll right");
      viewer.scrollRight();
    }
  }

  /**
   * Runnable function object for scrolling to home.
   */
  class ScrollHome implements Runnable {
    @Override
    public void run() {
      debug.format("Goto home");
      viewer.scrollToStart();
    }
  }

  /**
   * Runnable function object for scrolling to the end.
   */
  class ScrollEnd implements Runnable {
    @Override
    public void run() {
      debug.format("Goto end");
      viewer.scrollToEnd();
    }
  }

  /**
   * Starts playing current IComposition
   */
  class StartPlay implements Runnable {
    @Override
    public void run() {
      debug.format("Starting Threads\n");
      play = true;
      Thread bar = (new Thread(new MockController.UpdateBar()));
      Thread player = new Thread(new MockController.Play());
      bar.start();
      player.start();
    }
  }

  /**
   * Updates the position of the beat bar in the composite view. Synchronized with Play.
   */
  class UpdateBar implements Runnable {


    public void run() {
      debug.format("Updating Bar\n");
      while (play) {
        viewer.updateBeat(viewer.getBeat());
        position = viewer.getBeat();
      }

    }
  }


  /**
   * Plays the music sheet currently loaded in the composite view.
   */
  class Play implements Runnable {
    public void run() {
      debug.format("Playing\n");
      viewer.play();
    }
  }

  /**
   * Stops playing current IComposition
   */
  class StopPlay implements Runnable {
    @Override
    public void run() {
      debug.format("Stopping\n");
      play = false;
      viewer.stop();
    }
  }

  /**
   * Resumes playing current IComposition
   */
  class ResumePlay implements Runnable {
    @Override
    public void run() {
      debug.format("In resume, position = " + position + "\n");
      play = true;
      viewer.resume(position);
      new Thread(new MockController.UpdateBar()).start();
    }
  }


  @Override
  public Runnable addRepeat() {
    return null;
  }

  @Override
  public Runnable removeEnd() {
    return null;
  }

}
