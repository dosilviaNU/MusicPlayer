package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listener for MouseEvents in an IController Created by Jake on 6/21/2016.
 */
public class SwingMouseListener implements MouseListener {
  IController controller;

  public SwingMouseListener(IController controller){
    this.controller = controller;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    controller.mouseRunnable(e.getX(), e.getY());
  }

  @Override
  public void mousePressed(MouseEvent e) {
    //Not used
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    //Not used
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    //Not used
  }

  @Override
  public void mouseExited(MouseEvent e) {
    //Not used
  }
}
