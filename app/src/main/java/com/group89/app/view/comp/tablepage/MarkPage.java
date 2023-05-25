package com.group89.app.view.comp.tablepage;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.group89.app.controller.MarkPageController;
import com.group89.app.model.enumeration.ComboBoxItem;
import com.group89.app.model.enumeration.CourseType;
import com.group89.app.model.enumeration.Semester;
import com.group89.app.utils.EnumFactory;
import com.group89.app.view.comp.ILabel;
import com.group89.app.view.comp.IComboBox;
import com.group89.app.view.comp.Header;

/**
 * A table page for the mark table.
 * 
 * @see DefaultTablePage
 */
public class MarkPage extends DefaultTablePage {
  public static final String[] SCALES = {"Both", "CN", "UK"};

  /**
   * The number of labels.
   */
  private static final int LABEL_COUNT = 5;

  private JComboBox<String> scaleBox;
  private JComboBox<ComboBoxItem> semesterBox, typeBox;
  private JLabel[] labels;

  public MarkPage() {
    super();

    semesterBox = new IComboBox<>(EnumFactory.addItemAll(Semester.values()));
    scaleBox = new IComboBox<>(SCALES);
    typeBox = new IComboBox<>(EnumFactory.addItemAll(CourseType.values()));

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

    new MarkPageController(this);
  }

  /**
   * Returns the semester combo box.
   * 
   * @return the semester combo box
   */
  public JComboBox<ComboBoxItem> getSemesterBox() {
    return semesterBox;
  }

  /**
   * Returns the scale combo box.
   * 
   * @return the scale combo box
   */
  public JComboBox<String> getScaleBox() {
    return scaleBox;
  }

  /**
   * Returns the type combo box.
   * 
   * @return the type combo box
   */
  public JComboBox<ComboBoxItem> getTypeBox() {
    return typeBox;
  }

  /**
   * Returns the labels.
   * 
   * @return the labels
   */
  public JLabel[] getLabels() {
    return labels;
  }
}
