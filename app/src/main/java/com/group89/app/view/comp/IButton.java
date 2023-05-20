package com.group89.app.view.comp;

import java.awt.Dimension;
import javax.swing.JButton;
import com.group89.app.view.AppColor;

public class IButton extends JButton {
  private static final float FONT_SIZE = 16f;
  private static final Dimension BUTTON_SIZE = new Dimension(100, 30);

  public IButton(String text) {
    super(text);

    setFocusPainted(false);
    setPreferredSize(BUTTON_SIZE);
    setBackground(AppColor.LIGHT_GREY);
    setForeground(AppColor.BLACK);
    setFont(getFont().deriveFont(FONT_SIZE));
  }
}
