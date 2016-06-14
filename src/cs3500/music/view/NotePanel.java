package cs3500.music.view;

import cs3500.music.model.IMusicSheet;
import cs3500.music.model.INote;
import cs3500.music.model.MidiNote;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

/**
 * Created by David on 6/14/2016.
 */

public class NotePanel extends JPanel {
    IMusicSheet sheet;
    int middleNote;
    int[] spread;
    int mod;
    int area;



    public NotePanel(IMusicSheet sheet){
        this.sheet = sheet;

        System.out.print(area);
        this.spread = sheet.getSpread(sheet.getNotes());
        middleNote = (spread[0] + spread[1]) / 2;

        System.out.println(spread[0]);
        System.out.println(middleNote);
        System.out.println(spread[1]);



        this.mod = 400/(spread[1] - spread[0]);
        setPreferredSize(new Dimension(400,1000));

        setLayout(new FlowLayout());

        GridBagConstraints gc = new GridBagConstraints();


        /*JButton note1 = new NoteDisplay(50);
        JButton note2 = new NoteDisplay(1000);
        JButton note3 = new NoteDisplay(300);
        JButton note4 = new NoteDisplay(600);
        gc.weightx=.5;
        gc.weighty=.5;
        gc.gridx=0;
        gc.gridy=0;
        add(note1);
        gc.gridx=1;
        gc.gridy=0;
        add(note2);
        gc.gridx=0;
        gc.gridy=2;
        add(note3);
        gc.gridx=1;
        gc.gridy=2;
        add(note4);*/




        setVisible(true);
        repaint();

    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;

        Collection<MidiNote> notes = sheet.getNotes();
        for(INote note:notes){

            int x = note.getStart()*20;
            int y = (note.getValue()%12)*mod;
            int width = note.getDuration()*10;



            g2.setColor(Color.green);
            g2.fillRect(x,y,width,mod);
            g2.setColor(Color.black);
            g2.fillRect(x,y,20,mod);

        }



        g2.setColor(Color.black);
        for (int i = 40; i < 1000;i+=100)
        {
            g2.setStroke(new BasicStroke(2));
            //if(i%80==0){g2.setStroke(new BasicStroke(5));}

            g.drawLine(i, 0,i,400);

        }

        for(int i = mod;i<7*mod;i+=mod){
            g2.setStroke(new BasicStroke(2));
            if(i==3*mod){g2.setStroke(new BasicStroke(5));}
            g.drawLine(0,i, 1000,i);
        }






    }


}
