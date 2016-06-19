import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;

import cs3500.music.model.IComposition;
import cs3500.music.model.MidiComposition;
import cs3500.music.model.ROMidiComposition;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MidiCompBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.IMusicView;
import cs3500.music.view.console.ConsoleMock;
import cs3500.music.view.midi.MidiMock;

import static org.junit.Assert.*;

/**
 * Tests console view using a mock Created by Jake on 6/19/2016.
 */
public class ConsoleViewTest {
  IMusicView mock;
  StringBuilder builder;

  @Test
  //test mary-little-lamb.txt
  //using the same line tests as in the music sheet to ensure they are the same
  public void testMary() throws Exception {
    String testHeader = "    E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   " +
            "F4  F#4   G4 ";
    String testLine0 = " 0                 X                                            X     " +
            "            ";
    String testLine20 = "20                 |                                  X              " +
            "             ";
    String testLine41 = "41                 |                                            |    " +
            "             ";
    String testLine63 = "63  |                                       |                        " +
            "             ";
    builder = new StringBuilder();
    CompositionBuilder<MidiComposition> mcb = new MidiCompBuilder();
    File file1 = new File("mary-little-lamb.txt");
    FileReader music = new FileReader(file1);
    Assert.assertTrue(music instanceof Readable);
    MusicReader mr = new MusicReader();
    IComposition comp2 = mr.parseFile(music, mcb);
    comp2 = new ROMidiComposition((MidiComposition) comp2);
    mock = new ConsoleMock(comp2, builder);
    mock.display();
    //assertEquals(builder.toString(), "");
    assertTrue(builder.toString().contains(testHeader));
    assertTrue(builder.toString().contains(testLine0));
    assertTrue(builder.toString().contains(testLine20));
    assertTrue(builder.toString().contains(testLine41));
    assertTrue(builder.toString().contains(testLine63));
  }
}