package com.group89.app.model;

import javax.swing.table.AbstractTableModel;

public class MarkRecordTableModel extends AbstractTableModel {
  MarkRecordList records;

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
      case 4 -> record.getCredits();
      default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
    };
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    MarkRecord record = this.records.get(rowIndex);
    switch (columnIndex) {
      case 0 -> record.setSemester((String) aValue);
      case 1 -> record.setModuleCode((String) aValue);
      case 2 -> record.setTitle((String) aValue);
      case 3 -> record.setMark((Integer) aValue);
      case 4 -> record.setCredits((Double) aValue);
      default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
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

  public void addRow(MarkRecord record) {
    this.records.add(record);
    fireTableDataChanged();
  }
}
