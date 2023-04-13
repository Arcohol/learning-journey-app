package com.group89.app.view.comp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.group89.app.controller.Controller;
import com.group89.app.controller.MarkRecordPageController;

public class MarkRecordPage extends JPanel {
  private JComboBox<String> semesterBox;

  private JButton queryButton;

  private static final int LABEL_COUNT = 5;
  private JLabel[] labels;

  private JScrollPane scrollPane;

  public MarkRecordPage() {
    super(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.insets.set(10, 10, 10, 10);

    this.semesterBox = new JComboBox<String>();
    this.semesterBox.setPreferredSize(new Dimension(100, 30));
    this.semesterBox.addItem("all");
    this.semesterBox.addItem("2020-2021-1");
    this.semesterBox.addItem("2020-2021-2");
    this.semesterBox.addItem("2021-2022-1");
    this.semesterBox.addItem("2021-2022-2");
    this.semesterBox.addItem("2022-2023-1");
    this.semesterBox.addItem("2022-2023-2");
    this.semesterBox.addItem("2023-2024-1");
    this.semesterBox.addItem("2023-2024-2");

    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 0;
    c.weighty = 0;
    c.anchor = GridBagConstraints.WEST;
    c.fill = GridBagConstraints.NONE;
    this.add(this.semesterBox, c);

    this.queryButton = new JButton("Query");
    c.gridx = 4;
    c.gridy = 0;
    c.weightx = 1;
    c.weighty = 0;
    c.anchor = GridBagConstraints.EAST;
    c.fill = GridBagConstraints.NONE;
    this.add(this.queryButton, c);

    c.insets.set(0, 10, 10, 10);

    c.fill = GridBagConstraints.HORIZONTAL;

    this.labels = new JLabel[LABEL_COUNT];

    this.labels[0] = new JLabel("Semester:");
    c.gridx = 0;
    c.gridy = 1;
    c.weightx = 1;
    c.weighty = 0;
    c.anchor = GridBagConstraints.WEST;
    // c.fill = GridBagConstraints.NONE;
    this.add(this.labels[0], c);

    c.insets.set(0, 0, 10, 10);

    this.labels[1] = new JLabel("Modules Count: ");
    c.gridx = 1;
    c.gridy = 1;
    c.weightx = 1;
    c.weighty = 0;
    c.anchor = GridBagConstraints.WEST;
    // c.fill = GridBagConstraints.NONE;
    this.add(this.labels[1], c);

    this.labels[2] = new JLabel("Total Credits: ");
    c.gridx = 2;
    c.gridy = 1;
    c.weightx = 1;
    c.weighty = 0;
    c.anchor = GridBagConstraints.WEST;
    // c.fill = GridBagConstraints.NONE;
    this.add(this.labels[2], c);

    this.labels[3] = new JLabel("GPA: ");
    c.gridx = 3;
    c.gridy = 1;
    c.weightx = 1;
    c.weighty = 0;
    c.anchor = GridBagConstraints.WEST;
    // c.fill = GridBagConstraints.NONE;
    this.add(this.labels[3], c);

    this.labels[4] = new JLabel("Average Mark: ");
    c.gridx = 4;
    c.gridy = 1;
    c.weightx = 1;
    c.weighty = 0;
    c.anchor = GridBagConstraints.WEST;
    // c.fill = GridBagConstraints.NONE;
    this.add(this.labels[4], c);

    // testing border for labels
    for (JLabel label : this.labels) {
      label.setBorder(BorderFactory.createLineBorder(Color.RED));
      label.setPreferredSize(new Dimension(100, 20));
    }

    this.scrollPane = new JScrollPane();
    this.scrollPane.setBorder(BorderFactory.createLineBorder(Color.RED));
    c.insets.set(0, 10, 10, 10);
    c.gridx = 0;
    c.gridy = 2;
    c.gridwidth = 5;
    c.gridheight = 1;
    c.weightx = 1;
    c.weighty = 1;
    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.BOTH;
    this.add(this.scrollPane, c);

    this.setVisible(true);

    Controller markRecordPagController = new MarkRecordPageController(this);
    markRecordPagController.init();
  }

  // getters
  public JComboBox<String> getSemesterBox() {
    return this.semesterBox;
  }

  public JButton getQueryButton() {
    return this.queryButton;
  }

  public JScrollPane getScrollPane() {
    return this.scrollPane;
  }

  public JLabel[] getLabels() {
    return this.labels;
  }
}
