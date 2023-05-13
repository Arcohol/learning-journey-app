package com.group89.app.controller;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;
import com.group89.app.model.MarkRecord;
import com.group89.app.utils.JsonConverter;
import com.group89.app.view.comp.ChartPage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChartPageController {
    private ChartPage view;

    public ChartPageController(ChartPage page) {
        view = page;

        queryAverageTrend();
        query();
    }

    private void queryAverageTrend() {
        //read the mark out of the json file
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("marks.json"))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String jsonStr = stringBuilder.toString();
            JSONArray jsonArray = new JSONArray(jsonStr);

            Map<String, double[]> semesterObject = new HashMap<>();
            //calculate each semester's average mark
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String semester = jsonObject.getString("semester");
                double mark = jsonObject.getDouble("markCN");
                double credits = jsonObject.getDouble("creditsCN");

                if (!semesterObject.containsKey(semester)) {
                    semesterObject.put(semester, new double[]{0, 0});
                }
                double[] semesterData = semesterObject.get(semester);
                semesterData[0] += mark * credits;
                semesterData[1] += credits;
            }
            //sort semester on the x-axis
            List<String> sortedSemesters = new ArrayList<>(semesterObject.keySet());
            Collections.sort(sortedSemesters);
            for (int i = 0; i < sortedSemesters.size(); i++) {
                System.out.println(sortedSemesters.get(i));
            }
            //add value onto dataset
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (String semester : sortedSemesters) {
                double[] semesterData = semesterObject.get(semester);
                double gpa = semesterData[0] / semesterData[1];
                dataset.addValue(gpa, "Average", semester);
            }

            JFreeChart chart = ChartFactory.createLineChart(
                    "Average Trend",
                    "Semester",
                    "Average",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );

            CategoryPlot plot = chart.getCategoryPlot();
            //customize chart
            LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
            renderer.setSeriesPaint(0, new Color(122, 144, 167));
            renderer.setSeriesStroke(0, new java.awt.BasicStroke(2.0f));
            renderer.setSeriesShapesVisible(0, true);
            renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-2.0, -2.0, 4.0, 4.0));

            plot.getRangeAxis().setLowerBound(50);

            ChartPanel chartPanel = new ChartPanel(chart);

            view.add(chartPanel);
        } catch (IOException | JSONException e) {
        }
    }

  private void query() {
    JsonConverter<MarkRecord> converter;
    ArrayList<MarkRecord> list;
    converter = new JsonConverter<>("marks.json", MarkRecord[].class);
    list = converter.toArrayList();

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
    plot.setBackgroundPaint(Color.white);
    plot.setRangeGridlinePaint(Color.black);
    NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
    // renderer.setDrawBarOutline(false);
    renderer.setBarPainter(new StandardXYBarPainter());

    ChartPanel chartPanel = new ChartPanel(chart, false);
    view.add(chartPanel);
  }
}
