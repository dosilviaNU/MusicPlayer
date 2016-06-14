package cs3500.music.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by David on 6/14/2016.
 */

public class NotePanel extends JPanel {
    public NotePanel(){

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();


        JButton note1 = new NoteDisplay(50);
        JButton note2 = new NoteDisplay(1000);




        setVisible(true);
        repaint();

    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.green);
        g2.fillRect(200,200,60,20);

        g2.setColor(Color.black);
        for (int i = 40; i < 1000;i+=40)
        {
            g2.setStroke(new BasicStroke(2));
            //if(i%80==0){g2.setStroke(new BasicStroke(5));}

            g.drawLine(i, 0,i,400);

        }

        for(int i = 20;i<400;i+=20){
            g2.setStroke(new BasicStroke(2));
            if(i==200){g2.setStroke(new BasicStroke(5));}
            g.drawLine(0,i, 1000,i);
        }




    }


}
