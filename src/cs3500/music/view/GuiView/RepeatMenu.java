package cs3500.music.view.GuiView;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import javax.swing.*;

/**
 * Created by David on 6/27/2016.
 */
public class RepeatMenu extends JComponent {
  JTextField startNote;
  JTextField endNote;
  JButton addButton;
  JButton removeButton;
  List<JButton> buttons;

  public RepeatMenu(){
    setVisible(true);
    setSize(new Dimension(GuiView.GUI_WIDTH, GuiView.GUI_HEIGHT/4));
    setLayout(new FlowLayout());

    buttons = new ArrayList<JButton>();

    JLabel start = new JLabel("Start Note: ");
    startNote = new JTextField(3);
    JLabel end = new JLabel("End Notes: ");
    endNote = new JTextField(3);

    addButton = new JButton("Add Repeat/End");
    addButton.setActionCommand("addRepeat");
    buttons.add(addButton);

    removeButton = new JButton("Remove Repeat/End");
    removeButton.setActionCommand("removeRepeat");
    buttons.add(removeButton);

    add(start);
    add(startNote);
    add(end);
    add(endNote);
    add(addButton);
    add(removeButton);


    JPanel instructions = new JPanel();
    JTextPane commandInfo = new JTextPane();
    commandInfo.setText("Press P to Play Current Song from Start\nPress O to Stop the Song" +
            "\nPress I to Resume\n\nTo Add a Note Fill in Desired Values and Press Add" +
            "\nTo Remove a Note Select A Note from The List and Press Remove\n" +
            "To Edit a Note Select a Note from the List and Enter New Values And Press Edit");
    instructions.add(commandInfo);
    add(instructions);

  }


  public void addActionListener(ActionListener actions){
    for(JButton button:buttons){
      button.addActionListener(actions);
    }
  }

  public int[] getRepeats(){
    String startString = startNote.getText().trim();
    int startBeat = 0;
    if(!(startString.equals(""))){
      startBeat = Integer.valueOf(startString);
    }
    String endString = endNote.getText();
    String[] endBeats = endString.split(",");
    int[] allBeats = new int[1 + endBeats.length];
    allBeats[0] = startBeat;
    for(int i = 1;i<endBeats.length+1;i++){
        String trimmed = endBeats[i-1].trim();
        allBeats[i] = Integer.valueOf(trimmed);

    }
    return allBeats;
  }
}
