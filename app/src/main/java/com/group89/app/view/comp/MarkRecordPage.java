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
import javax.swing.JTable;
import com.group89.app.controller.Controller;
import com.group89.app.controller.MarkRecordPageController;

public class MarkRecordPage extends JPanel {
  private static final int LABEL_COUNT = 5;
  private JComboBox<String> semesterBox;
  private JButton queryButton;
  private JButton saveButton;
  private JLabel[] labels;
  private JScrollPane scrollPane;
  private JTable table;

  public MarkRecordPage() {
    super(new GridBagLayout());

    this.semesterBox = new JComboBox<String>();
    this.queryButton = new JButton("Query");
    this.saveButton = new JButton("Save");
    this.labels = new JLabel[LABEL_COUNT];
    this.labels[0] = new JLabel("Semester:");
    this.labels[1] = new JLabel("Modules Count: ");
    this.labels[2] = new JLabel("Total Credits: ");
    this.labels[3] = new JLabel("GPA: ");
    this.labels[4] = new JLabel("Average Mark: ");
    this.scrollPane = new JScrollPane();
    this.table = new JTable();

    GridBagConstraints c = new GridBagConstraints();

    // for labels and buttons
    c.weightx = 1;
    c.weighty = 0;

    c.insets.set(10, 10, 10, 10);

    this.semesterBox.setPreferredSize(new Dimension(100, 20));
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
    c.anchor = GridBagConstraints.WEST;
    c.fill = GridBagConstraints.NONE;
    this.add(this.semesterBox, c);

    c.insets.set(10, 0, 10, 10);

    this.queryButton.setPreferredSize(new Dimension(100, 20));
    c.gridx = 4;
    c.gridy = 0;
    c.anchor = GridBagConstraints.EAST;
    c.fill = GridBagConstraints.NONE;
    this.add(this.queryButton, c);

    c.insets.set(0, 10, 10, 10);

    for (JLabel label : this.labels) {
      label.setBorder(BorderFactory.createLineBorder(Color.RED));
      label.setPreferredSize(new Dimension(100, 20));
    }

    c.gridx = 0;
    c.gridy = 1;
    c.anchor = GridBagConstraints.WEST;
    c.fill = GridBagConstraints.HORIZONTAL;
    this.add(this.labels[0], c);

    c.insets.set(0, 0, 10, 10);

    c.gridx = 1;
    c.gridy = 1;
    c.anchor = GridBagConstraints.WEST;
    c.fill = GridBagConstraints.HORIZONTAL;
    this.add(this.labels[1], c);

    c.gridx = 2;
    c.gridy = 1;
    c.anchor = GridBagConstraints.WEST;
    c.fill = GridBagConstraints.HORIZONTAL;
    this.add(this.labels[2], c);

    c.gridx = 3;
    c.gridy = 1;
    c.anchor = GridBagConstraints.WEST;
    c.fill = GridBagConstraints.HORIZONTAL;
    this.add(this.labels[3], c);

    c.gridx = 4;
    c.gridy = 1;
    c.anchor = GridBagConstraints.WEST;
    c.fill = GridBagConstraints.HORIZONTAL;
    this.add(this.labels[4], c);

    c.insets.set(0, 10, 10, 10);

    // for scroll pane
    c.weightx = 1;
    c.weighty = 1;

    this.scrollPane.setBorder(BorderFactory.createLineBorder(Color.RED));
    c.gridx = 0;
    c.gridy = 2;
    c.gridwidth = 5;
    c.gridheight = 1;
    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.BOTH;
    this.add(this.scrollPane, c);

    // for save button
    c.weightx = 1;
    c.weighty = 0;

    this.saveButton.setPreferredSize(new Dimension(100, 20));
    this.saveButton.setEnabled(false);
    c.gridx = 2;
    c.gridy = 3;
    c.gridwidth = 1;
    c.gridheight = 1;
    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.NONE;
    this.add(this.saveButton, c);

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

  public JButton getSaveButton() {
    return this.saveButton;
  }

  public JScrollPane getScrollPane() {
    return this.scrollPane;
  }

  public JLabel[] getLabels() {
    return this.labels;
  }

  public JTable getTable() {
    return this.table;
  }
}
