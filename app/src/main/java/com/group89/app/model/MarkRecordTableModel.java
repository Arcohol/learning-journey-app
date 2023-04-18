package com.group89.app.model;

import javax.swing.table.AbstractTableModel;
import com.group89.app.utils.MarkConverter;

public class MarkRecordTableModel extends AbstractTableModel {
  MarkRecordList records;
  private MarkConverter converter = new MarkConverter();

  public MarkRecordTableModel(MarkRecordList records) {
    this.records = records;
  }

  @Override
  public int getRowCount() {
    return this.records.size();
  }

  @Override
  public int getColumnCount() {
    return MarkRecord.class.getDeclaredFields().length;
  }

  @Override
  public String getColumnName(int columnIndex) {
    return MarkRecord.class.getDeclaredFields()[columnIndex].getName();
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    return MarkRecord.class.getDeclaredFields()[columnIndex].getType();
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return true;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    MarkRecord record = this.records.get(rowIndex);
    return switch (columnIndex) {
      case 0 -> record.getSemester();
      case 1 -> record.getModuleCode();
      case 2 -> record.getTitle();
      case 3 -> record.getMark();
      case 4 -> record.getMarkQMUL();
      case 5 -> record.getCredits();
      case 6 -> record.getCreditsQMUL();
      default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
    };
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    MarkRecord record = this.records.get(rowIndex);
    switch (columnIndex) {
      case 0:
        record.setSemester((String) aValue);
        break;
      case 1:
        record.setModuleCode((String) aValue);
        break;
      case 2:
        record.setTitle((String) aValue);
        break;
      case 3:
        record.setMark((Integer) aValue);
        record.setMarkQMUL(this.converter.toQMUL(record.getMark()));
        fireTableCellUpdated(rowIndex, 4);
        break;
      case 4:
        record.setMarkQMUL((Integer) aValue);
        record.setMark(this.converter.toBUPT(record.getMarkQMUL()));
        fireTableCellUpdated(rowIndex, 3);
        break;
      case 5:
        record.setCredits((Double) aValue);
        break;
      case 6:
        record.setCreditsQMUL((Integer) aValue);
        break;
      default:
        throw new IllegalArgumentException("Unexpected value: " + columnIndex);
    }
    fireTableCellUpdated(rowIndex, columnIndex);
  }

  public MarkRecordList getMarkRecordList() {
    return this.records;
  }

  public void removeRows(int[] selectedRows) {
    for (int i = selectedRows.length - 1; i >= 0; i--) {
      this.records.remove(selectedRows[i]);
    }
    fireTableDataChanged();
  }

  public MarkRecord getMarkRecord(Object identifier) {
    return this.records.get((int) identifier);
  }
}
