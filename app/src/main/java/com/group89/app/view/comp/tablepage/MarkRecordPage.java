package com.group89.app.view.comp.tablepage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import com.group89.app.controller.MarkRecordPageController;
import com.group89.app.model.CourseType;
import com.group89.app.view.comp.IButton;
import com.group89.app.view.comp.ILabel;
import com.group89.app.view.comp.ITable;
import com.group89.app.view.comp.MyComboBox;

public class MarkRecordPage extends AbstractTablePage {
  public static final String[] SEMESTERS = {"all", "2020-2021-1", "2020-2021-2", "2021-2022-1",
      "2021-2022-2", "2022-2023-1", "2022-2023-2", "2023-2024-1", "2023-2024-2"};
  public static final String[] SCALES = {"BOTH", "CN", "UK"};

  private static final int LABEL_COUNT = 5;

  private JComboBox<String> semesterBox, scaleBox;
  private JComboBox<CourseType> typeBox;
  private JButton queryButton;
  private JLabel[] labels;

  public MarkRecordPage() {
    super();

    setLayout(new GridBagLayout());

    semesterBox = new MyComboBox<>(SEMESTERS);
    scaleBox = new MyComboBox<>(SCALES);
    typeBox = new MyComboBox<>(CourseType.values());

    queryButton = new IButton("Query");
    saveButton = new IButton("Save");
    deleteButton = new IButton("Delete");
    addButton = new IButton("Add");

    labels = new JLabel[LABEL_COUNT];
    labels[0] = new ILabel("Semester:");
    labels[1] = new ILabel("Modules Count: ");
    labels[2] = new ILabel("Total Credits: ");
    labels[3] = new ILabel("GPA: ");
    labels[4] = new ILabel("Average Mark: ");

    scrollPane = new JScrollPane();
    table = new ITable();

    GridBagConstraints c = new GridBagConstraints();

    c.insets.set(10, 10, 0, 0);

    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.WEST;
    add(semesterBox, c);

    c.gridx = 1;
    c.gridy = 0;
    c.anchor = GridBagConstraints.WEST;
    add(scaleBox, c);

    c.gridx = 2;
    c.gridy = 0;
    c.anchor = GridBagConstraints.WEST;
    add(typeBox, c);

    c.insets.set(10, 0, 0, 10);

    c.gridx = 4;
    c.gridy = 0;
    c.anchor = GridBagConstraints.EAST;
    add(queryButton, c);

    c.weightx = 1;

    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.BOTH;

    c.gridx = 0;
    c.gridy = 1;
    add(labels[0], c);


    c.gridx = 1;
    c.gridy = 1;
    add(labels[1], c);

    c.gridx = 2;
    c.gridy = 1;
    add(labels[2], c);

    c.gridx = 3;
    c.gridy = 1;
    add(labels[3], c);

    c.insets.set(10, 10, 0, 10);

    c.gridx = 4;
    c.gridy = 1;
    add(labels[4], c);

    c.weightx = 1;
    c.weighty = 1;

    c.insets.set(10, 10, 10, 10);

    scrollPane.setViewportView(table);
    c.gridx = 0;
    c.gridy = 2;
    c.gridwidth = 5;
    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.BOTH;
    add(scrollPane, c);

    c.weightx = 1;
    c.weighty = 0;
    c.gridwidth = 1;

    c.insets.set(0, 10, 10, 10);

    c.gridx = 0;
    c.gridy = 3;
    c.anchor = GridBagConstraints.WEST;
    c.fill = GridBagConstraints.NONE;
    add(addButton, c);

    saveButton.setEnabled(false);
    c.gridx = 2;
    c.gridy = 3;
    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.NONE;
    add(saveButton, c);

    c.gridx = 4;
    c.gridy = 3;
    c.anchor = GridBagConstraints.EAST;
    c.fill = GridBagConstraints.NONE;
    add(deleteButton, c);

    new MarkRecordPageController(this);
  }

  public JComboBox<String> getSemesterBox() {
    return semesterBox;
  }

  public JComboBox<String> getScaleBox() {
    return scaleBox;
  }

  public JComboBox<CourseType> getTypeBox() {
    return typeBox;
  }

  public JButton getQueryButton() {
    return queryButton;
  }

  public JLabel[] getLabels() {
    return labels;
  }
}
