package com.group89.app.utils;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import com.group89.app.model.entity.MarkRecord;

public class ChartGenerator {
  public static ChartPanel getAverageTrend() {
    JsonConverter<MarkRecord> jsonConverter = new JsonConverter<>("marks.json", MarkRecord[].class);
    List<MarkRecord> marks = jsonConverter.toArrayList();

    if (marks == null) {
      return null;
    }

    // calculate average mark for each semester
    // draw a line chart
    // return the chart

    HashMap<String, List<Double>> weightedMarks = new HashMap<>();

    for (MarkRecord mark : marks) {
      if (!weightedMarks.containsKey(mark.getSemester())) {
        LinkedList<Double> marksInSemester = new LinkedList<>();
        marksInSemester.add(0.0);
        weightedMarks.put(mark.getSemester(), marksInSemester);
      }
      List<Double> marksInSemester = weightedMarks.get(mark.getSemester());
      marksInSemester.add(mark.getMarkCN() * mark.getCreditsCN());
      marksInSemester.set(0, marksInSemester.get(0) + mark.getCreditsCN());
    }


    for (String semester : weightedMarks.keySet()) {
      double sum = 0;
      List<Double> marksInSemester = weightedMarks.get(semester);
      for (Double mark : marksInSemester) {
        sum += mark;
      }
      marksInSemester.set(0, sum / marksInSemester.get(0));
    }

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    for (String semester : weightedMarks.keySet()) {
      List<Double> marksInSemester = weightedMarks.get(semester);
      dataset.addValue(marksInSemester.get(0), "Average Mark", semester);
    }

    // return a JFreeChart using the dataset
    JFreeChart chart =
        ChartFactory.createLineChart("Average Mark Trend", "Semester", null, dataset);
    chart.getPlot().setBackgroundPaint(Color.WHITE);
    // make the line thicker
    chart.getCategoryPlot().getRenderer().setSeriesStroke(0, new java.awt.BasicStroke(3.0f));

    ChartPanel panel = new ChartPanel(chart);
    // set the panel small enough so that it can be automatically resized
    panel.setPreferredSize(new java.awt.Dimension(0, 0));

    return panel;
  }
}
