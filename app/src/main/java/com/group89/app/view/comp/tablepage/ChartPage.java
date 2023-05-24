package com.group89.app.view.comp.tablepage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.group89.app.controller.ChartPageController;
import com.group89.app.view.comp.IButton;

public class ChartPage extends JPanel {
  private JPanel displayPanel;
  private JPanel controlPanel;
  private JButton[] buttons;
  
  public ChartPage() {
    super();
    
    setLayout(new GridBagLayout());

    displayPanel = new JPanel(new GridBagLayout());
    displayPanel.setBorder(BorderFactory.createTitledBorder("Display"));
    displayPanel.setBackground(Color.WHITE);

    controlPanel = new JPanel();
    controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
    controlPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
    controlPanel.setPreferredSize(new Dimension(200, 0));
    controlPanel.setBorder(BorderFactory.createTitledBorder("Control"));
    controlPanel.setBackground(Color.WHITE);

    buttons = new JButton[3];
    buttons[0] = new IButton("Trend");
    buttons[1] = new IButton("Composition");
    buttons[2] = new IButton("Distribution");
    for (JButton button : buttons) {
      button.setMaximumSize(new Dimension(150, 30));
      button.setAlignmentX(JButton.CENTER_ALIGNMENT);
    }

    controlPanel.add(Box.createVerticalGlue());

    controlPanel.add(buttons[0]);
    controlPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    controlPanel.add(buttons[1]);
    controlPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    controlPanel.add(buttons[2]);

    controlPanel.add(Box.createVerticalGlue());

    GridBagConstraints c = new GridBagConstraints();

    c.insets = new Insets(10, 10, 10, 10);

    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    add(displayPanel, c);

    c.insets = new Insets(10, 0, 10, 10);

    c.gridx = 1;
    c.gridy = 0;
    c.weightx = 0;
    c.weighty = 1;
    c.fill = GridBagConstraints.VERTICAL;
    add(controlPanel, c);

    new ChartPageController(this);
  }

  public JPanel getDisplayPanel() {
    return displayPanel;
  }

  public JPanel getControlPanel() {
    return controlPanel;
  }

  public JButton[] getButtons() {
    return buttons;
  }

  public void setChart(JPanel chart) {
    GridBagConstraints c = new GridBagConstraints();
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;

    displayPanel.removeAll();
    displayPanel.add(chart, c);
    displayPanel.revalidate();
    displayPanel.repaint();
  }
}
