package cs3500.music.view.GuiView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cs3500.music.model.INote;

/**
 * Created by David on 6/16/2016.
 */


//Work in progress.
public class NoteEditOptionsPanel extends JPanel {
  JLabel pitch;
  JLabel octave;
  JLabel beatStart;
  JLabel length;
  JTextField pitchArea;
  JTextField octaveArea;
  JTextField beatArea;
  JTextField lengthArea;

  public NoteEditOptionsPanel() {
    setSize(100, 100);
    setLayout(new GridBagLayout());
  }

  public String noteEditOptions(){
    String[] options = {"Enter as Midi Value?", "Enter as Pitch?"};
    int choice = JOptionPane.showOptionDialog(this,
            "Choose a Method:",
            "How would you like to enter your edit?",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,     //do not use a custom Icon
            options, null);
    if (choice == 0) {
      return midiEntry();
    }
    if(choice == 1){
      return pitchEntry();
    }
    else{
      return null;
    }
  }
  public String noteAddOptions(){
    String[] options = {"Enter as Midi Value?", "Enter as Pitch?"};
    int choice = JOptionPane.showOptionDialog(this,
            "Choose a Method:",
            "How would you like add your note?",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,     //do not use a custom Icon
            options, null);
    if (choice == 0) {
      return midiEntry();
    }
    if(choice == 1){
      return pitchEntry();
    }
    else{
      return null;
    }
  }



  public String midiEntry() {
    GridBagConstraints gc = new GridBagConstraints();
    StringBuilder sb = new StringBuilder();
    gc.gridx = 0;
    gc.gridy = 0;
    add(new JLabel("Enter a Value 0-127: "), gc);
    gc.gridx = 0;
    gc.gridy = 1;
    add(new JLabel("Enter a Start Beat: "), gc);
    gc.gridx = 0;
    gc.gridy = 2;
    add(new JLabel("Enter a Duration: "), gc);
    gc.gridx = 1;
    gc.gridy = 0;
    add(new JTextField(5), gc);
    gc.gridx = 1;
    gc.gridy = 1;
    add(new JTextField(5), gc);
    gc.gridx = 1;
    gc.gridy = 2;
    add(new JTextField(5), gc);
    return null;
  }


  public String pitchEntry(){
    GridBagConstraints gc = new GridBagConstraints();
    gc.gridx = 0;
    gc.gridy = 0;
    add(new JLabel("Enter a Value 0-127: "), gc);
    gc.gridx = 0;
    gc.gridy = 1;
    add(new JLabel("Enter an Octave: "), gc);
    gc.gridx = 0;
    gc.gridy = 2;
    add(new JLabel("Enter a Start Beat: "), gc);
    gc.gridx = 0;
    gc.gridy = 3;
    add(new JLabel("Enter a Duration: "), gc);

    gc.gridx = 1;
    gc.gridy = 0;
    add(new JTextField(5), gc);
    gc.gridx = 1;
    gc.gridy = 1;
    add(new JTextField(5), gc);
    gc.gridx = 1;
    gc.gridy = 2;
    add(new JTextField(5), gc);
    gc.gridx = 1;
    gc.gridy = 3;
    add(new JTextField(5), gc);
    setVisible(true);

    return null;
  }
}


