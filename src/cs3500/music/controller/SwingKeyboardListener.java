package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Listener for KeyBoard Events in an IController Created by Jake on 6/21/2016.
 */
public class SwingKeyboardListener implements KeyListener {
  IController controller;

  public SwingKeyboardListener(IController controller){
    this.controller = controller;
  }
  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    controller.keyboardRunnable(e.getKeyCode());
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
