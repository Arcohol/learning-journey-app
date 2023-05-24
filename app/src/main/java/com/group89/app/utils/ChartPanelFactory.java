package com.group89.app.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;
import com.group89.app.model.CourseType;
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
    renderer.setSeriesStroke(0, new BasicStroke(3.0f));
    renderer.setSeriesItemLabelsVisible(0, true);
    renderer.setSeriesItemLabelGenerator(0, new StandardCategoryItemLabelGenerator());
    chart.getCategoryPlot().setRenderer(renderer);


    ChartPanel panel = new ChartPanel(chart);
    // set the panel small enough so that it can be automatically resized
    panel.setPreferredSize(new Dimension(0, 0));

    return panel;
  }

  public static ChartPanel getCourseComposition() {
    // creates a pie chart containing different typs of courses
    JsonConverter<MarkRecord> jsonConverter = new JsonConverter<>("marks.json", MarkRecord[].class);
    List<MarkRecord> marks = jsonConverter.toArrayList();

    if (marks == null) {
      return null;
    }

    HashMap<CourseType, Integer> courseTypeCount = new HashMap<>();
    for (MarkRecord mark : marks) {
      if (!courseTypeCount.containsKey(mark.getType())) {
        courseTypeCount.put(mark.getType(), 0);
      }
      courseTypeCount.put(mark.getType(), courseTypeCount.get(mark.getType()) + 1);
    }

    DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
    for (CourseType type : courseTypeCount.keySet()) {
      dataset.setValue(type.toString(), courseTypeCount.get(type));
    }

    JFreeChart chart = ChartFactory.createPieChart("Course Composition", dataset);
    chart.getPlot().setBackgroundPaint(Color.WHITE);

    // show the percentage of each course type
    // set label format to "Course Type: Percentage"
    PieSectionLabelGenerator g = new StandardPieSectionLabelGenerator("{0}: {2}");
    ((PiePlot<?>) chart.getPlot()).setLabelGenerator(g);

    ChartPanel panel = new ChartPanel(chart);
    panel.setPreferredSize(new Dimension(0, 0));

    return panel;
  }

  public static ChartPanel getMarkDistribution() {
    JsonConverter<MarkRecord> converter = new JsonConverter<>("marks.json", MarkRecord[].class);
    List<MarkRecord> list = converter.toArrayList();

    HistogramDataset dataset = new HistogramDataset();
    double[] values = new double[list.size()];
    double max = 0, min = 0;
    for (int i = 0; i < list.size(); i++) {
      values[i] = list.get(i).getMarkCN();
      if (i == 0) {
        max = values[i];
        min = values[i];
      } else {
        if (values[i] > max) {
          max = values[i];
        }
        if (values[i] < min) {
          min = values[i];
        }
      }
    }
    dataset.addSeries("Marks", values, (int) (max - min + 1), min, max + 1);

    JFreeChart chart = ChartFactory.createHistogram("Distribution of Marks", "Mark Range",
        "Number of Courses", dataset, PlotOrientation.VERTICAL, false, false, false);

    XYPlot plot = (XYPlot) chart.getPlot();
    plot.setBackgroundPaint(Color.WHITE);
    plot.setRangeGridlinePaint(Color.BLACK);

    NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

    XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
    renderer.setBarPainter(new StandardXYBarPainter());

    ChartPanel panel = new ChartPanel(chart);
    panel.setPreferredSize(new Dimension(0, 0));

    return panel;
  }
}
