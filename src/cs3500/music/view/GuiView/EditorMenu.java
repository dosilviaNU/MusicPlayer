package cs3500.music.view.GuiView;

import cs3500.music.model.INote;
import cs3500.music.model.MidiNote;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by David on 6/21/2016.
 */
public class EditorMenu extends JComponent {
  private JPanel fileAdder;
  private JTextField fileValue;
  private JPanel addRemoveEdit;
  private JPanel pitchFields;
  private JTextField pitchValue;
  private JPanel channelFields;
  private JTextField channelValue;
  private JPanel velocityFields;
  private JTextField velocityValue;
  private JPanel startFields;
  private JTextField startValue;
  private JPanel endFields;
  private JTextField endValue;
  private ArrayList<JButton> buttons;
  private JList noteList;
  private DefaultListModel<INote> noteListModel;
  private EditorMenuListener editListener;

  public EditorMenu() {

    setVisible(true);
    setPreferredSize(new Dimension(250, 500));
    setLayout(new FlowLayout());
    JPanel editorMain = new JPanel();
    editListener = new EditorMenuListener();

    buttons = new ArrayList<JButton>();

    editorMain.setVisible(true);
    editorMain.setPreferredSize(new Dimension(250, 500));
    editorMain.setLayout(new BoxLayout(editorMain, BoxLayout.PAGE_AXIS));


    JLabel fileBoxLabel = new JLabel("Enter Filename to Open: ");
    fileBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    editorMain.add(fileBoxLabel);


    //Add file.
    fileAdder = new JPanel();
    fileAdder.setVisible(true);
    fileAdder.setLayout(new FlowLayout());
    fileValue = new JTextField(15);

    JButton openFile = new JButton("Open");
    openFile.setActionCommand("open");
    buttons.add(openFile);

    fileAdder.add(fileValue);
    fileAdder.add(openFile);
    editorMain.add(fileAdder);

    //Add/Remove/Edit buttons.
    addRemoveEdit = new JPanel();
    addRemoveEdit.setVisible(true);
    addRemoveEdit.setLayout(new FlowLayout());

    JButton addNote = new JButton("Add");
    addNote.setActionCommand("add");
    buttons.add(addNote);

    JButton removeNote = new JButton("Remove");
    removeNote.setActionCommand("remove");
    buttons.add(removeNote);

    JButton editNote = new JButton("Edit");
    editNote.setActionCommand("edit");
    buttons.add(editNote);

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
    pitchValue = new JTextField(10);
    pitchFields.add(pitch);
    pitchFields.add(pitchValue);
    editorMain.add(pitchFields);

    //Channel fields.
    channelFields = new JPanel();
    channelFields.setVisible(true);
    channelFields.setLayout(new FlowLayout());
    JLabel channel = new JLabel("Channel: ");
    channelValue = new JTextField(10);
    channelFields.add(channel);
    channelFields.add(channelValue);
    editorMain.add(channelFields);

    //Velocity fields.
    velocityFields = new JPanel();
    velocityFields.setVisible(true);
    velocityFields.setLayout(new FlowLayout());
    JLabel velocity = new JLabel("Velocity: ");
    velocityValue = new JTextField(10);
    velocityFields.add(velocity);
    velocityFields.add(velocityValue);
    editorMain.add(velocityFields);

    //Start fields.
    startFields = new JPanel();
    startFields.setVisible(true);
    startFields.setLayout(new FlowLayout());
    JLabel start = new JLabel("Start:      ");
    startValue = new JTextField(10);
    startFields.add(start);
    startFields.add(startValue);
    editorMain.add(startFields);

    //End fields.
    endFields = new JPanel();
    endFields.setVisible(true);
    endFields.setLayout(new FlowLayout());
    JLabel end = new JLabel("Duration: ");
    endValue = new JTextField(10);
    endFields.add(end);
    endFields.add(endValue);
    editorMain.add(endFields);

    JLabel noteBoxLabel = new JLabel("Notes at Selected Beat");
    noteBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    editorMain.add(noteBoxLabel);


    //Notes at Beat List
    noteList = new JList();
    noteList.addListSelectionListener(editListener);
            noteListModel = new DefaultListModel();
    JScrollPane listScroll = new JScrollPane(noteList);
    listScroll.setSize(new Dimension(150, 300));
    noteList.setModel(noteListModel);
    noteList.setVisible(true);
    noteList.setPreferredSize(new Dimension(200, 300));
    noteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    editorMain.add(listScroll);
    add(editorMain);
  }

  public void populateNoteList(Collection<INote> notes) {
    noteList.removeListSelectionListener(editListener);
    noteListModel.clear();
    for (INote note : notes) {
      noteListModel.addElement(note);
    }
    noteList.addListSelectionListener(editListener);
  }

  public void addActionListener(ActionListener actionListener) {
    for (JButton button : buttons) {
      button.addActionListener(actionListener);
    }
  }

  public INote getNoteFromFields() throws IllegalArgumentException {
    int pValue = Integer.valueOf(pitchValue.getText());
    int start = Integer.valueOf(startValue.getText());
    int duration = Integer.valueOf(endValue.getText());
    int volume = Integer.valueOf(velocityValue.getText());
    int channel = Integer.valueOf(channelValue.getText());
    return new MidiNote(pValue, start, duration, volume, channel);
  }

  public INote getNoteFromList() {
    return (INote) noteList.getSelectedValue();
  }

  public String getFileFromField() {
    return fileValue.getText();
  }

  public void fieldsFromClick(int[] noteValue) {
    pitchValue.setText("" + noteValue[0]);
    startValue.setText("" + noteValue[1]);
  }

  class EditorMenuListener implements ListSelectionListener {
    @Override
    public void valueChanged(ListSelectionEvent e) {
      int index = noteList.getSelectedIndex();
      INote note = noteListModel.getElementAt(index);
      pitchValue.setText("" + note.getValue());
      channelValue.setText("" + note.getChannel());
      velocityValue.setText("" + note.getVolume());
      startValue.setText("" + note.getStart());
      endValue.setText("" + note.getDuration());
    }
  }
}


