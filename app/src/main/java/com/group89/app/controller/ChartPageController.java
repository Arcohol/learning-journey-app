package com.group89.app.controller;

import javax.swing.JButton;
import com.group89.app.utils.ChartPanelFactory;
import com.group89.app.view.comp.tablepage.ChartPage;

public class ChartPageController {
  private ChartPage view;

  public ChartPageController(ChartPage view) {
    this.view = view;

    init();
  }

  private void init() {
    JButton[] buttons = view.getButtons();
    buttons[0].addActionListener(e -> {
      view.setChart(ChartPanelFactory.getAverageTrend());
    });
    buttons[1].addActionListener(e -> {
      view.setChart(ChartPanelFactory.getCourseComposition());
    });
    buttons[2].addActionListener(e -> {
      view.setChart(ChartPanelFactory.getMarkDistribution());
    });
  }
}
