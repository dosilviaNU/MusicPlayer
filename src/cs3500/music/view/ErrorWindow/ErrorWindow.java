package cs3500.music.view.ErrorWindow;

import javax.swing.*;

/**
 * Created by Jake on 6/24/2016.
 */
public class ErrorWindow {
  public ErrorWindow(String infoMessage, String titleBar) {
    JOptionPane.showMessageDialog(null, infoMessage, "Error: " + titleBar, JOptionPane
            .INFORMATION_MESSAGE);
  }
}