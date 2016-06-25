package cs3500.music;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.music.controller.IController;
import cs3500.music.controller.MidiController;
import cs3500.music.controller.SwingActionListener;
import cs3500.music.model.MidiComposition;
import cs3500.music.util.MidiCompBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.IMusicView;
import cs3500.music.view.console.ConsoleView;
import cs3500.music.view.GuiView.GuiView;
import cs3500.music.view.midi.MidiView;

/**
 * Created by David on 6/16/2016.
 */

/**
 * Holds main method for launching the Music Editor. Main method takes a midi text file, and a
 * display method as arguments valid inputs are "gui", "midi" and "console" case insensitive.
 */
public class MusicEditor {
  public static void main(String[] args) throws InterruptedException {
    if (args.length < 2) {
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
    String mode = args[1].toLowerCase();

    //Validate and run given mode.
    switch (mode) {
      case "console":
        ConsoleView console = new ConsoleView(comp);
        console.display();
        break;
      case "midi":
        MidiView midi = new MidiView(comp);
        midi.run();
        Thread.sleep(400000);
        break;
      case "gui":
      case "controller":
        GuiView gui = new GuiView(comp);
        //gui.display();
        MidiView midiView = new MidiView(comp);
        MidiController midiController = new MidiController(comp);
        break;
      default:
        throw new IllegalArgumentException("Invalid Input.");
    }
  }
}
