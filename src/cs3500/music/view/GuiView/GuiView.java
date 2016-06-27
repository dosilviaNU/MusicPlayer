package cs3500.music.view.GuiView;

import cs3500.music.model.IComposition;
import cs3500.music.model.INote;
import cs3500.music.util.EndPair;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import java.util.Collection;

/**
 * Created by David on 6/12/2016.
 */

/**
 * Displays an IMusicSheet using Java Swing components.
 */
public class GuiView extends JFrame implements IGuiView {
  private NoteDisplay centerPanel;
  private EditorMenu eastPanel;
  private RepeatMenu southPanel;
  private JScrollPane scrollPane;
  private int[] stats;

  public static final int GUI_WIDTH = 1200;
  public static final int GUI_HEIGHT= 600;
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
    stats=sheet.getSpread(sheet.getNotes());

    setSize(new Dimension(GuiView.GUI_WIDTH, GUI_HEIGHT));
    setLayout(new BorderLayout());

    setFocusable(true);

    //Set center panel, as scrollpane.
    centerPanel = new NoteDisplay(sheet);
    scrollPane = new JScrollPane(centerPanel);
    scrollPane.setLayout(new ScrollPaneLayout());
    add(scrollPane, BorderLayout.CENTER);

    //Set east panel tp editor menu.
    eastPanel = new EditorMenu();
    add(eastPanel, BorderLayout.EAST);

    //Add repeat menu.
    southPanel = new RepeatMenu();
    add(southPanel, BorderLayout.SOUTH);

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
  public void scrollLeft() {
    int current = scrollPane.getHorizontalScrollBar().getValue();

      scrollPane.getHorizontalScrollBar().setValue(current - BEAT_WIDTH);

  }

  @Override
  public void scrollRight() {
    int current = scrollPane.getHorizontalScrollBar().getValue();
    scrollPane.getHorizontalScrollBar().setValue(current + BEAT_WIDTH);
  }

  @Override
  public void scrollUp() {

  }

  @Override
  public void scrollDown() {

  }

  @Override
  public INote getNoteFromList() {
    return eastPanel.getNoteFromList();
  }

  @Override
  public void addKListener(KeyListener keyListener) {
    addKeyListener(keyListener);
  }

  @Override
  public void addAListener(ActionListener actionListener) {
    eastPanel.addActionListener(actionListener);
    southPanel.addActionListener(actionListener);
  }

  public void redraw() {
    repaint();
  }

  @Override
  public void addMListener(MouseListener mouseListener) {
    centerPanel.addMouseListener(mouseListener);
  }

  @Override
  public int[] getNoteFromClick(int x, int y) {
    return centerPanel.getNoteFromClick(x, y);
  }

  @Override
  public void fieldsFromClick(int[] noteValue) {
    eastPanel.fieldsFromClick(noteValue);
  }

  @Override
  public void updateBeat(int beat){
    centerPanel.nextBeat(beat);
    if(beat%21==0) {
      scrollPane.getHorizontalScrollBar().setValue((beat) * BEAT_WIDTH);
    }
  }

  @Override
  public void scrollToStart() {
    scrollPane.getHorizontalScrollBar().setValue(0);
  }

  @Override
  public void scrollToEnd() {
    scrollPane.getHorizontalScrollBar().setValue(stats[2]*BEAT_WIDTH);
  }

  @Override
  public void populateNoteList(Collection<INote> notes){
    eastPanel.populateNoteList(notes);
  }

  @Override
  public void updateNotes(Collection<INote> notes, int[] spread){
    centerPanel.updateNotes(notes, spread);
    scrollPane.setPreferredSize(centerPanel.getPreferredSize());
    repaint();
    requestFocus();
  }

  @Override
  public void giveFocus() {
    this.requestFocus();
  }

  @Override
  public void addRepeat(int beatNum){
    centerPanel.addRepeat(beatNum);
  }

  @Override
  public void addInvertRepeat(int beatNum){
    centerPanel.addInvertRepeat(beatNum);
  }

  @Override
  public void addEnding(EndPair end){
    centerPanel.addEnding(end);
  }

  @Override
  public void removeEnding(int end){
    centerPanel.removeEnding(end);
  }

  @Override
  public int[] getRepeats() {
    return southPanel.getRepeats();
  }
}
