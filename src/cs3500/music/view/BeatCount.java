package cs3500.music.view;

import java.awt.*;

import javax.swing.*;

/**
 * Created by David on 6/14/2016.
 */
public class BeatCount extends JPanel {
  public BeatCount(){
    setPreferredSize(new Dimension(1350,20));
    setLayout(new GridLayout(1,4 ));

    add(new JLabel(""));
    for(int i = 0;i<=48;i+=16){

      add(new JLabel(""+i));
    }
  }
}
