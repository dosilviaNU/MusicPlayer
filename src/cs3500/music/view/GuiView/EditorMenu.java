package cs3500.music.view.GuiView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by David on 6/21/2016.
 */
public class EditorMenu extends JComponent {

    public EditorMenu(){
        setVisible(true);
        setPreferredSize(new Dimension(250,500));
        setLayout(new FlowLayout());
        JPanel editorMain = new JPanel();
        editorMain.setVisible(true);
        editorMain.setPreferredSize(new Dimension(250,500));
        editorMain.setLayout(new BoxLayout(editorMain, BoxLayout.PAGE_AXIS));

        //Add file.
        JPanel fileAdder = new JPanel();
        fileAdder.setVisible(true);
        fileAdder.setLayout(new FlowLayout());
        TextField fileInput = new TextField(20);
        JButton openFile = new JButton("Open");
        fileAdder.add(fileInput);
        fileAdder.add(openFile);
        editorMain.add(fileAdder);

        //Add/Remove/Edit buttons.
        JPanel addRemoveEdit = new JPanel();
        addRemoveEdit.setVisible(true);
        addRemoveEdit.setLayout(new FlowLayout());
        JButton addNote = new JButton("Add");
        JButton removeNote = new JButton("Remove");
        JButton editNote = new JButton("Edit");
        addRemoveEdit.add(addNote);
        addRemoveEdit.add(removeNote);
        addRemoveEdit.add(editNote);
        editorMain.add(addRemoveEdit);

        //Note Field Level pitch,channel,velocity,start, end.
        JPanel pitchFields = new JPanel();
        pitchFields.setVisible(true);
        pitchFields.setLayout(new FlowLayout());
        JLabel pitch = new JLabel("Pitch:      ");
        JTextField pitchValues = new JTextField(10);
        pitchFields.add(pitch);
        pitchFields.add(pitchValues);
        editorMain.add(pitchFields);

        //Channel fields.
        JPanel channelFields = new JPanel();
        channelFields.setVisible(true);
        channelFields.setLayout(new FlowLayout());
        JLabel channel = new JLabel("Channel: ");
        JTextField channelValues = new JTextField(10);
        channelFields.add(channel);
        channelFields.add(channelValues);
        editorMain.add(channelFields);

        //Velocity fields.
        JPanel velocityFields = new JPanel();
        velocityFields.setVisible(true);
        velocityFields.setLayout(new FlowLayout());
        JLabel velocity = new JLabel("Velocity: ");
        JTextField velocityValues = new JTextField(10);
        velocityFields.add(velocity);
        velocityFields.add(velocityValues);
        editorMain.add(velocityFields);

        //Start fields.
        JPanel startFields = new JPanel();
        startFields.setVisible(true);
        startFields.setLayout(new FlowLayout());
        JLabel start = new JLabel("Start:      ");
        JTextField startValues = new JTextField(10);
        startFields.add(start);
        startFields.add(startValues);
        editorMain.add(startFields);

        //End fields.
        JPanel endFields = new JPanel();
        endFields.setVisible(true);
        endFields.setLayout(new FlowLayout());
        JLabel end = new JLabel("End:        ");
        JTextField endValues = new JTextField(10);
        endFields.add(end);
        endFields.add(endValues);
        editorMain.add(endFields);



        add(editorMain);
    }
}


