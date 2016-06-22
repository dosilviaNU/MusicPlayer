import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import cs3500.music.model.IComposition;
import cs3500.music.model.MidiComposition;
import cs3500.music.model.MidiNote;
import cs3500.music.model.ROMidiComposition;
import cs3500.music.util.MidiCompBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.IMusicView;
import cs3500.music.view.console.ConsoleView;
import cs3500.music.view.midi.MidiMock;
import cs3500.music.view.midi.MidiView;

import static org.junit.Assert.*;

/**
 * File for creating the other required test files for the assignment Created by Jake on 6/15/2016.
 */
public class MusicReaderTest {
  /*to use the parser
  File file1 = new File("df-ttfaf.txt");
  FileReader music = new FileReader(file1);
  MusicReader mr = new MusicReader();
  MidiComposition comp = mr.parseFile(music, mcb);
  */
  @Test
  public void parseFile() throws Exception {
    MusicReader mr = new MusicReader();

    MidiCompBuilder mcb1 = new MidiCompBuilder();
    File file1 = new File("mary-little-lamb.txt");
    FileReader music = new FileReader(file1);
    StringBuilder sbOut1 = new StringBuilder();
    IComposition comp = mr.parseFile(music, mcb1);
    comp = new ROMidiComposition((MidiComposition) comp);
    assertTrue(comp.size() > 0);
    IMusicView<IComposition> mock = new MidiMock(comp, sbOut1);
    mock.display();
    PrintWriter out1 = new PrintWriter("midi-transcript.txt");
    out1.print(sbOut1.toString());
    out1.close();
    IMusicView<IComposition> console2 = new ConsoleView(comp);
    //console2.display();


    File file2 = new File("mystery-1.txt");
    FileReader music2 = new FileReader(file2);
    StringBuilder sbOut2 = new StringBuilder();
    MidiCompBuilder mcb2 = new MidiCompBuilder();
    IComposition comp2 = mr.parseFile(music2, mcb2);
    assertTrue(comp.size() > 0);
    IMusicView<IComposition> console = new ConsoleView(comp2);
    console.display();


    //MidiComposition comp2 = mr.parseFile(musicTfaf, mcb);

    /* Plays a song
    MidiView player = new MidiView(comp);
    player.playComp();
    for (int i = 0; i < 1000; i++) {
      System.out.println(player.getBeat());
      try {
        Thread.sleep(1500);
      } catch (Exception e) {
      }
    }*/

  }
}