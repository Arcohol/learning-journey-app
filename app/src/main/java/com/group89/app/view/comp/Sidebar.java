package com.group89.app.view.comp;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.group89.app.view.AppColor;


public class Sidebar extends JPanel {
  class IButton extends JButton {
    public IButton(String text) {
      super(text);
      this.setFocusPainted(false);
      this.setBorder(null);
      this.setFont(this.getFont().deriveFont(18f));
      this.setForeground(AppColor.DARK_GREY);
      this.setBackground(AppColor.LIGHT_GREY);
      this.setPreferredSize(BUTTON_SIZE);
    }
  }

  private static final Dimension BUTTON_SIZE = new Dimension(0, 50);
  private static final Dimension DATE_SIZE = new Dimension(0, 80);
  private static final Dimension SIDEBAR_SIZE = new Dimension(150, 0);
  private static final int NUM_BUTTONS = 3;

  private JLabel date;
  private JButton[] buttons;
  private JButton exitButton;
  private int currentButtonIndex = 0;

  public Sidebar() {
    super(new GridBagLayout());
    this.setBackground(AppColor.LIGHT_GREY);
    this.setPreferredSize(SIDEBAR_SIZE);

    GridBagConstraints c = new GridBagConstraints();

    this.date = new JLabel(LocalDate.now().toString());
    this.buttons = new JButton[NUM_BUTTONS];
    this.exitButton = new IButton("Exit");

    this.date.setFont(date.getFont().deriveFont(18f));
    this.date.setForeground(AppColor.DARK_GREY);
    this.date.setPreferredSize(DATE_SIZE);
    this.date.setHorizontalAlignment(JLabel.CENTER);
    this.date.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, AppColor.DARK_GREY));

    c.weightx = 1;
    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.HORIZONTAL;

    c.gridx = 0;
    c.gridy = 0;
    this.add(this.date, c);

    this.buttons[0] = new IButton("Marks");
    this.buttons[0].setForeground(AppColor.BLACK);
    this.buttons[0].setBackground(AppColor.WHITE);

    c.gridx = 0;
    c.gridy = 1;
    this.add(this.buttons[0], c);

    this.buttons[1] = new IButton("Tasks");

    c.gridx = 0;
    c.gridy = 2;
    this.add(this.buttons[1], c);

    this.buttons[2] = new IButton("Overview");

    c.gridx = 0;
    c.gridy = 3;
    this.add(this.buttons[2], c);

    c.weighty = 1;
    c.gridx = 0;
    c.gridy = 4;
    c.anchor = GridBagConstraints.SOUTH;
    this.add(exitButton, c);
  }

  public JButton[] getButtons() {
    return this.buttons;
  }

  public JButton getExitButton() {
    return this.exitButton;
  }

  public void onClick(int index) {
    if (index == this.currentButtonIndex) {
      return;
    }

    this.buttons[this.currentButtonIndex].setForeground(AppColor.DARK_GREY);
    this.buttons[this.currentButtonIndex].setBackground(AppColor.LIGHT_GREY);
    this.currentButtonIndex = index;
    this.buttons[this.currentButtonIndex].setForeground(AppColor.BLACK);
    this.buttons[this.currentButtonIndex].setBackground(AppColor.WHITE);
  }
}
