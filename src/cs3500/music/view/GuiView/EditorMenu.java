package cs3500.music.view.GuiView;

import cs3500.music.model.INote;
import cs3500.music.model.MidiNote;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Collection;

/**
 * Created by David on 6/21/2016.
 */
public class EditorMenu extends JComponent {
    private JPanel fileAdder;
    private JPanel addRemoveEdit;
    private JPanel pitchFields;
    private JPanel channelFields;
    private JPanel velocityFields;
    private JPanel startFields;
    private JPanel endFields;
    private JList noteList;
    private DefaultListModel<MidiNote> noteListModel;

    public EditorMenu(){
        setVisible(true);
        setPreferredSize(new Dimension(250,500));
        setLayout(new FlowLayout());
        JPanel editorMain = new JPanel();
        editorMain.setVisible(true);
        editorMain.setPreferredSize(new Dimension(250,500));
        editorMain.setLayout(new BoxLayout(editorMain, BoxLayout.PAGE_AXIS));

        JLabel fileBoxLabel = new JLabel("Enter Filename to Open: ");
        fileBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        editorMain.add(fileBoxLabel);



        //Add file.
        fileAdder = new JPanel();
        fileAdder.setVisible(true);
        fileAdder.setLayout(new FlowLayout());
        TextField fileInput = new TextField(20);
        JButton openFile = new JButton("Open");
        fileAdder.add(fileInput);
        fileAdder.add(openFile);
        editorMain.add(fileAdder);

        //Add/Remove/Edit buttons.
        addRemoveEdit = new JPanel();
        addRemoveEdit.setVisible(true);
        addRemoveEdit.setLayout(new FlowLayout());
        JButton addNote = new JButton("Add");
        JButton removeNote = new JButton("Remove");
        JButton editNote = new JButton("Edit");
        addRemoveEdit.add(addNote);
        addRemoveEdit.add(removeNote);
        addRemoveEdit.add(editNote);
        editorMain.add(addRemoveEdit);

        JLabel fieldBoxLabel = new JLabel("Enter Desired Integer Values: ");
        fieldBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        editorMain.add(fieldBoxLabel);

        //Note Field Level pitch,channel,velocity,start, end.
        pitchFields = new JPanel();
        pitchFields.setVisible(true);
        pitchFields.setLayout(new FlowLayout());
        JLabel pitch = new JLabel("Pitch:      ");
        JTextField pitchValues = new JTextField(10);
        pitchFields.add(pitch);
        pitchFields.add(pitchValues);
        editorMain.add(pitchFields);

        //Channel fields.
        channelFields = new JPanel();
        channelFields.setVisible(true);
        channelFields.setLayout(new FlowLayout());
        JLabel channel = new JLabel("Channel: ");
        JTextField channelValues = new JTextField(10);
        channelFields.add(channel);
        channelFields.add(channelValues);
        editorMain.add(channelFields);

        //Velocity fields.
        velocityFields = new JPanel();
        velocityFields.setVisible(true);
        velocityFields.setLayout(new FlowLayout());
        JLabel velocity = new JLabel("Velocity: ");
        JTextField velocityValues = new JTextField(10);
        velocityFields.add(velocity);
        velocityFields.add(velocityValues);
        editorMain.add(velocityFields);

        //Start fields.
        startFields = new JPanel();
        startFields.setVisible(true);
        startFields.setLayout(new FlowLayout());
        JLabel start = new JLabel("Start:      ");
        JTextField startValues = new JTextField(10);
        startFields.add(start);
        startFields.add(startValues);
        editorMain.add(startFields);

        //End fields.
        endFields = new JPanel();
        endFields.setVisible(true);
        endFields.setLayout(new FlowLayout());
        JLabel end = new JLabel("Duration:        ");
        JTextField endValues = new JTextField(10);
        endFields.add(end);
        endFields.add(endValues);
        editorMain.add(endFields);

        JLabel noteBoxLabel = new JLabel("Notes at Selected Beat");
        noteBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        editorMain.add(noteBoxLabel);



        //Notes at Beat List
        noteList = new JList();
        noteList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = noteList.getSelectedIndex();
                MidiNote note = noteListModel.getElementAt(index);
                pitchValues.setText(note.toString());
                channelValues.setText(""+note.getChannel());
                velocityValues.setText(""+note.getVolume());
                startValues.setText(""+note.getStart());
                endValues.setText(""+note.getDuration());

            }
        });
        noteListModel = new DefaultListModel();
        JScrollPane listScroll = new JScrollPane(noteList);
        listScroll.setSize(new Dimension(150,300));
        noteList.setModel(noteListModel);
        noteListModel.addElement(new MidiNote(60, 5, 10));
        noteListModel.addElement(new MidiNote(50, 5, 20));
        noteListModel.addElement(new MidiNote(72, 5, 30));
        noteListModel.addElement(new MidiNote(80, 5, 40));
        noteListModel.addElement(new MidiNote(95, 5, 50));
        noteList.setVisible(true);
        noteList.setPreferredSize(new Dimension(200, 300));
        editorMain.add(listScroll);



        add(editorMain);
    }

    public void populateNoteList(Collection<MidiNote> notes){
        for(MidiNote note:notes){
            noteListModel.addElement(note);
        }
    }
}


