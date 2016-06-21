package cs3500.music.view.GuiView;

import cs3500.music.model.IMusicSheet;
import cs3500.music.model.INote;

import javax.swing.*;

import java.awt.*;

import java.util.*;
import java.util.List;

/**
 * Created by David on 6/14/2016.
 */

/**
 * Main window of the GuiView, paints all the notes,beat counts, and grid lines.
 */
public class NoteDisplay extends JComponent implements Scrollable {
  private static IMusicSheet sheet;
  private static int[] spread;
  private static int noteMod;
  private static int noteCount;
  private static final int topBorder = GuiView.BEAT_HEIGHT;
  private static final int leftBorder = GuiView.BEAT_WIDTH;
  private static int windowHeight;
  private static int windowWidth;
  private static Collection<INote> notes;
  private static int curBeat;

  /**
   * Default constructor for a NoteDisplay panel.
   * @param givenSheet
   */
  public NoteDisplay(IMusicSheet givenSheet) {
    super();
    setAutoscrolls(true);
    //Initialize instance variables.
    sheet = givenSheet;
    spread = sheet.getSpread(sheet.getNotes());
    noteMod = spread[1] - spread[0] - 1;
    noteCount = spread[1] - spread[0] + 1;
    windowHeight = (noteMod + 3) * topBorder;
    windowWidth = (spread[2]+1) * leftBorder;
    curBeat=2;


    setPreferredSize(new Dimension(windowWidth, windowHeight));
    setVisible(true);
  }

  /**
   * Draws the notes, grid lines, and beats of the music sheet.
   *
   * @param g Graphic object.
   */
  @Override
  public void paintComponent(Graphics g) {
    int beatLine = (curBeat*leftBorder)+leftBorder;
    Graphics2D g2 = (Graphics2D) g;
    notes = sheet.getNotes();

    //Draw notes.
    for (INote note : notes) {
      int x = (note.getStart() + 1) * leftBorder;
      int y = calcY(note.getValue());
      int width = (note.getDuration()-1)*leftBorder;
      g2.setColor(Color.green);
      g2.fillRect(x, y, width, topBorder);
      g2.setColor(Color.black);
      g2.fillRect(x, y, leftBorder, topBorder);
    }

    //Draw beat counts.
    for (int i = 0; i <= spread[2]; i += 16) {
      g2.drawString("" + i, (i + 1) * leftBorder, topBorder - 2);
    }

    //Draw note spread.
    for (int i = spread[1]; i >= spread[0]; i--) {
      String temp = valueToPitchString(i);
      int y = calcY(i) + 14;
      g2.drawString(temp, 10, y);
    }

    //Paint vert gridlines.
    g2.setColor(Color.black);
    for (int i = leftBorder; i < windowWidth; i += 4 * leftBorder) {
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
    g2.drawLine(beatLine+1, topBorder+2, beatLine+1, windowHeight-3);
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
   *
   * @param noteValue Value to convert to a string.
   * @return String representation of given note value.
   */
  private static String valueToPitchString(int noteValue) {
    StringBuilder result = new StringBuilder();
    result.append(INote.Pitch.fromValue(noteValue % 12).toString());
    result.append((noteValue / 12) - 1);
    return result.toString();
  }

  public void nextBeat(){
    curBeat=curBeat+1;
  }

  @Override
  public Dimension getPreferredScrollableViewportSize() {
    return null;
  }

  @Override
  public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
    return 0;
  }

  @Override
  public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
    return 0;
  }

  @Override
  public boolean getScrollableTracksViewportWidth() {
    return false;
  }

  @Override
  public boolean getScrollableTracksViewportHeight() {
    return false;
  }
}
