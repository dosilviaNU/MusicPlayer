import org.junit.Test;

import java.io.File;
import java.io.FileReader;

import cs3500.music.model.MidiComposition;
import cs3500.music.util.MidiCompBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.midi.MidiView;

import static org.junit.Assert.*;

/**
 * Created by Jake on 6/15/2016.
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
    MidiCompBuilder mcb = new MidiCompBuilder();
    File file1 = new File("zoot-zl.txt");
    File file2 = new File("mystery-2.txt");
    File file3 = new File("mystery-3.txt");
    File file4 = new File("df-ttfaf.txt");
    FileReader music = new FileReader(file1);
    assertTrue(music instanceof Readable);

    MusicReader mr = new MusicReader();

    MidiComposition comp = mr.parseFile(music, mcb);
    //MidiComposition comp2 = mr.parseFile(musicTfaf, mcb);


    MidiView player = new MidiView(comp);
    player.playComp();
    for (int i = 0; i < 1000; i++) {
      System.out.println(player.getBeat());
      try {
        Thread.sleep(1500); }
      catch(Exception e) {}
    }

  }
}