package cs3500.music.view;

import cs3500.music.model.IComposition;
import cs3500.music.model.IMusicSheet;
import cs3500.music.model.INote;
import cs3500.music.model.MidiNote;
import cs3500.music.model.MidiSheet;

/**
 * Created by David on 6/14/2016.
 */
public class ConsoleView {
  private IComposition sheet;

  public ConsoleView(IComposition givenSheet){
    this.sheet=givenSheet;
  }

  public void display(){
    System.out.print(sheet.printSheet());
  }
}
