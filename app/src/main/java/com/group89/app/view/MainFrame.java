package com.group89.app.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.group89.app.controller.Controller;
import com.group89.app.controller.MainController;
import com.group89.app.view.comp.MarkRecordPage;
import com.group89.app.view.comp.Sidebar;

public class MainFrame extends JFrame {
  private Sidebar sidebar;
  private JPanel content;

  private JPanel[] pages = new JPanel[3];

  public MainFrame() {
    super("Group 89");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(800, 600);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setLayout(new BorderLayout());

    this.sidebar = new Sidebar();
    this.add(this.sidebar, BorderLayout.WEST);

    this.content = new JPanel(new CardLayout());
    this.add(this.content, BorderLayout.CENTER);

    // dummy panels
    // todo: each panel serves as a page for different functionalities
    this.pages[0] = new MarkRecordPage();

    this.pages[1] = new JPanel();
    this.pages[1].setBackground(java.awt.Color.GREEN);
    this.pages[2] = new JPanel();
    this.pages[2].setBackground(java.awt.Color.BLUE);

    this.content.add(this.pages[0], "page1");
    this.content.add(this.pages[1], "page2");
    this.content.add(this.pages[2], "page3");

    ((CardLayout) this.content.getLayout()).show(this.content, "page1");

    this.setVisible(true);

    Controller controller = new MainController(this);
    controller.init();
  }

  public Sidebar getSidebar() {
    return this.sidebar;
  }

  public JPanel getContent() {
    return this.content;
  }
}
