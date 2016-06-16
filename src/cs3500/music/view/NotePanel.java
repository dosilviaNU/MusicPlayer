package cs3500.music.view;

import cs3500.music.model.IMusicSheet;
import cs3500.music.model.INote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Created by David on 6/14/2016.
 */

public class NotePanel extends JLabel {
    private static IMusicSheet sheet;
    private static int[] spread;
    private static int noteMod;
    private static int noteCount;
    private static final int topBorder=GuiView.BEAT_HEIGHT;
    private static final int leftBorder=GuiView.BEAT_WIDTH;
    private static int windowHeight;
    private static int windowWidth;
    private static Map<String, List<INote>> pitchBeatMap;
    private static Collection<INote> notes;
    private EditorMenu test;

    public NotePanel(IMusicSheet givenSheet) {
        sheet = givenSheet;
        setLayout(new BorderLayout());
        spread = sheet.getSpread(sheet.getNotes());
        noteMod =spread[1]-spread[0]-1;
        noteCount=spread[1]-spread[0]+1;
        windowHeight=(noteMod +3)*topBorder;
        windowWidth=(spread[2]+1)*leftBorder;
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        pitchBeatMap=new HashMap<String, List<INote>>();
        notes=sheet.getNotes();
        buildPitchBeatMap();
        for(INote note:notes){
            addNoteToPitchMap(note);
        }
        setVisible(true);
        repaint();


        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                List<INote> tempNotes = NotePanel.mouseClickToNotes(e.getX(),e.getY());
                System.out.println(e.getX()+" "+e.getY());
                System.out.println(tempNotes.size());


                for(INote note:tempNotes){
                    System.out.println(note);
                }
                openDialog(tempNotes);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });



    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        notes = sheet.getNotes();

        //Draw notes.
        for (INote note : notes) {
            int x =(note.getStart()+1)*leftBorder;
            int y = calcY(note.getValue());
            int width=note.getDuration()*GuiView.BEAT_WIDTH;
            g2.setColor(Color.green);
            g2.fillRect(x, y, width, GuiView.BEAT_HEIGHT);
            g2.setColor(Color.black);
            g2.fillRect(x, y, GuiView.BEAT_WIDTH, GuiView.BEAT_HEIGHT);
        }

        //Draw beat counts.
        for(int i = 0;i<=spread[2];i+=16){
            g2.drawString(""+i,(i+1)*leftBorder,topBorder-2);
        }

        //Draw note spread.
        for(int i = spread[1];i >= spread[0];i--){
           String temp = valueToPitchString(i);
            int y = calcY(i)+14;
            g2.drawString(temp,10,y );
        }

        //Paint vert gridlines.
        g2.setColor(Color.black);
        for (int i =leftBorder;i < windowWidth;i +=4*leftBorder) {
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

    }

    private int calcY(int noteValue) {
        int reset = noteValue-spread[0];
        int mod = (reset*2);
        int y = (reset+noteCount-mod)*topBorder;
        return y;
    }

    private void buildPitchBeatMap(){
        for(int i = spread[1];i >= spread[0];i--){
            String temp = valueToPitchString(i);
            for(int j = 0;j <=spread[2];j++){
                pitchBeatMap.put(temp+j,new ArrayList<INote>());
            }
        }
    }

    private void addNoteToPitchMap(INote note){
        int start = note.getStart();
        int end = start+note.getDuration();
        int noteValue=note.getValue();
        for(int i=start;i<end;i++){
            String key=valueToPitchString(noteValue)+i;
            pitchBeatMap.get(key).add(note);
        }
    }


    private static String valueToPitchString(int i){
        StringBuilder result = new StringBuilder();
        result.append(INote.Pitch.fromValue(i % 12).toString());
        result.append((i / 12) - 1);
        return result.toString();
    }

    public static List<INote> mouseClickToNotes(int x, int y){
        StringBuilder key = new StringBuilder();
        int temp = ((y-topBorder-1)/topBorder)-1;
        int value = coordToValue(temp);
        key.append(valueToPitchString(value));
        int beat = ((x-leftBorder)/leftBorder);
        key.append(beat);
        List<INote> tempArray = pitchBeatMap.get(key.toString());
        return tempArray;
    }

    private static int coordToValue(int y){
        int value = (noteMod -y)+spread[0];
        return value;
    }

    private void openDialog(Collection<INote> notes){

        Window win = SwingUtilities.getWindowAncestor(this);
        test = new EditorMenu();
        test.addList(notes);





    }



}
