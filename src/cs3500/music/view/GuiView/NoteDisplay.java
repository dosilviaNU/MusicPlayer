package cs3500.music.view.GuiView;

import cs3500.music.model.IMusicSheet;
import cs3500.music.model.INote;
import cs3500.music.util.EndPair;
import javafx.animation.Animation;

import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

/**
 * Created by David on 6/14/2016.
 */

/**
 * Main window of the GuiView, paints all the notes,beat counts, and grid lines.
 */
public class NoteDisplay extends JComponent {
  private IMusicSheet sheet;
  private int[] spread;
  private int noteMod;
  private int noteCount;
  private final int topBorder = GuiView.BEAT_HEIGHT;
  private final int leftBorder = GuiView.BEAT_WIDTH;
  private int windowHeight;
  private int windowWidth;
  private Collection<INote> notes;
  private int curBeat;

  private List<Integer> repeats;
  private List<Integer> invertRepeats;
  private List<EndPair> endings;

  /**
   * Default constructor for a NoteDisplay panel.
   */
  public NoteDisplay(IMusicSheet givenSheet) {
    super();
    setAutoscrolls(true);
    //Initialize instance variables.
    this.sheet = givenSheet;
    this.notes = sheet.getNotes();
    this.spread = sheet.getSpread(notes);
    this.noteMod = spread[1] - spread[0] - 1;
    this.noteCount = spread[1] - spread[0] + 1;
    this.windowHeight = (noteMod + 3) * topBorder;
    this.windowWidth = (spread[2] + 1) * leftBorder;
    this.curBeat = 0;
    this.repeats = new ArrayList<Integer>();
    this.invertRepeats = new ArrayList<Integer>();
    this.endings = new ArrayList<EndPair>();
    setPreferredSize(new Dimension(windowWidth, windowHeight));
    setVisible(true);
    setFocusable(true);
    repaint();
  }

  /**
   * Draws the notes, grid lines, and beats of the music sheet.
   *
   * @param g Graphic object.
   */
  @Override
  public void paintComponent(Graphics g) {
    int beatLine = (curBeat * leftBorder) + leftBorder;
    Graphics2D g2 = (Graphics2D) g;


    //Draw notes.
    for (INote note : this.notes) {
      int x = (note.getStart() + 1) * leftBorder;
      int y = calcY(note.getValue());
      int width = (note.getDuration() - 1) * leftBorder;
      g2.setColor(Color.green);
      g2.fillRect(x, y, width, topBorder);
      g2.setColor(Color.black);
      g2.fillRect(x, y, leftBorder, topBorder);
    }

    //Draw beat counts.
    for (int i = 0; i <= spread[2]; i += 16) {
      g2.drawString("" + i, (i + 1) * leftBorder, topBorder - 2);
    }

    //Draw endings.
    for(int i = 0;i < endings.size();i++){
      g2.setColor(Color.BLUE);
      g2.setStroke(new BasicStroke(2));
      int first = (endings.get(i).getFirstBeat()+1)*leftBorder;
      int last = (endings.get(i).getLastBeat()+1)*leftBorder;
      int mid = (first+last)/2;
      g2.drawLine(first,topBorder-2,first,
      topBorder-8);
      g2.drawLine(last,topBorder-2,last,
              topBorder-8);
      g2.drawLine(first, topBorder-8, last, topBorder-8);
      g2.drawString(""+(i+1),mid,topBorder-9);
    }

    //Draw note spread.
    for (int i = spread[1]; i >= spread[0]; i--) {
      g2.setColor(Color.BLACK);
      String temp = valueToPitchString(i);
      int y = calcY(i) + 14;
      g2.drawString(temp, 10, y);
    }

    //Paint vert gridlines.
    g2.setColor(Color.black);
    for (int i = leftBorder; i <= windowWidth; i += 4 * leftBorder) {
      g2.setStroke(new BasicStroke(2));
      g.drawLine(i, topBorder, i, windowHeight);
    }

    //Paint horizontal gridlines.
    for (int i = topBorder; i <= windowHeight; i += topBorder) {
      g2.setStroke(new BasicStroke(2));
      if (i == 4) {
        g2.setStroke(new BasicStroke(5));
      }
      g.drawLine(leftBorder, i, windowWidth, i);
    }

    //Paint beat line.
    g2.setStroke(new BasicStroke(3));
    g2.setColor(Color.red);
    g2.drawLine(beatLine + 1, topBorder + 2, beatLine + 1, windowHeight - 3);

    //Draw repeats.
    for(Integer i:repeats){
      g2.setColor(Color.BLUE);
      int yPos = (topBorder+windowHeight)/2;
      int repeatLine =(i*GuiView.BEAT_WIDTH)+leftBorder;
      g2.drawLine(repeatLine-3,topBorder+2,repeatLine-3,windowHeight - 3 );
      g.fillOval(repeatLine-12,yPos-20, 12, 12) ;
      g.fillOval(repeatLine-12,yPos+20, 12, 12) ;
    }

    //Draw invert repeats.
    for(Integer i:invertRepeats){
      g2.setColor(Color.BLUE);
      int yPos = (topBorder+windowHeight)/2;
      int repeatLine =(i*GuiView.BEAT_WIDTH)+leftBorder;

      g2.drawLine(repeatLine+2,topBorder+2,repeatLine+2,windowHeight - 3 );
      g.fillOval(repeatLine,yPos-20, 12, 12) ;
      g.fillOval(repeatLine,yPos+20, 12, 12) ;
    }
  }

  /**
   * Given a note value, return the y position for that note.
   *
   * @param noteValue 0-127 value from a Note.
   * @return y position of note value.
   */
  private int calcY(int noteValue) {
    int reset = noteValue - spread[0];
    int mod = (reset * 2);
    int y = (reset + noteCount - mod) * topBorder;
    return y;
  }

  /**
   * Given a note value, returns its string representation.
   * @param noteValue Value to convert to a string.
   * @return String representation of given note value.
   */
  private String valueToPitchString(int noteValue) {
    StringBuilder result = new StringBuilder();
    result.append(INote.Pitch.fromValue(noteValue % 12).toString());
    result.append((noteValue / 12)-1);
    return result.toString();
  }

  /**
   * Updates the next beat
   * @param beat updates to the next beat
   */
  public void nextBeat(int beat) {
    curBeat = beat;
    repaint();
  }

  /**
   * Returns the String representation of a note with = as a delimiter between values
   * example: Pitch=Beat
   * @param x
   * @param y
   * @return
   */
  public int[] getNoteFromClick(int x, int y){
    int[] results = new int[2];
    //Find and convert pitchValue.
    results[0] =  yCoordToValue(y);
    //Find and append beat value.
    results[1]  = xCoordToValue(x);

    return results;
  }

  /**
   * Adds repeat at given beat number.
   */
  public void addRepeat(int beatNum){
    this.repeats.add(beatNum);
    repaint();
  }

  /**
   * Adds inverted repeat at given beat number.
   */
  public void addInvertRepeat(int beatNum){
    this.invertRepeats.add(beatNum);
    repaint();
  }

  /**
   * Adds given ending to this display.
   * @param end ending to be added.
   */
  public void addEnding(EndPair end){
    this.endings.add(end);
    repaint();
  }

  /**
   * removes given display.
   * @param end ending number to be removed.
   */
  public void removeEnding(EndPair end) throws IllegalArgumentException{
    endings.remove(end);
    repaint();
  }

  /**
   *Given a y coordinate, return the pitch value associated with it.
   * @param y Mouse y position.
   * @return Pitch value associated with given y.
   */
  private  int yCoordToValue(int y) {
    int ordinal = ((y - topBorder - 1) / topBorder) - 1;
    int value = (noteMod - ordinal) + spread[0];
    return value;
  }

  /**
   * Given an x coordinate, return the beat number associated with it.
   * @param x Mouse x position.
   * @return Beat number associated with given x.
   */
  private  int xCoordToValue(int x) {
    int value = ((x - leftBorder) / leftBorder);
    return value;
  }

  /**
   * Updates and redraws this note display after an edited music sheet.
   * @param notes new notes.
   * @param spread new spread.
   */
  public void updateNotes(Collection<INote> notes, int[] spread){
    this.notes = notes;
    this.spread = spread;
    updateSpread();
  }

  /**
   * Updates the note spread based on an update music sheet,
   */
  private void updateSpread(){
    this.noteMod = spread[1] - spread[0] - 1;
    this.noteCount = spread[1] - spread[0] + 1;
    this.windowHeight = (noteMod + 3) * topBorder;
    this.windowWidth = (spread[2] + 1) * leftBorder;
    setPreferredSize(new Dimension(windowWidth, windowHeight));
  }

  public void removeRepeat(int beat){
    int index = -1;
    for(int i = 0;i<repeats.size();i++){
      if(repeats.get(i) == beat){
        index = i;
      }
    }
    if(index != -1) {
      repeats.remove(index);
    }
  }

  public void removeInvertRepeat(int beat){
    int index = -1;
    for(int i = 0;i<invertRepeats.size();i++){
      if(invertRepeats.get(i) == beat){
        index = i;
      }
    }
    if(index != -1) {
      invertRepeats.remove(index);
    }
  }

  public void wipeRepeats(){
    this.invertRepeats = new ArrayList<>();
    this.repeats = new ArrayList<>();
    this.endings = new ArrayList<>();
  }

}
