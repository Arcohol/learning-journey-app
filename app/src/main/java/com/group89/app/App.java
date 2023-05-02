package com.group89.app;

import javax.swing.SwingUtilities;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;
import com.group89.app.view.MainFrame;

public class App {
  public static void main(String[] args) {
    Configurator.setLevel(LogManager.getRootLogger(), Level.DEBUG);

    // System.setProperty("sun.java2d.uiScale.enabled", "false");
    SwingUtilities.invokeLater(MainFrame::new);
  }
}
