package cs3500.music.controller;

import cs3500.music.view.midi.IMidiView;

/**
 * Concrete Class implementing the IController Interface
 * Created by Jake on 6/21/2016.
 */
public class MidiController {
  IMidiView player;
  IGuiView viewer;

  MidiController(IMidiView player, IGuiView viewer) {
    this.player = player;
    this.viewer = viewer;
  }


  /**
   * sets the position to the current song beat
   */
  class setPosition implements Runnable {
    @Override
    public void run() {

    }
  }

  /**
   * Add a note to a IComposition
   */
  class addNote implements Runnable {
    @Override
    public void run() {

    }
  }

  /**
   * Edits an existing note in an IComposiiton
   */
  class editNote implements Runnable {
    @Override
    public void run() {

    }
  }

  /**
   * Remove note from IComposition
   */
  class removeNote implements Runnable {
    @Override
    public void run() {

    }
  }

  /**
   * Gets the current beat of a playing IComposition
   */
  class getBeat implements Runnable {
    @Override
    public void run() {

    }
  }

  /**
   * Starts playing current IComposition
   */
  class startPlay implements Runnable {
    @Override
    public void run() {

    }
  }

  /**
   * Stops playing current IComposition
   */
  class stopPlay implements Runnable {
    @Override
    public void run() {

    }
  }

  /**
   * Resumes playing current IComposition
   */
  class resumePlay implements Runnable {
    @Override
    public void run() {

    }
  }
}
