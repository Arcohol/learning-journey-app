package com.group89.app.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
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
import com.group89.app.model.entity.Mark;
import com.group89.app.model.enumeration.CourseType;
import com.group89.app.model.enumeration.Semester;

/**
 * A factory class that creates different charts.
 */
public class ChartPanelFactory {

  /**
   * Creates a chart panel that shows the average mark trend.
   * 
   * @return a chart panel
   */
  public static ChartPanel getAverageTrend() {
    JsonConverter<Mark> jsonConverter = new JsonConverter<>("marks.json", Mark[].class);
    List<Mark> marks = jsonConverter.toArrayList();

    if (marks == null) {
      return null;
    }

    Map<Semester, Double> averageMarks = marks.stream()
        .collect(Collectors.groupingBy(Mark::getSemester,
            Collectors.teeing(Collectors.summingDouble(m -> m.getMarkCN() * m.getCreditsCN()),
                Collectors.summingDouble(m -> m.getCreditsCN()),
                (weighted, credits) -> weighted / credits)));

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    marks.stream().map(Mark::getSemester).distinct().sorted()
        .forEach(s -> dataset.addValue(averageMarks.get(s), "Average Mark", s));

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

  /**
   * Creates a chart panel that shows the course composition.
   * 
   * @return a chart panel
   */
  public static ChartPanel getCourseComposition() {
    // creates a pie chart containing different typs of courses
    JsonConverter<Mark> jsonConverter = new JsonConverter<>("marks.json", Mark[].class);
    List<Mark> marks = jsonConverter.toArrayList();

    if (marks == null) {
      return null;
    }

    Map<CourseType, Integer> courseTypeCount =
        marks.stream().collect(Collectors.groupingBy(Mark::getType, Collectors.summingInt(m -> 1)));

    DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
    marks.stream().map(Mark::getType).distinct().sorted()
        .forEach(t -> dataset.setValue(t.toString(), courseTypeCount.get(t)));

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

  /**
   * Creates a chart panel that shows the distribution of marks.
   * 
   * @return a chart panel
   */
  public static ChartPanel getMarkDistribution() {
    JsonConverter<Mark> converter = new JsonConverter<>("marks.json", Mark[].class);
    List<Mark> list = converter.toArrayList();

    DoubleStream.Builder builder = DoubleStream.builder();
    DoubleStream stream = list.stream().mapToDouble(Mark::getMarkCN);
    double[] values = stream.peek(builder).toArray();
    stream = builder.build();
    builder = DoubleStream.builder();
    double max = stream.peek(builder).max().orElse(0);
    stream = builder.build();
    double min = stream.min().orElse(0);

    HistogramDataset dataset = new HistogramDataset();
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
