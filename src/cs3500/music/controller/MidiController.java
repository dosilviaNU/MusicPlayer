package cs3500.music.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import cs3500.music.model.IComposition;
import cs3500.music.model.INote;
import cs3500.music.model.MidiComposition;
import cs3500.music.util.MidiCompBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.GuiView.GuiView;
import cs3500.music.view.GuiView.IGuiView;
import cs3500.music.view.midi.IMidiView;
import cs3500.music.view.midi.MidiView;

/**
 * Concrete Class implementing the IController Interface Created by Jake on 6/21/2016.
 */
public class MidiController implements ActionListener {
  IComposition sheet;
  IMidiView player;
  IGuiView viewer;
  int beat = 0;

  public MidiController(IComposition musicSheet) {
    this.player = new MidiView(musicSheet);
    this.viewer = new GuiView(musicSheet);
    viewer.addAListener(this);
    this.sheet = musicSheet;
    viewer.display();
  }

  @Override
  //NEED TO ADD INT MIDIVIEW COMPLIMENTS.
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "add":
        INote add = viewer.getNoteFromFields();
        sheet.addNote(add);
        viewer.redraw();
        break;
      case "remove":
        INote remove = viewer.getNoteFromFields();
        sheet.removeNote(remove);
        viewer.redraw();
        break;
      case "edit":
        INote edit = viewer.getNoteFromList();
        INote editTo = viewer.getNoteFromFields();
        sheet.edit(edit, editTo);
        viewer.redraw();
        break;
      case "update":
        viewer.scrollToStart();
        beat+=1;
        viewer.redraw();
        break;
      case "open":
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
        this.sheet = comp;
        player = new MidiView(comp);
        viewer.remove();
        viewer = new GuiView(comp);
        viewer.addAListener(this);
        viewer.display();
      default:
        return;
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
   * Add a note to a IComposition
   */
  class addNote implements Runnable {
    @Override
    public void run() {

    }
  }

  /**
   * Edits an existing note in an IComposiiton
   */
  class editNote implements Runnable {
    @Override
    public void run() {

    }
  }

  /**
   * Remove note from IComposition
   */
  class removeNote implements Runnable {
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

    }
  }

  /**
   * Stops playing current IComposition
   */
  class stopPlay implements Runnable {
    @Override
    public void run() {

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
