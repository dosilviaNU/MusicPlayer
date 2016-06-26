package cs3500.music;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import cs3500.music.controller.IController;
import cs3500.music.controller.MidiController;
import cs3500.music.controller.SwingActionListener;
import cs3500.music.controller.SwingKeyboardListener;
import cs3500.music.controller.SwingMouseListener;
import cs3500.music.model.MidiComposition;
import cs3500.music.util.MidiCompBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.CompositeView.CompositeView;
import cs3500.music.view.CompositeView.ICompositeView;
import cs3500.music.view.IMusicView;
import cs3500.music.view.console.ConsoleView;
import cs3500.music.view.GuiView.GuiView;
import cs3500.music.view.midi.MidiView;

import static java.awt.event.KeyEvent.VK_END;
import static java.awt.event.KeyEvent.VK_HOME;
import static java.awt.event.KeyEvent.VK_I;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_O;
import static java.awt.event.KeyEvent.VK_P;
import static java.awt.event.KeyEvent.VK_RIGHT;

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
        ICompositeView viewer = new CompositeView(comp);
        MidiController midiController = new MidiController(comp, viewer);
        loadConfig(midiController, viewer);
        break;
      default:
        throw new IllegalArgumentException("Invalid Input.");
    }
  }

  private static void loadConfig(MidiController midiController, ICompositeView viewer) {
    SwingActionListener actionListener;
    SwingKeyboardListener keyListener;
    SwingMouseListener mouseListener;
    Map<String, Runnable> actionMap;
    Map<Integer, Runnable> keyMap;


    actionMap = new HashMap<String, Runnable>();
    actionMap.put("add", midiController.addNote());
    actionMap.put("remove", midiController.removeNote());
    actionMap.put("edit", midiController.editNote());
    actionMap.put("open", midiController.openFile());


    keyMap = new HashMap<Integer, Runnable>();
    keyMap.put(VK_LEFT, midiController.scrollLeft());
    keyMap.put(VK_RIGHT, midiController.scrollRight());
    keyMap.put(VK_P, midiController.startPlay());
    keyMap.put(VK_O, midiController.stopPlay());
    keyMap.put(VK_I, midiController.resumePlay());
    keyMap.put(VK_HOME, midiController.scrollHome());
    keyMap.put(VK_END, midiController.scrollEnd());



    actionListener = new SwingActionListener(midiController, actionMap);
    keyListener = new SwingKeyboardListener(midiController, keyMap);
    mouseListener = new SwingMouseListener(midiController);

    viewer.addAListener(actionListener);
    viewer.addKListener(keyListener);
    viewer.addMListener(mouseListener);
  }
}
