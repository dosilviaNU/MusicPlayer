package cs3500.music.view.GuiView;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.*;

import cs3500.music.model.INote;

/**
 * Created by David on 6/15/2016.
 */

//Work in progress.
public class EditorMenu extends JPanel{
  JComponent contents;

  public EditorMenu(int x, int y){
    super();
    setLocation(x,y);
    setVisible(true);

  }

  public  void editNotes(Collection<INote> notes){
    ArrayList<INote> temp = new ArrayList<INote>();
    for(INote note:notes){
      temp.add(note);
    }
    String[] noteMessage = new String[notes.size()+1];
    for(int i = 0;i<noteMessage.length-1;i++){
      noteMessage[i]=temp.get(i).toString()+" #"+(i+1)+ " @ beat " + temp.get(i).getStart();
    }



    noteMessage[noteMessage.length-1] = "Add Note";
    this.contents = new JOptionPane();
    JOptionPane optionPane =(JOptionPane)this.contents;

    int choice = optionPane.showOptionDialog(this,
            "Choose A Note:",
            "Edit Note",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,     //do not use a custom Icon
            noteMessage,null);
    setVisible(false);
    if(choice==noteMessage.length-1){

    }
    if (choice==-1){

    }

  }

  public void editNote(INote note){
    this.contents = new NoteEditOptionsPanel();
    NoteEditOptionsPanel editOptions = (NoteEditOptionsPanel) this.contents;
    add(this.contents);
    //this.pack();
    this.setVisible(true);
  }




  public void addNote(){
    this.contents = new NoteEditOptionsPanel();
    NoteEditOptionsPanel editOptions = (NoteEditOptionsPanel) this.contents;
    add(this.contents);
    //this.pack();
    this.setVisible(true);
  }


}
