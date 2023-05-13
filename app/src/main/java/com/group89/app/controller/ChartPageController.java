package com.group89.app.controller;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import com.group89.app.view.comp.ChartPage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChartPageController {
    private ChartPage view;

    public ChartPageController(ChartPage page) {
        view = page;

        queryAverageTrend();
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
            renderer.setSeriesShapesVisible(0, true); // 显示黑点
            renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-2.0, -2.0, 4.0, 4.0)); // 设置黑点大小

            plot.getRangeAxis().setLowerBound(50);

            ChartPanel chartPanel = new ChartPanel(chart);

            view.add(chartPanel);
        } catch (IOException | JSONException e) {
        }
    }
}
