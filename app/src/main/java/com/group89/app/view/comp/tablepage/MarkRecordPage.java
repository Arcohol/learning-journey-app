package com.group89.app.view.comp.tablepage;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.group89.app.controller.MarkRecordPageController;
import com.group89.app.model.CourseType;
import com.group89.app.model.SemesterList;
import com.group89.app.view.comp.Header;
import com.group89.app.view.comp.IComboBox;
import com.group89.app.view.comp.ILabel;

public class MarkRecordPage extends DefaultTablePage {
  public static final String[] SCALES = {"BOTH", "CN", "UK"};

  private static final int LABEL_COUNT = 5;

  private JComboBox<String> semesterBox, scaleBox;
  private JComboBox<CourseType> typeBox;
  private JLabel[] labels;

  public MarkRecordPage() {
    super();

    semesterBox = new IComboBox<>(new SemesterList(true).toArray());
    scaleBox = new IComboBox<>(SCALES);
    typeBox = new IComboBox<>(CourseType.values());

    labels = new JLabel[LABEL_COUNT];
    labels[0] = new ILabel("Semester:");
    labels[1] = new ILabel("Modules Count: ");
    labels[2] = new ILabel("Total Credits: ");
    labels[3] = new ILabel("GPA: ");
    labels[4] = new ILabel("Average Mark: ");

    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.WEST;

    c.gridx = 0;
    c.gridy = 0;
    getHeader().add(semesterBox, c);

    c.insets.set(0, 10, 0, 0);

    c.gridx = 1;
    c.gridy = 0;
    getHeader().add(scaleBox, c);

    c.gridx = 2;
    c.gridy = 0;
    c.weightx = 1;
    getHeader().add(typeBox, c);

    JPanel labelPanel = new Header();
    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.insets.set(0, 0, 0, 0);
    c.weightx = 1;

    c.gridx = 0;
    c.gridy = 0;
    labelPanel.add(labels[0], c);

    c.gridx = 1;
    c.gridy = 0;
    labelPanel.add(labels[1], c);

    c.gridx = 2;
    c.gridy = 0;
    labelPanel.add(labels[2], c);

    c.gridx = 3;
    c.gridy = 0;
    labelPanel.add(labels[3], c);

    c.gridx = 4;
    c.gridy = 0;
    labelPanel.add(labels[4], c);

    c.insets.set(5, 0, 0, 0);
    c.gridx = 0;
    c.gridy = 1;
    c.gridwidth = 3;
    c.weightx = 1;
    c.weighty = 0;
    c.fill = GridBagConstraints.HORIZONTAL;
    getHeader().add(labelPanel, c);

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

  public JLabel[] getLabels() {
    return labels;
  }
}
