package com.group89.app.controller;

import java.awt.CardLayout;

import javax.swing.JPanel;

import com.group89.app.view.MainFrame;
import com.group89.app.view.comp.Sidebar;

public class MainController {
  private MainFrame frame;

  public MainController(MainFrame frame) {
    this.frame = frame;
  }

  public void init() {
    JPanel content = this.frame.getContent();
    Sidebar sidebar = this.frame.getSidebar();
    CardLayout layout = (CardLayout) content.getLayout();
    sidebar.setButtonAction(0, e -> {
      layout.show(content, "page1");
    });
    sidebar.setButtonAction(1, e -> {
      layout.show(content, "page2");
    });
    sidebar.setButtonAction(2, e -> {
      layout.show(content, "page3");
    });
  }
}
