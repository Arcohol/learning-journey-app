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
  class ILabel extends JLabel {
    public ILabel(String text) {
      super(text);
      this.setPreferredSize(new Dimension(100, 20));
    }
  }

  class SemesterBox extends JComboBox<String> {
    public SemesterBox() {
      super();
      this.setPreferredSize(new Dimension(100, 30));
      this.addItem("all");
      this.addItem("2020-2021-1");
      this.addItem("2020-2021-2");
      this.addItem("2021-2022-1");
      this.addItem("2021-2022-2");
      this.addItem("2022-2023-1");
      this.addItem("2022-2023-2");
      this.addItem("2023-2024-1");
      this.addItem("2023-2024-2");
    }
  }

  class IButton extends JButton {
    public IButton(String text) {
      super(text);
      this.setPreferredSize(new Dimension(100, 30));
    }
  }

  private static final int LABEL_COUNT = 5;

  private JComboBox<String> semesterBox;
  private JButton queryButton;
  private JButton saveButton;
  private JButton deleteButton;
  private JButton addButton;
  private JLabel[] labels;
  private JScrollPane scrollPane;
  private JTable table;

  public MarkRecordPage() {
    super(new GridBagLayout());

    this.semesterBox = new SemesterBox();
    this.queryButton = new IButton("Query");
    this.saveButton = new IButton("Save");
    this.deleteButton = new IButton("Delete");
    this.addButton = new IButton("Add");

    this.labels = new JLabel[LABEL_COUNT];
    this.labels[0] = new ILabel("Semester:");
    this.labels[1] = new ILabel("Modules Count: ");
    this.labels[2] = new ILabel("Total Credits: ");
    this.labels[3] = new ILabel("GPA: ");
    this.labels[4] = new ILabel("Average Mark: ");

    this.scrollPane = new JScrollPane();
    this.table = new JTable(null);

    GridBagConstraints c = new GridBagConstraints();

    c.weightx = 1;
    c.weighty = 0;

    c.insets.set(10, 10, 10, 10);

    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.WEST;
    c.fill = GridBagConstraints.NONE;
    this.add(this.semesterBox, c);

    c.insets.set(10, 0, 10, 10);

    c.gridx = 4;
    c.gridy = 0;
    c.anchor = GridBagConstraints.EAST;
    c.fill = GridBagConstraints.NONE;
    this.add(this.queryButton, c);

    c.insets.set(0, 10, 10, 10);

    c.anchor = GridBagConstraints.WEST;
    c.fill = GridBagConstraints.HORIZONTAL;

    c.gridx = 0;
    c.gridy = 1;
    this.add(this.labels[0], c);

    c.insets.set(0, 0, 10, 10);

    c.gridx = 1;
    c.gridy = 1;
    this.add(this.labels[1], c);

    c.gridx = 2;
    c.gridy = 1;
    this.add(this.labels[2], c);

    c.gridx = 3;
    c.gridy = 1;
    this.add(this.labels[3], c);

    c.gridx = 4;
    c.gridy = 1;
    this.add(this.labels[4], c);

    c.insets.set(0, 10, 10, 10);

    c.weightx = 1;
    c.weighty = 1;

    this.scrollPane.setBorder(BorderFactory.createLineBorder(Color.RED));
    this.scrollPane.setViewportView(this.table);
    c.gridx = 0;
    c.gridy = 2;
    c.gridwidth = 5;
    c.gridheight = 1;
    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.BOTH;
    this.add(this.scrollPane, c);

    c.weightx = 1;
    c.weighty = 0;

    this.saveButton.setEnabled(false);
    c.gridx = 2;
    c.gridy = 3;
    c.gridwidth = 1;
    c.gridheight = 1;
    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.NONE;
    this.add(this.saveButton, c);

    c.gridx = 4;
    c.gridy = 3;
    c.anchor = GridBagConstraints.EAST;
    c.fill = GridBagConstraints.NONE;
    this.add(this.deleteButton, c);

    c.gridx = 0;
    c.gridy = 3;
    c.anchor = GridBagConstraints.WEST;
    c.fill = GridBagConstraints.NONE;
    this.add(this.addButton, c);

    Controller markRecordPagController = new MarkRecordPageController(this);
    markRecordPagController.init();
  }

  public JComboBox<String> getSemesterBox() {
    return this.semesterBox;
  }

  public JButton getQueryButton() {
    return this.queryButton;
  }

  public JButton getSaveButton() {
    return this.saveButton;
  }

  public JButton getDeleteButton() {
    return this.deleteButton;
  }

  public JButton getAddButton() {
    return this.addButton;
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
