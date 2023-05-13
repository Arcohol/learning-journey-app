package com.group89.app.view.comp;

import javax.swing.JPanel;
import com.group89.app.controller.ChartPageController;

public class ChartPage extends JPanel {
  public ChartPage() {
    super();

    new ChartPageController(this);
  }
}
