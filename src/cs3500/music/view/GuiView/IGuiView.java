package cs3500.music.view.GuiView;

import cs3500.music.model.INote;

/**
 * Created by Jake on 6/21/2016.
 */
public interface IGuiView {
  /**
   * Returns the note from the 5 Text fields in the editor pane
   * @return Note being newly created
   */
  INote getNoteFromFields();

  /**
   * Returns the currently selected note in the JList of notes
   * @return Currently Selected Note
   */
  INote getNoteFromList();
}
