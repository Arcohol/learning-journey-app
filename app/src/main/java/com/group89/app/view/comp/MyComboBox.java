package com.group89.app.view.comp;

import javax.swing.JComboBox;

public class MyComboBox<E> extends JComboBox<E> {
  private static final float FONT_SIZE = 16f;

  public MyComboBox(E items[]) {
    super(items);

    setBackground(java.awt.Color.WHITE);
    setForeground(java.awt.Color.BLACK);
    setFont(getFont().deriveFont(FONT_SIZE));
    setPreferredSize(new java.awt.Dimension(150, 30));
  }
}
