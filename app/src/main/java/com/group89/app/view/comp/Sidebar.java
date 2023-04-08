package com.group89.app.view.comp;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Sidebar extends JPanel {
  private static final int NUM_BUTTONS = 3;
  private JButton[] buttons = new JButton[NUM_BUTTONS];

  public Sidebar() {
    super();

    this.setSize(200, 0);
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    for (int i = 0; i < NUM_BUTTONS; i++) {
      this.buttons[i] = new JButton("Button " + i);
      this.add(Box.createRigidArea(new Dimension(0, 10)));
      this.add(this.buttons[i]);
    }

    this.setVisible(true);
  }

  public void setButtonAction(int index, ActionListener action) {
    this.buttons[index].addActionListener(action);
  }
}
