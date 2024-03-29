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

/**
 * A sidebar that contains buttons for navigating the application.
 */
public class Sidebar extends JPanel {
  /**
   * A button with a custom style and is used in {@link Sidebar}.
   */
  class IButton extends JButton {
    public IButton(String text) {
      super(text);

      setFocusPainted(false);
      setBorder(null);
      setFont(getFont().deriveFont(BUTTON_FONT_SIZE));
      setForeground(AppColor.DARK_GREY);
      setBackground(AppColor.LIGHT_GREY);
      setPreferredSize(BUTTON_SIZE);
    }
  }

  /**
   * The size of the sidebar components.
   */
  private static final Dimension BUTTON_SIZE = new Dimension(0, 50);

  /**
   * The size of the date label.
   */
  private static final Dimension DATE_SIZE = new Dimension(0, 80);

  /**
   * The size of the sidebar.
   */
  private static final Dimension SIDEBAR_SIZE = new Dimension(150, 0);

  /**
   * The font size of the sidebar buttons.
   */
  private static final float BUTTON_FONT_SIZE = 18f;

  /**
   * The number of buttons in the sidebar.
   */
  private static final int NUM_BUTTONS = 7;

  private JLabel date;
  private JButton[] buttons;
  private JButton exitButton;
  private int currentButtonIndex = 0;

  /**
   * Constructs a new {@link Sidebar}.
   */
  public Sidebar() {
    super(new GridBagLayout());

    setBackground(AppColor.LIGHT_GREY);
    setPreferredSize(SIDEBAR_SIZE);

    GridBagConstraints c = new GridBagConstraints();

    date = new JLabel(LocalDate.now().toString());
    buttons = new JButton[NUM_BUTTONS];
    exitButton = new IButton("Exit");

    date.setFont(date.getFont().deriveFont(BUTTON_FONT_SIZE));
    date.setForeground(AppColor.DARK_GREY);
    date.setPreferredSize(DATE_SIZE);
    date.setHorizontalAlignment(JLabel.CENTER);
    date.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, AppColor.DARK_GREY));

    c.weightx = 1;
    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.HORIZONTAL;

    c.gridx = 0;
    c.gridy = 0;
    add(date, c);

    buttons[0] = new IButton("Mark");
    buttons[0].setForeground(AppColor.BLACK);
    buttons[0].setBackground(AppColor.WHITE);

    c.gridx = 0;
    c.gridy = 1;
    add(buttons[0], c);

    buttons[1] = new IButton("Task");

    c.gridx = 0;
    c.gridy = 2;
    add(buttons[1], c);

    buttons[2] = new IButton("Role");

    c.gridx = 0;
    c.gridy = 3;
    add(buttons[2], c);

    buttons[3] = new IButton("Achievement");

    c.gridx = 0;
    c.gridy = 4;
    add(buttons[3], c);

    buttons[4] = new IButton("Portfolio");

    c.gridx = 0;
    c.gridy = 5;
    add(buttons[4], c);

    buttons[5] = new IButton("Application");

    c.gridx = 0;
    c.gridy = 6;
    add(buttons[5], c);

    buttons[6] = new IButton("Chart");

    c.gridx = 0;
    c.gridy = 7;
    add(buttons[6], c);

    c.weighty = 1;
    c.gridx = 0;
    c.gridy = 8;
    c.anchor = GridBagConstraints.SOUTH;
    add(exitButton, c);
  }

  public JButton[] getButtons() {
    return buttons;
  }

  public JButton getExitButton() {
    return exitButton;
  }

  /**
   * This method restores the default style of the previously selected button and sets the newly
   * selected button to the active style.
   * 
   * @param index the index of the new selected button
   */
  public void onClick(int index) {
    if (index == currentButtonIndex) {
      return;
    }

    buttons[currentButtonIndex].setForeground(AppColor.DARK_GREY);
    buttons[currentButtonIndex].setBackground(AppColor.LIGHT_GREY);
    currentButtonIndex = index;
    buttons[currentButtonIndex].setForeground(AppColor.BLACK);
    buttons[currentButtonIndex].setBackground(AppColor.WHITE);
  }
}
