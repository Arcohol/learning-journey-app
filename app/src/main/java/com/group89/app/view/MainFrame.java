package com.group89.app.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.group89.app.controller.Controller;
import com.group89.app.controller.MainFrameController;
import com.group89.app.view.comp.MarkRecordPage;
import com.group89.app.view.comp.Sidebar;
import com.group89.app.view.comp.TaskPage;

public class MainFrame extends JFrame {
  private Sidebar sidebar;
  private JPanel content;
  private JPanel[] pages = new JPanel[3];

  public MainFrame() {
    super("Group 89");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1200, 800);
    this.setLocationRelativeTo(null);
    // this.setResizable(false);
    this.setLayout(new BorderLayout());

    this.sidebar = new Sidebar();
    this.add(this.sidebar, BorderLayout.WEST);

    this.content = new JPanel(new CardLayout());
    this.add(this.content, BorderLayout.CENTER);

    this.pages[0] = new MarkRecordPage();
    this.pages[0].setBackground(java.awt.Color.WHITE);

    this.pages[1] = new TaskPage();
    this.pages[1].setBackground(java.awt.Color.WHITE);

    this.pages[2] = new JPanel();
    this.pages[2].setBackground(java.awt.Color.BLUE);

    this.content.add(this.pages[0], "page1");
    this.content.add(this.pages[1], "page2");
    this.content.add(this.pages[2], "page3");

    this.setVisible(true);

    Controller controller = new MainFrameController(this);
    controller.init();
  }

  public Sidebar getSidebar() {
    return this.sidebar;
  }

  public JPanel getContent() {
    return this.content;
  }
}
