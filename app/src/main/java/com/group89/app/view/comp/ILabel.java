package com.group89.app.view.comp;

import java.awt.Dimension;
import javax.swing.JLabel;

public class ILabel extends JLabel {
  private static final float FONT_SIZE = 16f;
  private static final Dimension LABEL_SIZE = new Dimension(150, 30);
  
  public ILabel(String text) {
    super(text);

    setPreferredSize(LABEL_SIZE);
    setFont(getFont().deriveFont(FONT_SIZE));
    setHorizontalAlignment(JLabel.CENTER);
  }
}
