package com.group89.app.controller;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import javax.swing.table.TableRowSorter;
import com.group89.app.model.MarkRecord;
import com.group89.app.model.MarkRecordList;
import com.group89.app.model.MarkRecordTableModel;
import com.group89.app.utils.JsonConverter;
import com.group89.app.view.comp.MarkRecordPage;

public class MarkRecordPageController implements Controller {
  // temporary solution for enforcing number range
  // subject to change
  class NumberEditor extends DefaultCellEditor {
    Integer value;

    public NumberEditor() {
      super(new JTextField());
      ((JTextField) getComponent()).setHorizontalAlignment(JTextField.RIGHT);
    }

    @Override
    public boolean stopCellEditing() {
      String s = (String) super.getCellEditorValue();
      try {
        value = Integer.parseInt(s);
        if (value < 0 || value > 100) {
          throw new NumberFormatException();
        }
      } catch (NumberFormatException e) {
        ((JComponent) getComponent()).setBorder(new LineBorder(Color.red));
        return false;
      }
      return super.stopCellEditing();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
        int row, int column) {
      this.value = null;
      ((JComponent) getComponent()).setBorder(new LineBorder(Color.black));
      return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }

    @Override
    public Object getCellEditorValue() {
      return value;
    }
  }

  private MarkRecordPage page;
  private JsonConverter<MarkRecord> converter;
  private MarkRecordList records;
  private TableRowSorter<MarkRecordTableModel> sorter;

  public MarkRecordPageController(MarkRecordPage page) {
    this.page = page;
    this.converter = new JsonConverter<>("marks.json", MarkRecord[].class);
    this.records = new MarkRecordList(converter.toList());
    this.sorter = new TableRowSorter<MarkRecordTableModel>();
  }

  @Override
  public void init() {
    this.page.getQueryButton().addActionListener(e -> this.query());
    this.page.getSaveButton().addActionListener(e -> this.save());
    this.page.getDeleteButton().addActionListener(e -> this.delete());
    this.page.getAddButton().addActionListener(e -> this.add());
    this.page.getTable().setRowSorter(sorter);

    query();
  }

  private void updateLabels() {
    String semester = (String) this.page.getSemesterBox().getSelectedItem();
    JLabel[] labels = this.page.getLabels();

    JTable table = this.page.getTable();
    MarkRecordTableModel tableModel = (MarkRecordTableModel) table.getModel();

    int size = table.getRowCount();
    double totalCreditsCN = 0;
    double gpa = 0.0;
    double averageCN = 0.0;

    for (int row = 0; row < size; row++) {
      int modelRow = table.convertRowIndexToModel(row);
      MarkRecord record = tableModel.getMarkRecord(modelRow);
      totalCreditsCN += record.getCreditsCN();
      gpa += record.getGradePoint() * record.getCreditsCN();
      averageCN += (double) record.getMarkCN() * record.getCreditsCN();
    }

    gpa /= totalCreditsCN;
    averageCN /= totalCreditsCN;

    DecimalFormat df = new DecimalFormat("#.##");
    gpa = Double.parseDouble(df.format(gpa));
    averageCN = Double.parseDouble(df.format(averageCN));

    labels[0].setText("Semester: " + semester);
    labels[1].setText("Module Count: " + size);
    labels[2].setText("Total Credits: " + totalCreditsCN);
    labels[3].setText("GPA: " + gpa);
    labels[4].setText("Average Mark: " + averageCN);
  }

  private void query() {
    // get semester from semesterBox
    String semester = (String) this.page.getSemesterBox().getSelectedItem();

    // filter records
    RowFilter<MarkRecordTableModel, Object> filter = new RowFilter<MarkRecordTableModel, Object>() {
      @Override
      public boolean include(Entry<? extends MarkRecordTableModel, ? extends Object> entry) {
        MarkRecordTableModel tableModel = entry.getModel();
        MarkRecord record = tableModel.getMarkRecord(entry.getIdentifier());
        return semester.equals("all") || record.getSemester().equals(semester);
      }
    };
    sorter.setRowFilter(filter);

    JTable table = this.page.getTable();
    MarkRecordTableModel tableModel = new MarkRecordTableModel(records);
    sorter.setModel(tableModel);
    tableModel.addTableModelListener(e -> this.page.getSaveButton().setEnabled(true));
    tableModel.addTableModelListener(e -> this.updateLabels());
    table.setModel(tableModel);
    NumberEditor e = new NumberEditor();
    table.getColumnModel().getColumn(3).setCellEditor(e);
    table.getColumnModel().getColumn(4).setCellEditor(e);

    updateLabels();
  }

  private void save() {
    MarkRecordTableModel tableModel = (MarkRecordTableModel) this.page.getTable().getModel();
    converter.toFile(tableModel.getMarkRecordList());
    this.page.getSaveButton().setEnabled(false);
  }

  private void delete() {
    JTable table = this.page.getTable();
    MarkRecordTableModel tableModel = (MarkRecordTableModel) table.getModel();
    // maps selected rows to model rows
    int[] modelRows = table.getSelectedRows();
    for (int i = 0; i < modelRows.length; i++) {
      modelRows[i] = table.convertRowIndexToModel(modelRows[i]);
    }
    tableModel.removeRows(modelRows);
  }

  private void add() {
    // add a blank new row
    MarkRecordTableModel tableModel = (MarkRecordTableModel) this.page.getTable().getModel();
    tableModel.addRow(new MarkRecord());
  }
}
