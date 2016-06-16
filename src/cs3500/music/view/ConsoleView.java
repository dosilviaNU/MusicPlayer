package cs3500.music.view;

import cs3500.music.model.IMusicSheet;
import cs3500.music.model.INote;
import cs3500.music.model.MidiNote;
import cs3500.music.model.MidiSheet;

/**
 * Created by David on 6/14/2016.
 */
public class ConsoleView {
  private IMusicSheet sheet;

  public ConsoleView(IMusicSheet givenSheet){
    this.sheet=givenSheet;
  }

  public void display(){
    System.out.print(sheet.printSheet());
  }
}
