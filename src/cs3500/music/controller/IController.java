package cs3500.music.controller;

/**
 * Interface for a controller An IController should take at least one IMidiView and one IGuiView
 * methods create instances of Runnable objects that can then be mapped to commands.
 * Created by Jake on 6/21/2016.
 */
public interface IController {
  /**
   *Handles mouse clicks.
   * @param x x position of the mouse click.
   * @param y y position of the mouse click.
   */
  void mouseRunnable(int x, int y);

  /**
   * Adds a note to the composition
   */
  Runnable addNote();

  /**
   * Runnable function object for removing a note from the composite view.
   */
  Runnable removeNote();

  /**
   * Runnable function object for editting a note in the composite view.
   */
  Runnable editNote();


  /**
   * Runnable function object for opening a file in the composite view.
   */
  Runnable openFile();

  /**
   * Runnable function object for scrolling the composite view to the left.
   */
  Runnable scrollLeft();

  /**
   * Runnable function object for scrolling the composite view to the right.
   */
  Runnable scrollRight();

  /**
   * Runnable function object for scrolling to home.
   */
  Runnable scrollHome();

  /**
   * Runnable function object for scrolling to the end.
   */
  Runnable scrollEnd();

  /**
   * Starts playing current IComposition
   */
  Runnable startPlay();

  /**
   * Updates the position of the beat bar in the composite view. Synchronized with Play.
   */
  Runnable updateBar();

  /**
   * Plays the music sheet currently loaded in the composite view.
   */
  Runnable play();

  /**
   * Stops playing current IComposition
   */
  Runnable stopPlay();

  /**
   * Resumes playing current IComposition
   */
  Runnable resumePlay();

  Runnable addRepeat();

  Runnable removeEnd();
}