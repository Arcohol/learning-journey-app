package com.group89.app.controller;

import javax.swing.JLabel;
import com.group89.app.model.MarkRecord;
import com.group89.app.model.MarkRecordList;
import com.group89.app.model.MarkRecordTableModel;
import com.group89.app.utils.JsonConverter;
import com.group89.app.view.comp.MarkRecordPage;

public class MarkRecordPageController implements Controller {
  private MarkRecordPage page;

  public MarkRecordPageController(MarkRecordPage page) {
    this.page = page;
  }

  @Override
  public void init() {
    this.page.getQueryButton().addActionListener(e -> this.query());
    this.page.getSaveButton().addActionListener(e -> this.save());
  }

  private void query() {
    // get semester from semesterBox
    String semester = (String) this.page.getSemesterBox().getSelectedItem();

    // get mark records
    JsonConverter<MarkRecord> converter = new JsonConverter<>("marks.json", MarkRecord[].class);
    MarkRecordList records = new MarkRecordList(converter.toList());

    // get labels
    JLabel[] labels = this.page.getLabels();

    // filter records
    if (!semester.equals("all")) {
      records.removeIf(record -> !record.getSemester().equals(semester));
    }

    // update table
    MarkRecordTableModel tableModel = new MarkRecordTableModel(records);

    // enable save button when table is changed
    tableModel.addTableModelListener(e -> this.page.getSaveButton().setEnabled(true));

    this.page.getTable().setModel(tableModel);
    this.page.getScrollPane().setViewportView(this.page.getTable());

    // update labels
    labels[0].setText("Semester: " + semester);
    labels[1].setText("Module Count: " + records.size());
    labels[2].setText("Total Credits: " + records.getTotalCredits());
    labels[3].setText("GPA: " + records.getGPA());
    labels[4].setText("Average Mark: " + records.getAverageMark());
  }

  private void save() {
    MarkRecordTableModel tableModel = (MarkRecordTableModel) this.page.getTable().getModel();
    JsonConverter<MarkRecord> converter = new JsonConverter<>("marks.json", MarkRecord[].class);
    converter.toFile(tableModel.getMarkRecordList());
    this.page.getSaveButton().setEnabled(false);
  }
}
