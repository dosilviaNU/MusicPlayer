package cs3500.music.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener Action Events in an IController Created by Jake on 6/21/2016.
 */
public class SwingActionListener implements ActionListener {
  IController controller;

  SwingActionListener(IController controller) {
    this.controller = controller;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    controller.actionRunnable(e.getActionCommand());
  }
}
