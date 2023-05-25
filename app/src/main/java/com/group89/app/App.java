package com.group89.app;

import javax.swing.SwingUtilities;
import com.group89.app.view.MainFrame;

/**
 * Main class of the application.
 */
public class App {
  /**
   * Entry point of the application. It creates a new {@code MainFrame}.
   * 
   * @param args arguments passed to the application
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(MainFrame::new);
  }
}
