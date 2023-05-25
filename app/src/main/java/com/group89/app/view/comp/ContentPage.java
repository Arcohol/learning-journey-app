package com.group89.app.view.comp;

import javax.swing.JPanel;
import java.awt.CardLayout;

/**
 * A custom {@code JPanel} class for displaying content.
 */
public class ContentPage extends JPanel {
  /**
   * Constructs a page that uses a {@code CardLayout}.
   */
  public ContentPage() {
    super(new CardLayout());
  }
}
