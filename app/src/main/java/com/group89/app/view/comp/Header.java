package com.group89.app.view.comp;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import com.group89.app.view.AppColor;

/**
 * A JPanel with a fixed background color.
 */
public class Header extends JPanel {
  public Header() {
    super(new GridBagLayout());
    setBackground(AppColor.WHITE);
  }
}
