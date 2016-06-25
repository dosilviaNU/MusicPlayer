package cs3500.music.controller;

        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileReader;
        import java.util.Collection;
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
  int position;


  /**
   * Class to represent an CompositeView controller.
   *
   * @param musicSheet Sheet of musive to be passed to composite view.
   */
  public MidiController(IComposition musicSheet) {
    this.viewer = new CompositeView(musicSheet);
    this.sheet = musicSheet;

    buildActionMap();
    buildKeyMap();

    actionListener = new SwingActionListener(this, actionMap);
    keyListener = new SwingKeyboardListener(this, keyMap);
    mouseListener = new SwingMouseListener(this);

    viewer.addAListener(actionListener);
    viewer.addKListener(keyListener);
    viewer.addMListener(mouseListener);
    viewer.display();
    int position = 0;
  }

  /**
   * Build the action listener map of action commands -> Runnable.
   */
  private void buildActionMap() {
    actionMap = new HashMap<String, Runnable>();
    actionMap.put("add", new AddNote());
    actionMap.put("remove", new RemoveNote());
    actionMap.put("edit", new EditNote());
    actionMap.put("open", new OpenFile());
  }

  /**
   * Build the key listener map of key inputs -> Runnable.
   */
  private void buildKeyMap() {
    keyMap = new HashMap<Integer, Runnable>();
    keyMap.put(VK_LEFT, new ScrollLeft());
    keyMap.put(VK_RIGHT, new ScrollRight());
    keyMap.put(VK_P, new StartPlay());
    keyMap.put(VK_O, new StopPlay());
    keyMap.put(VK_I, new ResumePlay());
    keyMap.put(VK_HOME, new ScrollHome());
    keyMap.put(VK_END, new ScrollEnd());
  }


  public void mouseRunnable(int x, int y) {
    int[] noteValues = viewer.getNoteFromClick(x, y);
    Collection<INote> notes = sheet.getNotes(noteValues[0], noteValues[1]);
    if (notes.size() == 0) {
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
  class OpenFile implements Runnable {
    @Override
    public void run() {
      File musicFile = new File(viewer.getFileFromField());
      //Parse and build given music text file.
      FileReader music = null;
      try {
        music = new FileReader(musicFile);
      } catch (FileNotFoundException err) {
        new ErrorWindow(err.getMessage(), "Cannot find file!");
      }
      MusicReader mr = new MusicReader();
      MidiCompBuilder mcb = new MidiCompBuilder();
      MidiComposition comp = null;
      try {
        comp = mr.parseFile(music, mcb);
      } catch (IllegalArgumentException err) {
        new ErrorWindow(err.getMessage(), "Cannot parse file!");
      }
      sheet = comp;
      viewer.updateMidiComp(sheet);
      viewer.updateNotes(sheet.getNotes(), sheet.getSpread(sheet.getNotes()));
    }
  }

  /**
   * Runnable function object for scrolling the composite view to the left.
   */
  class ScrollLeft implements Runnable {
    @Override
    public void run() {
      viewer.scrollLeft();
    }
  }

  /**
   * Runnable function object for scrolling the composite view to the right.
   */
  class ScrollRight implements Runnable {
    @Override
    public void run() {
      viewer.scrollRight();
    }
  }

  /**
   * Runnable function object for scrolling to home.
   */
  class ScrollHome implements Runnable {
    @Override
    public void run() {
      viewer.scrollToStart();
    }
  }

  /**
   * Runnable function object for scrolling to the end.
   */
  class ScrollEnd implements Runnable {
    @Override
    public void run() {
      viewer.scrollToEnd();
    }
  }

  /**
   * Starts playing current IComposition
   */
  class StartPlay implements Runnable {
    @Override
    public void run() {
      play = true;
      Thread bar = (new Thread(new UpdateBar()));
      Thread player = new Thread(new Play());
      bar.start();
      player.start();
    }
  }

  /**
   * Updates the position of the beat bar in the composite view. Synchronized with Play.
   */
  class UpdateBar implements Runnable {


    public void run() {
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
      System.out.println("In resume, position = " + position);
      play = true;
      viewer.resume(position);
      new Thread(new UpdateBar()).start();
    }
  }

}
