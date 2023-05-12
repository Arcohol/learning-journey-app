package com.group89.app.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.group89.app.controller.MainFrameController;
import com.group89.app.view.comp.MarkRecordPage;
import com.group89.app.view.comp.Sidebar;
import com.group89.app.view.comp.TaskPage;

public class MainFrame extends JFrame {
  private Sidebar sidebar;
  private JPanel content;
  private JPanel[] pages = new JPanel[3];

  private static final Dimension FRAME_SIZE = new Dimension(1200, 800);

  public MainFrame() {
    super("Group 89");

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(FRAME_SIZE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());

    sidebar = new Sidebar();
    add(sidebar, BorderLayout.WEST);

    content = new JPanel(new CardLayout());
    add(content, BorderLayout.CENTER);

    pages[0] = new MarkRecordPage();
    pages[0].setBackground(java.awt.Color.WHITE);

    pages[1] = new TaskPage();
    pages[1].setBackground(java.awt.Color.WHITE);

    pages[2] = new JPanel();
    pages[2].setBackground(java.awt.Color.BLUE);

    content.add(pages[0], "page1");
    content.add(pages[1], "page2");
    content.add(pages[2], "page3");

    setVisible(true);

    new MainFrameController(this);
  }

  public Sidebar getSidebar() {
    return sidebar;
  }

  public JPanel getContent() {
    return content;
  }
}
