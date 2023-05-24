package com.group89.app.view.comp;

import java.awt.Dimension;
import javax.swing.JComboBox;
import com.group89.app.view.AppColor;

public class IComboBox<E> extends JComboBox<E> {
  private static final float FONT_SIZE = 16f;
  private static final Dimension SIZE = new Dimension(150, 30);

  public IComboBox(E[] items) {
    super(items);

    setBackground(AppColor.WHITE);
    setForeground(AppColor.BLACK);
    setFont(getFont().deriveFont(FONT_SIZE));
    setPreferredSize(SIZE);
  }
}
