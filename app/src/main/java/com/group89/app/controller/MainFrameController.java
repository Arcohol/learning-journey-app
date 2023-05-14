package com.group89.app.controller;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.group89.app.view.MainFrame;

public class MainFrameController {
  private MainFrame frame;

  public MainFrameController(MainFrame frame) {
    this.frame = frame;

    init();
  }

  private void init() {
    JButton[] buttons = frame.getSidebar().getButtons();
    JPanel content = frame.getContent();
    CardLayout layout = (CardLayout) content.getLayout();

    buttons[0].addActionListener(e -> {
      frame.getSidebar().onClick(0);
      layout.show(content, "page1");
    });
    buttons[1].addActionListener(e -> {
      frame.getSidebar().onClick(1);
      layout.show(content, "page2");
    });
    buttons[2].addActionListener(e -> {
      frame.getSidebar().onClick(2);
      layout.show(content, "page3");
    });
    buttons[3].addActionListener(e -> {
      frame.getSidebar().onClick(3);
      layout.show(content, "page4");
    });
    buttons[4].addActionListener(e -> {
      frame.getSidebar().onClick(4);
      layout.show(content, "page5");
    });
    buttons[5].addActionListener(e -> {
      frame.getSidebar().onClick(5);
      layout.show(content, "page6");
    });

    frame.getSidebar().getExitButton().addActionListener(e -> System.exit(0));

    buttons[0].doClick();
  }
}
