package cs3500.music.view;

import javax.swing.*;

import java.util.jar.JarEntry;

/**
 * Interface for different types of views for compositions
 * Created by David on 6/12/2016.
 */
public interface IMusicView<T> {
  /**
   * Display, or play, the view
   */
  public void display();
}
