package cs3500.music.view.GuiView;

import cs3500.music.model.IComposition;
import cs3500.music.model.INote;
import cs3500.music.view.IMusicView;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.File;

/**
 * Created by David on 6/12/2016.
 */

/**
 * Displays an IMusicSheet using Java Swing components.
 */
public class GuiView extends JFrame implements IGuiView {
  private static NoteDisplay centerPanel;
  private static EditorMenu eastPanel;
  private static JScrollPane scrollPane;
  private static int[] stats;

  public static final int GUI_WIDTH = 1200;
  public static final int GUI_HEIGHT = 500;
  public static final int BEAT_WIDTH = 40;
  public static final int BEAT_HEIGHT = 20;


  /**
   * Default constructor for a GuiView Takes in an IMusicSheet to represent.
   *
   * @param sheet IMusicSheet this GuiView will display.
   */
  public GuiView(IComposition sheet) {
    super("Music Editor OOD Summer 1");
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setResizable(false);
    setSize(new Dimension(GuiView.GUI_WIDTH, GUI_HEIGHT));
    setLayout(new BorderLayout());
    stats=sheet.getSpread(sheet.getNotes());

    //Set center panel, as scrollpane.
    centerPanel = new NoteDisplay(sheet);
    scrollPane = new JScrollPane(centerPanel);
    scrollPane.setLayout(new ScrollPaneLayout());
    add(scrollPane, BorderLayout.CENTER);

    //Set east panel tp editor menu.
    eastPanel = new EditorMenu();
    add(eastPanel, BorderLayout.EAST);

  }

  @Override
  public void display() {
    this.setVisible(true);
  }

  @Override
  public INote getNoteFromFields() {
    return eastPanel.getNoteFromFields();
  }

  public String getFileFromField() {
    return eastPanel.getFileFromField();
  }

  @Override
  public INote getNoteFromList() {
    return eastPanel.getNoteFromList();
  }

  @Override
  public void addKListener(KeyListener keyListener) {
    centerPanel.addKeyListener(keyListener);
  }

  @Override
  public void addAListener(ActionListener actionListener) {
    eastPanel.addActionListener(actionListener);
  }

  public void redraw() {
    repaint();
  }

  @Override
  public void addMListener(MouseListener mouseListener) {
    centerPanel.addMouseListener(mouseListener);
  }

  public void remove() {
    setVisible(false);
  }

  public void updateBeat(int beat){
    centerPanel.nextBeat(beat);
    scrollPane.getHorizontalScrollBar().setValue(beat * BEAT_WIDTH);
  }

  @Override
  public void scrollToStart() {
    scrollPane.getHorizontalScrollBar().setValue(0*BEAT_WIDTH);
  }

  @Override
  public void scrollToEnd() {
    scrollPane.getHorizontalScrollBar().setValue(stats[2]*BEAT_WIDTH);
  }


}
