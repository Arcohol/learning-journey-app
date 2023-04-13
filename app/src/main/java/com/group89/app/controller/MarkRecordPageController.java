package com.group89.app.controller;

import javax.swing.JTable;
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
  }

  private void query() {
    // get semester from semesterBox
    String semester = (String) this.page.getSemesterBox().getSelectedItem();

    // get mark records
    JsonConverter<MarkRecord> converter = new JsonConverter<>("marks.json", MarkRecord[].class);
    MarkRecordList records = new MarkRecordList(converter.toList());

    // filter records
    if (!semester.equals("all")) {
      records.removeIf(record -> !record.getSemester().equals(semester));
    }

    // if no records, show empty table
    if (records.isEmpty()) {
      this.page.getScrollPane().setViewportView(new JTable());
      // update labels
      this.page.getLabels()[0].setText("Semester: " + semester);
      this.page.getLabels()[1].setText("Module Count: 0");
      this.page.getLabels()[2].setText("Total Credits: ");
      this.page.getLabels()[3].setText("GPA: ");
      this.page.getLabels()[4].setText("Average Mark: ");
      return;
    }

    // update table
    JTable table = new JTable(new MarkRecordTableModel(records));
    this.page.getScrollPane().setViewportView(table);

    // update labels
    this.page.getLabels()[0].setText("Semester: " + semester);
    this.page.getLabels()[1].setText("Module Count: " + records.size());
    this.page.getLabels()[2].setText("Total Credits: " + records.getTotalCredits());
    this.page.getLabels()[3].setText("GPA: " + records.getGPA());
    this.page.getLabels()[4].setText("Average Mark: " + records.getAverageMark());
  }
}
