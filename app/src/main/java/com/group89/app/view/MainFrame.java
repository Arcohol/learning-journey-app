package com.group89.app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.group89.app.controller.MainFrameController;
import com.group89.app.view.comp.ContentPage;
import com.group89.app.view.comp.Sidebar;
import com.group89.app.view.comp.tablepage.AchievementPage;
import com.group89.app.view.comp.tablepage.ApplicationPage;
import com.group89.app.view.comp.tablepage.ChartPage;
import com.group89.app.view.comp.tablepage.MarkPage;
import com.group89.app.view.comp.tablepage.PortfolioPage;
import com.group89.app.view.comp.tablepage.RolePage;
import com.group89.app.view.comp.tablepage.TaskPage;

/**
 * The main frame of the application. It contains a sidebar and a content page.
 */
public class MainFrame extends JFrame {
  private Sidebar sidebar;
  private JPanel content;
  private JPanel[] pages = new JPanel[7];

  /**
   * The size of the frame.
   */
  private static final Dimension FRAME_SIZE = new Dimension(1200, 800);

  /**
   * Constructs a new {@code MainFrame}.
   */
  public MainFrame() {
    super("Group 89");

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(FRAME_SIZE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());

    sidebar = new Sidebar();
    add(sidebar, BorderLayout.WEST);

    content = new ContentPage();
    add(content, BorderLayout.CENTER);

    pages[0] = new MarkPage();
    pages[0].setBackground(Color.WHITE);

    pages[1] = new TaskPage();
    pages[1].setBackground(Color.WHITE);

    pages[2] = new RolePage();
    pages[2].setBackground(Color.WHITE);

    pages[3] = new AchievementPage();
    pages[3].setBackground(Color.WHITE);

    pages[4] = new PortfolioPage();
    pages[4].setBackground(Color.WHITE);

    pages[5] = new ApplicationPage();
    pages[5].setBackground(Color.WHITE);

    pages[6] = new ChartPage();
    pages[6].setBackground(Color.WHITE);

    content.add(pages[0], "page1");
    content.add(pages[1], "page2");
    content.add(pages[2], "page3");
    content.add(pages[3], "page4");
    content.add(pages[4], "page5");
    content.add(pages[5], "page6");
    content.add(pages[6], "page7");

    setVisible(true);

    new MainFrameController(this);
  }

  /**
   * Returns the sidebar of this frame.
   * 
   * @return the sidebar
   */
  public Sidebar getSidebar() {
    return sidebar;
  }

  /**
   * Returns the content page of this frame.
   * 
   * @return the content page
   */
  public JPanel getContent() {
    return content;
  }
}
