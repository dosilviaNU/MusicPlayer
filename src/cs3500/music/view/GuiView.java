package cs3500.music.view;

import cs3500.music.model.IMusicSheet;
import cs3500.music.model.INote;
import cs3500.music.model.MidiNote;
import cs3500.music.model.MidiSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * Created by David on 6/12/2016.
 */
public class GuiView extends JFrame implements IMusicView{
    private static IMusicSheet sheet;
    private static JPanel displayPanel;
    public static final int GUI_WIDTH = 1350;
    public static int BEAT_HEIGHT;
    public static int NOTE_COUNT;
    public static int GUI_HEIGHT;
    public static final int BEAT_WIDTH = 25;




    public static void main(String[] args){
        new GuiView().display();
    }

    public GuiView(){
        super("Macaroni");
        IMusicSheet sheet3 = new MidiSheet();



        sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 0, 5));
        sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 2, 2));
        sheet3.addNote(new MidiNote(INote.Pitch.C, 4, 4, 7));
        sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 6, 2));
        sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 8, 5));
        sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 10, 2));
        sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 12, 3));
        sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 16, 2));
        sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 18, 2));
        sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 20, 4));
        sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 24, 2));
        sheet3.addNote(new MidiNote(INote.Pitch.G, 4, 26, 8));
        sheet3.addNote(new MidiNote(INote.Pitch.G, 4, 28, 4));
        sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 32, 2));
        sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 34, 2));
        sheet3.addNote(new MidiNote(INote.Pitch.C, 4, 36, 2));
        sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 38, 2));
        sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 40, 2));
        sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 42, 2));
        sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 44, 2));
        sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 46, 2));
        sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 48, 2));
        sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 50, 2));
        sheet3.addNote(new MidiNote(INote.Pitch.E, 4, 52, 2));
        sheet3.addNote(new MidiNote(INote.Pitch.D, 4, 54, 2));
        sheet3.addNote(new MidiNote(INote.Pitch.C, 4, 56, 8));
        sheet3.addNote(new MidiNote(INote.Pitch.C, 5, 8, 8));
        this.sheet = sheet3;
        BEAT_HEIGHT=determineBeatHeight();
        NOTE_COUNT = determineNoteCount();
        GUI_HEIGHT = NOTE_COUNT*BEAT_HEIGHT+50;


        setLayout(new BorderLayout());
        this.displayPanel = new NotePanel((MidiSheet)sheet);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        add(displayPanel,BorderLayout.CENTER);

        BeatCount beatcount = new BeatCount();
        NoteSpread noteSpread = new NoteSpread(sheet.getSpread(sheet.getNotes()));
        add(noteSpread,BorderLayout.WEST);
        add(beatcount, BorderLayout.NORTH);

        this.setSize(new Dimension(GuiView.GUI_WIDTH, GuiView.GUI_HEIGHT));
        this.getContentPane().getHeight();

        setResizable(false);
        //this.pack();





        repaint();

    }


    private static int determineBeatHeight(){
        int[] spread = sheet.getSpread(sheet.getNotes());
        int noteCount = spread[1] - spread[0] +1;
        int beatHeight = 400/noteCount;
        return beatHeight;
    }

    private static int determineNoteCount(){
        int[] spread = sheet.getSpread(sheet.getNotes());
        int noteCount = spread[1] - spread[0] +1;
        return noteCount;
    }
    @Override
    public void display() {
        this.setVisible(true);
    }
}
