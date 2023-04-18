package com.group89.app.controller;

import javax.swing.JTextField;
import com.group89.app.model.MarkRecord;
import com.group89.app.model.MarkRecordTableModel;
import com.group89.app.view.AddMarkRecordFrame;

public class AddMarkRecordFrameController implements Controller {
  private AddMarkRecordFrame frame;
  private MarkRecordTableModel tableModel;

  public AddMarkRecordFrameController(AddMarkRecordFrame frame, MarkRecordTableModel tableModel) {
    this.frame = frame;
    this.tableModel = tableModel;
  }

  @Override
  public void init() {
    this.frame.getSubmitButton().addActionListener(e -> this.submit());
    this.frame.getClearButton().addActionListener(e -> this.clear());
  }

  private void submit() {
    JTextField[] fields = this.frame.getTextFields();
    String[] values = new String[fields.length];
    for (int i = 0; i < fields.length; i++) {
      values[i] = fields[i].getText();
    }

    this.tableModel.addRow(new MarkRecord(values[0], values[1], values[2],
        Integer.parseInt(values[3]), Double.parseDouble(values[4])));
  }

  private void clear() {
    JTextField[] fields = this.frame.getTextFields();
    for (JTextField field : fields) {
      field.setText("");
    }
  }
}
