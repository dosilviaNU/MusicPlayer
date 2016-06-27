package cs3500.music.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.awt.event.KeyEvent.VK_END;
import static java.awt.event.KeyEvent.VK_HOME;
import static java.awt.event.KeyEvent.VK_I;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_O;
import static java.awt.event.KeyEvent.VK_P;
import static java.awt.event.KeyEvent.VK_RIGHT;

import cs3500.music.model.Extra.AltEndRepeat;
import cs3500.music.model.Extra.BasicRepeat;
import cs3500.music.model.Extra.ICompRepeat;
import cs3500.music.model.Extra.MidiCompRepeat;
import cs3500.music.model.IComposition;
import cs3500.music.model.INote;
import cs3500.music.model.MidiComposition;

import cs3500.music.util.EndPair;
import cs3500.music.util.MidiCompBuilder;
import cs3500.music.util.MusicReader;

import cs3500.music.view.CompositeView.CompositeView;
import cs3500.music.view.CompositeView.ICompositeView;
import cs3500.music.view.ErrorWindow.ErrorWindow;
import javafx.scene.paint.Stop;


/**
 * Concrete Class implementing the IController Interface Created by Jake on 6/21/2016.
 */
public class MidiController implements IController {
  private ICompRepeat sheet;
  private ICompositeView viewer;
  private boolean play;
  private int position;
  private List<List<Integer>> jumps;


  /**
   * Class to represent an CompositeView controller.
   *
   * @param musicSheet Sheet of music to be passed to composite view.
   */
  public MidiController(IComposition musicSheet, ICompositeView viewer) {
    this.viewer = viewer;
    viewer.updateMidiComp(musicSheet);
    this.sheet = new MidiCompRepeat(musicSheet);
    viewer.display();
    jumps = sheet.getJumps();
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

  @Override
  public Runnable addRepeat() {
    return new AddRepeat();
  }

  @Override
  public Runnable removeEnd() {
    return new RemoveEnd();
  }


  /**
   * Runnable function object for adding a note to the composite view.
   */
  class AddNote implements Runnable {
    @Override
    public void run() {
      try {
        INote add = viewer.getNoteFromFields();
        sheet.addNote(add);
        viewer.updateNotes(sheet.getNotes(), sheet.getSpread(sheet.getNotes()));
      } catch (Exception e) {
        new ErrorWindow(e.getMessage(), "Invalid Note");
      }

    }
  }

  /**
   * Runnable function object for removing a note from the composite view.
   */
  class RemoveNote implements Runnable {
    @Override
    public void run() {
      try {
        INote remove = viewer.getNoteFromFields();
        if (sheet.removeNote(remove)) {
          viewer.updateNotes(sheet.getNotes(), sheet.getSpread(sheet.getNotes()));
        } else {
          new ErrorWindow("Remove Failed: Note not found.", "Remove failed!");
        }
      } catch (Exception e) {
        new ErrorWindow("Invalid Field", "Remove failed!");
      }
    }
  }

  /**
   * Runnable function object for editting a note in the composite view.
   */
  class EditNote implements Runnable {
    @Override
    public void run() {
      try {
        INote edit = viewer.getNoteFromList();
        INote editTo = viewer.getNoteFromFields();
        sheet.edit(edit, editTo);
        viewer.updateNotes(sheet.getNotes(), sheet.getSpread(sheet.getNotes()));
      } catch (Exception e) {
        new ErrorWindow(e.getMessage(), "Edit Failed");
      }
    }
  }

  /**
   * Runnable function object for opening a file in the composite view.
   */
  class OpenFile implements Runnable {
    @Override
    public void run() {
      StopPlay stop = new StopPlay();
      stop.run();
      File musicFile = new File(viewer.getFileFromField());
      //Parse and build given music text file.
      FileReader music = null;
      try {
        music = new FileReader(musicFile);
      } catch (FileNotFoundException err) {
        new ErrorWindow(err.getMessage(), "Cannot find file!");
        viewer.giveFocus();
      }
      MusicReader mr = new MusicReader();
      MidiCompBuilder mcb = new MidiCompBuilder();
      MidiComposition comp = null;
      try {
        comp = mr.parseFile(music, mcb);
      } catch (IllegalArgumentException err) {
        new ErrorWindow(err.getMessage(), "Cannot parse file!");
        viewer.giveFocus();
      }
      sheet = new MidiCompRepeat(comp);
      jumps = sheet.getJumps();
      viewer.updateMidiComp(sheet);
      viewer.wipeRepeats();
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
        position = viewer.getBeat();
        if (jumps.size() != 0) {
          if (jumps.get(0).get(0) <= position) {
            viewer.resume(jumps.get(0).get(1));
            if (jumps.size() > 0) {
              jumps.remove(0);
            }
          }
          viewer.updateBeat(position);
        }
        position = viewer.getBeat();
        viewer.updateBeat(position);
      }
    }
  }


  /**
   * Plays the music sheet currently loaded in the composite view.
   */
  class Play implements Runnable {
    public void run() {
      viewer.play();
      jumps = sheet.getJumps();
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

  class AddRepeat implements Runnable {
    @Override
    public void run() {
      int[] tempRepeats = viewer.getRepeats();
      System.out.println("Repeats length: " + tempRepeats.length);
      if (tempRepeats.length == 2) {
        sheet.addRepeat(new BasicRepeat(tempRepeats[0], tempRepeats[1]));
        viewer.updateMidiComp(sheet);
        viewer.addRepeat(tempRepeats[1]);
        viewer.addInvertRepeat(tempRepeats[0]);
      } else {
        ArrayList<Integer> tempEndings = new ArrayList<Integer>();
        for (int i = 1; i < tempRepeats.length; i++) {
          tempEndings.add(tempRepeats[i]);
          if (i < tempRepeats.length - 1) {
            viewer.addEnding(new EndPair(tempRepeats[i], tempRepeats[i + 1]));
          }
        }
        sheet.addRepeat(new AltEndRepeat(tempRepeats[0], tempEndings));
        viewer.updateMidiComp(sheet);
      }
      viewer.giveFocus();
    }
  }

  class RemoveEnd implements Runnable {
    @Override
    public void run() {
      int[] tempRepeats = viewer.getRepeats();
      if (tempRepeats.length == 2) {
        sheet.removeRepeat(new BasicRepeat(tempRepeats[0], tempRepeats[1]));
        viewer.updateMidiComp(sheet);
        viewer.removeRepeat(tempRepeats[1]);
        viewer.removeInvertRepeat(tempRepeats[0]);
      } else {
        ArrayList<Integer> tempEndings = new ArrayList<Integer>();
        for (int i = 1; i < tempRepeats.length; i++) {
          tempEndings.add(tempRepeats[i]);
          if (i < tempRepeats.length - 1) {
            viewer.removeEnding(new EndPair(tempRepeats[i], tempRepeats[i + 1]));
          }
        }
        System.out.println("Removal: " + sheet.removeRepeat(new AltEndRepeat(tempRepeats[0],
                tempEndings)));
        viewer.updateMidiComp(sheet);
      }
      viewer.giveFocus();
    }
  }


}
