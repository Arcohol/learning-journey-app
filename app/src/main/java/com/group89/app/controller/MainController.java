package com.group89.app.controller;

import java.awt.CardLayout;
import javax.swing.JPanel;
import com.group89.app.view.MainFrame;

public class MainController {
  private MainFrame frame;

  public MainController(MainFrame frame) {
    this.frame = frame;
  }

  public void init() {
    JPanel content = this.frame.getContent();
    this.frame.getSidebar().setButtonAction(0, e -> {
      ((CardLayout) content.getLayout()).show(content, "page1");
    });

    this.frame.getSidebar().setButtonAction(1, e -> {
      ((CardLayout) content.getLayout()).show(content, "page2");
    });

    this.frame.getSidebar().setButtonAction(2, e -> {
      ((CardLayout) content.getLayout()).show(content, "page3");
    });
  }
}
