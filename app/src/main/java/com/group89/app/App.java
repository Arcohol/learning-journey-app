package com.group89.app;

import javax.swing.SwingUtilities;
import com.group89.app.view.MainFrame;

public class App {
  public static void main(String[] args) {
    System.setProperty("sun.java2d.uiScale.enabled", "false");
    SwingUtilities.invokeLater(MainFrame::new);
  }
}
