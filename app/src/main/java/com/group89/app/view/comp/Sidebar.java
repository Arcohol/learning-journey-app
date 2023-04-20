package com.group89.app.view.comp;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Sidebar extends JPanel {
  class IButton extends JButton {
    public IButton(String text) {
      super(text);
      this.setAlignmentX(CENTER_ALIGNMENT);
      this.setPreferredSize(BUTTON_SIZE);
      this.setMaximumSize(BUTTON_SIZE);
      this.setFocusPainted(false);
      this.setBackground(Color.LIGHT_GRAY);
    }
  }

  private static final Dimension BUTTON_SIZE = new Dimension(100, 30);
  private static final int NUM_BUTTONS = 3;
  private static final Dimension RIGID_AREA_SIZE = new Dimension(0, 10);

  private JButton[] buttons;
  private JButton exitButton;

  public Sidebar() {
    super();

    this.setBackground(Color.WHITE);

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
