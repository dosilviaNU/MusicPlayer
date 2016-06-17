package cs3500.music;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.music.model.MidiComposition;
import cs3500.music.util.MidiCompBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.console.ConsoleView;
import cs3500.music.view.GuiView.GuiView;
import cs3500.music.view.midi.MidiView;

/**
 * Created by David on 6/16/2016.
 */
public class MusicEditor {
  public static void main(String[] args){
    if(args.length<2){
      System.exit(0);
    }
    File musicFile = new File(args[0]);
    FileReader music = null;
    try {
      music = new FileReader(musicFile);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    MusicReader mr = new MusicReader();
    MidiCompBuilder mcb = new MidiCompBuilder();
    MidiComposition comp = null;
    try {
      comp = mr.parseFile(music, mcb);
    }catch (IllegalArgumentException e){
      e.printStackTrace();
    }
    switch (args[1]){
      case "console": ConsoleView console = new ConsoleView(comp);
              console.display();
        break;
      case "midi": MidiView midi = new MidiView(comp);
              midi.playComp();
        break;
      case "gui": GuiView gui = new GuiView(comp);
        gui.display();
        break;
      default: throw new IllegalArgumentException("Invalid Input.");
    }
  }
}
