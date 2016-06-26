import org.junit.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;

import cs3500.music.controller.MidiController;
import cs3500.music.controller.MockController;
import cs3500.music.model.IComposition;
import cs3500.music.model.MidiComposition;
import cs3500.music.model.ROMidiComposition;
import cs3500.music.util.MidiCompBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.GuiView.GuiView;
import cs3500.music.view.midi.MidiView;

import static java.awt.event.KeyEvent.VK_END;
import static java.awt.event.KeyEvent.VK_HOME;
import static java.awt.event.KeyEvent.VK_I;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_O;
import static java.awt.event.KeyEvent.VK_P;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static org.junit.Assert.*;

/**
 * Created by Jake on 6/25/2016.
 */
public class MidiControllerTest {
  GuiView gui;
  MidiView midiView;
  MockController mock;
  StringBuilder output;


  public void initData() {
    try {
      output = new StringBuilder();
      //Builds a controller for Mary Had a little lamb
      MusicReader mr = new MusicReader();
      MidiCompBuilder mcb1 = new MidiCompBuilder();
      File file1 = new File("mary-little-lamb.txt");
      FileReader music = new FileReader(file1);
      StringBuilder sbOut1 = new StringBuilder();
      IComposition comp = mr.parseFile(music, mcb1);
      comp = new ROMidiComposition((MidiComposition) comp);
      assertTrue(comp.size() > 0);
      GuiView gui = new GuiView(comp);
      //gui.display();
      MidiView midiView = new MidiView(comp);
      mock = new MockController(comp, output);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  //tests creation of controller
  public void testCreation() {
    initData();
  }

  @Test
  //tests creation of file
  public void sheetCreation() {
    initData();
    assertEquals(mock.sheet.size(), 65);
    assertEquals(output.toString(), "Controller Constructed\n");
  }

  @Test
  //tests start stop and play commands
  public void keyBoardTests() throws InterruptedException {
    initData();
    assertEquals(mock.sheet.size(), 65);
    assertEquals(output.toString(), "Controller Constructed\n");
    mock.listeners.keyPressed(new KeyEvent(new Choice(), VK_P, VK_P, VK_P, VK_P, 'p'));
    assertEquals(output.toString(), "Controller Constructed\nStarting Threads\n");
    Thread.sleep(1500);
    assertEquals(output.toString().contains("Updating Bar"), true);
    assertEquals(output.toString().contains("Playing"), true);
    mock.listeners.keyPressed(new KeyEvent(new Choice(), VK_O, VK_O, VK_O, VK_O, 'o'));
    Thread.sleep(1000);
    assertEquals(output.toString().contains("Stopping"), true);
    mock.listeners.keyPressed(new KeyEvent(new Choice(), VK_I, VK_I, VK_I, VK_I, 'i'));
    assertEquals(output.toString().contains("In resume, position = "), true);
  }

  @Test
  //tests scrolling keyboard commands
  public void keyBoardTests2() throws InterruptedException {
    initData();
    assertEquals(mock.sheet.size(), 65);
    assertEquals(output.toString(), "Controller Constructed\n");
    mock.listeners.keyPressed(new KeyEvent(new Choice(), VK_LEFT, VK_LEFT, VK_LEFT, VK_LEFT, 'p'));
    Thread.sleep(200);
    assertEquals(output.toString().contains("scroll left"), true);
    mock.listeners.keyPressed(new KeyEvent(new Choice(), VK_RIGHT, VK_RIGHT, VK_RIGHT, VK_RIGHT,
            'p'));
    Thread.sleep(200);
    assertEquals(output.toString().contains("scroll right"), true);
    mock.listeners.keyPressed(new KeyEvent(new Choice(), VK_END, VK_END, VK_END, VK_END, 'p'));
    Thread.sleep(200);
    assertEquals(output.toString().contains("Goto end"), true);
    mock.listeners.keyPressed(new KeyEvent(new Choice(), VK_HOME, VK_HOME, VK_HOME, VK_HOME,
            'p'));
    Thread.sleep(200);
    assertEquals(output.toString().contains("Goto home"), true);
  }

  @Test
  //tests creation of file
  public void actionListenerTests() throws InterruptedException {
    initData();
    assertEquals(mock.sheet.size(), 65);
    assertEquals(output.toString(), "Controller Constructed\n");
    mock.listeners.actionPerformed(new ActionEvent(new Object(), 1, "add"));
    assertEquals(output.toString().contains("Attempting to add note"), true);
    try {
      mock.listeners.actionPerformed(new ActionEvent(new Object(), 1, "remove"));
    } catch (Exception e) {}
    assertEquals(output.toString().contains("Attempting to remove note"), true);
    try {
    mock.listeners.actionPerformed(new ActionEvent(new Object(), 1, "edit"));
    } catch (Exception e) {}
    assertEquals(output.toString().contains("Attempting to edit note"), true);
    try {
    mock.listeners.actionPerformed(new ActionEvent(new Object(), 1, "open"));
    } catch (Exception e) {}
    assertEquals(output.toString().contains("Attempting to open file"), true);
  }
}