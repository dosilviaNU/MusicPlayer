package cs3500.music.view;

import java.awt.*;

import javax.swing.*;

/**
 * Created by David on 6/14/2016.
 */
public class BeatCount extends JPanel {
  public BeatCount(){
    setPreferredSize(new Dimension(1350,20));
    setLayout(new GridBagLayout());

    GridBagConstraints gc = new GridBagConstraints();
    gc.gridy=0;
    gc.insets=new Insets(0,50,0,0);
    add(new JLabel(""));
    for(int i = 0;i<=48;i+=16){
      gc.weightx=1;
      gc.anchor=GridBagConstraints.WEST;
      JLabel temp =new JLabel(""+i);
      add(temp, gc);
      gc.insets=new Insets(0,12*GuiView.BEAT_WIDTH,0,0);
    }
  }
}
