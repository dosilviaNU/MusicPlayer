package cs3500.music.controller;

/**
 * Interface for a controller An IController should take at least one IMidiView and one IGuiView
 * Created by Jake on 6/21/2016.
 */
public interface IController {
  /**
   *Handles mouse clicks.
   * @param x x position of the mouse click.
   * @param y y position of the mouse click.
   */
  void mouseRunnable(int x, int y);


}