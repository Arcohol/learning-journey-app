package com.group89.app.view.comp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Sidebar extends JPanel {
  class IButton extends JButton {
    public IButton(String text) {
      super(text);
      this.setAlignmentX(CENTER_ALIGNMENT);
      this.setMaximumSize(BUTTON_SIZE);
      this.setFocusPainted(false);
      this.setBorderPainted(false);
      this.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    }
  }

  private static final Dimension BUTTON_SIZE = new Dimension(150, 60);
  private static final int NUM_BUTTONS = 3;
  private static final Dimension RIGID_AREA_SIZE = new Dimension(0, 15);

  private JButton[] buttons;
  private JButton exitButton;

  public Sidebar() {
    super();

    buttons = new JButton[NUM_BUTTONS];
    exitButton = new IButton("Exit");

    this.setPreferredSize(new Dimension(120, 0));
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    this.buttons[0] = new IButton("Marks");
    this.add(Box.createRigidArea(RIGID_AREA_SIZE));
    this.add(this.buttons[0]);

    // dummy buttons
    for (int i = 1; i < buttons.length; i++) {
      this.buttons[i] = new IButton("Button " + i);
      this.add(Box.createRigidArea(RIGID_AREA_SIZE));
      this.add(this.buttons[i]);
    }

    this.add(Box.createVerticalGlue());
    this.add(exitButton);
    this.add(Box.createRigidArea(RIGID_AREA_SIZE));
  }

  public JButton[] getButtons() {
    return this.buttons;
  }

  public JButton getExitButton() {
    return this.exitButton;
  }
}
