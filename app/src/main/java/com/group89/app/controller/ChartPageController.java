package com.group89.app.controller;

import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import com.group89.app.model.MarkRecord;
import com.group89.app.utils.JsonConverter;
import com.group89.app.view.comp.ChartPage;

public class ChartPageController {
  private ChartPage view;

  public ChartPageController(ChartPage page) {
    view = page;

    query();
  }

  private void query() {
    JsonConverter<MarkRecord> converter;
    ArrayList<MarkRecord> list;
    converter = new JsonConverter<>("marks.json", MarkRecord[].class);
    list = converter.toArrayList();

    int[] num = new int[101];
    for (MarkRecord record : list) {
      num[record.getMarkCN()]++;
    }

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    // draw chart illustrating the distribution of marks
    for (int i = 0; i < 101; i++) {
      dataset.addValue(num[i], "Marks", String.valueOf(i));
    }

    JFreeChart chart = ChartFactory.createBarChart("Demo", // chart title
        "Category", // domain axis label
        "Value", // range axis label
        dataset, // data
        PlotOrientation.VERTICAL, // orientation
        true, // include legend
        true, // tooltips?
        false // URLs?
    );

    ChartPanel chartPanel = new ChartPanel(chart, false);
    view.add(chartPanel);

  }
}
