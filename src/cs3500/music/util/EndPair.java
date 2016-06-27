package cs3500.music.util;

import cs3500.music.view.GuiView.EditorMenu;

/**
 * Created by David on 6/25/2016.
 */
public class EndPair {
  private int firstBeat;
  private int lastBeat;

  public EndPair(int firstBeat, int lastBeat){
    this.firstBeat = firstBeat;
    this.lastBeat = lastBeat;
  }

  public int getFirstBeat(){
    return firstBeat;
  }

  public int getLastBeat(){
    return lastBeat;
  }

  public boolean equals(Object o){
    if(o instanceof EndPair){
      EndPair that = (EndPair)o;
      return that.firstBeat==this.firstBeat&&that.lastBeat==this.lastBeat;
    }
    else{return false;}
  }
}
