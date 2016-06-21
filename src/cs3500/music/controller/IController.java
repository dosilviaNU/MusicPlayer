package cs3500.music.controller;

/**
 * Interface for a controller
 * An IController should take at least one IMidiView and one IGuiView
 * Created by Jake on 6/21/2016.
 */
public interface IController {
  /**
   * sets the position to the current song beat
   */
  void setPosition();

  /**
   * Add a note to a IComposition
   */
  void addNote();

  /**
   * Edits an existing note in an IComposiiton
   */
  void editNote();

  /**
   * Remove note from IComposition
   */
  void removeNote();

  /**
   * Gets the current beat of a playing IComposition
   */
  void getBeat();


}