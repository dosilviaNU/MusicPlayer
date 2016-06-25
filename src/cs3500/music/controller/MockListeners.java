package cs3500.music.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

/**
 * Created by Jake on 6/25/2016.
 */
public class MockListeners implements ActionListener, KeyListener, MouseListener {
  IController controller;
  Map<String, Runnable> actionMap;
  Map<Integer, Runnable> keyMap;

  public MockListeners(IController controller, Map<String, Runnable> actionMap, Map<Integer,
          Runnable> keyMap) {
    this.controller = controller;
    this.actionMap = actionMap;
    this.keyMap = keyMap;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.actionRunnable(e.getActionCommand());
  }

  public void actionRunnable(String key) {
    Runnable action = actionMap.get(key);
    action.run();
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

    Runnable action = keyMap.get(e.getKeyCode());
    try {
      action.run();
    } catch (Exception ex) {
      System.out.println("Invalid Key");
    }

  }

  @Override
  public void keyReleased(KeyEvent e) {

  }

  @Override
  public void mouseClicked(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
