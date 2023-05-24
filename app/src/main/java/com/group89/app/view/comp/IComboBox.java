package com.group89.app.view.comp;

import java.awt.Dimension;
import javax.swing.JComboBox;
import com.group89.app.view.AppColor;

/**
 * A JComboBox with a fixed size and font size.
 * 
 * @param <E> the type of the items in the combo box
 */
public class IComboBox<E> extends JComboBox<E> {
  private static final float FONT_SIZE = 16f;
  private static final Dimension SIZE = new Dimension(150, 30);

  /**
   * Constructs a new IComboBox with the given items.
   * 
   * @param items the items in the combo box
   */
  public IComboBox(E[] items) {
    super(items);

    setBackground(AppColor.WHITE);
    setForeground(AppColor.BLACK);
    setFont(getFont().deriveFont(FONT_SIZE));
    setPreferredSize(SIZE);
  }
}
