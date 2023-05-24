package com.group89.app.utils;

import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import com.group89.app.model.entity.MarkRecord;

public class ChartPanelFactory {
  public static ChartPanel getAverageTrend() {
    JsonConverter<MarkRecord> jsonConverter = new JsonConverter<>("marks.json", MarkRecord[].class);
    List<MarkRecord> marks = jsonConverter.toArrayList();

    if (marks == null) {
      return null;
    }

    HashMap<String, List<Double>> weightedMarks = new HashMap<>();
    HashMap<String, Double> totalCredits = new HashMap<>();

    // calculate the weighted marks and total credits for each semester
    for (MarkRecord mark : marks) {
      if (!weightedMarks.containsKey(mark.getSemester())) {
        LinkedList<Double> marksInSemester = new LinkedList<>();
        marksInSemester.add(0.0);
        weightedMarks.put(mark.getSemester(), marksInSemester);
      }

      if (!totalCredits.containsKey(mark.getSemester())) {
        totalCredits.put(mark.getSemester(), 0.0);
      }

      List<Double> marksInSemester = weightedMarks.get(mark.getSemester());
      marksInSemester.add(mark.getMarkCN() * mark.getCreditsCN());

      totalCredits.put(mark.getSemester(),
          totalCredits.get(mark.getSemester()) + mark.getCreditsCN());;
    }

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    LinkedList<String> semesters = new LinkedList<>(weightedMarks.keySet());
    Collections.sort(semesters);

    // calculate the average mark for each semester
    for (String semester : semesters) {
      List<Double> marksInSemester = weightedMarks.get(semester);
      double sum = 0;
      for (Double mark : marksInSemester) {
        sum += mark;
      }
      dataset.addValue(sum / totalCredits.get(semester), "Average Mark", semester);
    }

    // return a JFreeChart using the dataset
    JFreeChart chart =
        ChartFactory.createLineChart("Average Mark Trend", "Semester", null, dataset);
    chart.getPlot().setBackgroundPaint(Color.WHITE);

    // make a renderer that has thicker lines and item labels
    LineAndShapeRenderer renderer = new LineAndShapeRenderer();
    renderer.setSeriesShapesVisible(0, true);
    renderer.setSeriesShapesFilled(0, true);
    renderer.setSeriesStroke(0, new java.awt.BasicStroke(3.0f));
    renderer.setSeriesItemLabelsVisible(0, true);
    renderer.setSeriesItemLabelGenerator(0, new StandardCategoryItemLabelGenerator());
    chart.getCategoryPlot().setRenderer(renderer);


    ChartPanel panel = new ChartPanel(chart);
    // set the panel small enough so that it can be automatically resized
    panel.setPreferredSize(new java.awt.Dimension(0, 0));

    return panel;
  }
}
