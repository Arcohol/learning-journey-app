package com.group89.app.controller;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.group89.app.view.MainFrame;

public class MainFrameController implements Controller {
  private MainFrame frame;

  public MainFrameController(MainFrame frame) {
    this.frame = frame;
  }

  @Override
  public void init() {
    JPanel content = this.frame.getContent();
    CardLayout layout = (CardLayout) content.getLayout();
    JButton[] buttons = this.frame.getSidebar().getButtons();
    this.frame.getSidebar().onClick(0);
    buttons[0].addActionListener(e -> {
      this.frame.getSidebar().onClick(0);
      layout.show(content, "page1");
    });
    buttons[1].addActionListener(e -> {
      this.frame.getSidebar().onClick(1);
      layout.show(content, "page2");
    });
    buttons[2].addActionListener(e -> {
      this.frame.getSidebar().onClick(2);
      layout.show(content, "page3");
    });
    this.frame.getSidebar().getExitButton().addActionListener(e -> System.exit(0));
  }
}
