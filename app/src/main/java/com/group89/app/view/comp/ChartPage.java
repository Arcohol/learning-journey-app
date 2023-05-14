package com.group89.app.view.comp;

import com.group89.app.controller.ChartPageController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChartPage extends JPanel {
  private JComboBox<String> chartComboBox;
  private JButton queryButton;
  private JLabel selectedChartLabel;
  private JPanel chartPanel;
  private JPanel topPanel;

  public ChartPage() {
    // Create the components
    chartComboBox = new JComboBox<>(new String[]{"Distribution of Marks", "Course Type Composition", "AverageTrend"});
    queryButton = new JButton("Query");
    selectedChartLabel = new JLabel("Selected Chart:");

    // Create a panel for the top section
    topPanel = new JPanel();
    topPanel.add(selectedChartLabel);
    topPanel.add(chartComboBox);
    topPanel.add(queryButton);
    topPanel.setBorder(new EmptyBorder(new Insets(10,10,55,10)));

    // Create a panel for the chart area
    chartPanel = new JPanel();

    // Set up the layout
    setLayout(new BorderLayout());
    add(topPanel, BorderLayout.NORTH);
    add(chartPanel, BorderLayout.CENTER);

    setVisible(true);
    new ChartPageController(this);
  }

  public JComboBox<String> getChartComboBox() {
    return chartComboBox;
  }

  public void setChartComboBox(JComboBox<String> chartComboBox) {
    this.chartComboBox = chartComboBox;
  }

  public JButton getQueryButton() {
    return queryButton;
  }

  public void setQueryButton(JButton queryButton) {
    this.queryButton = queryButton;
  }

  public JLabel getSelectedChartLabel() {
    return selectedChartLabel;
  }

  public void setSelectedChartLabel(JLabel selectedChartLabel) {
    this.selectedChartLabel = selectedChartLabel;
  }

  public JPanel getChartPanel() {
    return chartPanel;
  }

  public void setChartPanel(JPanel chartPanel) {
    this.chartPanel = chartPanel;
  }

  public JPanel getTopPanel() {
    return topPanel;
  }

  public void setTopPanel(JPanel topPanel) {
    this.topPanel = topPanel;
  }
}
