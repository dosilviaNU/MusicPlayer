package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

/**
 * Listener for KeyBoard Events in an IController Created by Jake on 6/21/2016.
 */
public class SwingKeyboardListener implements KeyListener {
  private long lastPressProcessed = 0;
  IController controller;
  Map<Integer, Runnable> keyMap;

  public SwingKeyboardListener(IController controller, Map<Integer, Runnable> keyMap) {
    this.keyMap = keyMap;
    this.controller = controller;
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (System.currentTimeMillis() - lastPressProcessed > 500) {
      this.keyboardRunnable(e.getKeyCode());
      lastPressProcessed = System.currentTimeMillis();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }

  public void keyboardRunnable(int key) {
    Runnable action = keyMap.get(key);
    try {
      action.run();
    } catch (Exception e) {
      System.out.println("Invalid Key");
    }
  }
}
