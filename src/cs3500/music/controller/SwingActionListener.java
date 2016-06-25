package cs3500.music.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Listener Action Events in an IController Created by Jake on 6/21/2016.
 */
public class SwingActionListener implements ActionListener {
  IController controller;
  Map<String, Runnable> actionMap;

  SwingActionListener(IController controller, Map<String, Runnable> actionMap) {
    this.controller = controller;
    this.actionMap = actionMap;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.actionRunnable(e.getActionCommand());
  }

  public void actionRunnable(String key) {
    Runnable action = actionMap.get(key);
    action.run();
  }
}
