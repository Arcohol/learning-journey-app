package com.group89.app.view.comp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Sidebar extends JPanel {
  private static final int NUM_BUTTONS = 3;
  private static final Dimension BUTTON_SIZE = new Dimension(100, 30);
  private JButton[] buttons = new JButton[NUM_BUTTONS];

  public Sidebar() {
    super();
    this.setPreferredSize(new Dimension(120, 0));
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // set border for testing
    this.setBorder(BorderFactory.createLineBorder(Color.BLACK));


    // dummy buttons
    // todo: each button serves as a link to different pages
    for (int i = 0; i < NUM_BUTTONS; i++) {
      this.buttons[i] = new JButton("Button " + i);
      this.buttons[i].setAlignmentX(CENTER_ALIGNMENT);
      this.buttons[i].setMaximumSize(BUTTON_SIZE);
      this.add(Box.createRigidArea(new Dimension(0, 10)));
      this.add(this.buttons[i]);
    }

    // add a exit button at the very buttom
    // just for testing
    // use glue
    this.add(Box.createVerticalGlue());
    JButton exitButton = new JButton("Exit");
    exitButton.setMaximumSize(BUTTON_SIZE);
    exitButton.setAlignmentX(CENTER_ALIGNMENT);
    exitButton.addActionListener(e -> System.exit(0));
    this.add(exitButton);
    
    this.add(Box.createRigidArea(new Dimension(0, 10)));

    this.setVisible(true);
  }

  public void setButtonAction(int index, ActionListener action) {
    this.buttons[index].addActionListener(action);
  }
}
