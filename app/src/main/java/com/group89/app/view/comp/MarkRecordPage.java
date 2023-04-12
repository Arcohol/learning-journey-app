package com.group89.app.view.comp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.group89.app.controller.Controller;
import com.group89.app.controller.MarkRecordPageController;

public class MarkRecordPage extends JPanel {
  // for now, there is only one option for filtering.
  private JComboBox<String> semesterBox;

  private JButton queryButton;

  // scrollable table
  private JScrollPane scrollPane;

  public MarkRecordPage() {
    super(new GridBagLayout());

    this.semesterBox = new JComboBox<String>();
    this.semesterBox.setPreferredSize(new Dimension(100, 30));
    this.semesterBox.addItem("2020-2021-1");
    this.semesterBox.addItem("2020-2021-2");
    this.semesterBox.addItem("2021-2022-1");
    this.semesterBox.addItem("2021-2022-2");
    this.semesterBox.addItem("2022-2023-1");
    this.semesterBox.addItem("2022-2023-2");
    this.semesterBox.addItem("2023-2024-1");
    this.semesterBox.addItem("2023-2024-2");

    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.CENTER;

    c.insets.set(10, 10, 10, 10);

    c.gridx = 0;
    this.add(this.semesterBox, c);

    this.queryButton = new JButton("Query");
    c.gridx = 1;
    c.weightx = 1;
    c.anchor = GridBagConstraints.EAST;
    this.add(this.queryButton, c);

    this.scrollPane = new JScrollPane(new MarkRecordTable());
    // testing border
    this.scrollPane.setBorder(BorderFactory.createLineBorder(Color.RED));
    // remove insets
    c.insets.set(0, 10, 10, 10);
    c.gridx = 0;
    c.gridy = 1;
    c.gridwidth = 2;
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    c.anchor = GridBagConstraints.CENTER;
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
}


class MarkRecordTable extends JTable {
  public MarkRecordTable() {
    super();
  }
}
