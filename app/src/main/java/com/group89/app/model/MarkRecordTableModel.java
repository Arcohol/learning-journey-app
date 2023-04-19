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
      case 3 -> record.getMarkCN();
      case 4 -> record.getMarkUK();
      case 5 -> record.getCreditsCN();
      case 6 -> record.getCreditsUK();
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
      case 3 -> record.setMarkCN((int) aValue);
      case 4 -> record.setMarkUK((int) aValue);
      case 5 -> record.setCreditsCN((double) aValue);
      case 6 -> record.setCreditsUK((int) aValue);
      default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
    }
    fireTableDataChanged();
  }

  public MarkRecordList getMarkRecordList() {
    return this.records;
  }

  public MarkRecord getMarkRecord(Object identifier) {
    return this.records.get((int) identifier);
  }

  public void removeRows(int[] selectedRows) {
    for (int i = selectedRows.length - 1; i >= 0; i--) {
      this.records.remove(selectedRows[i]);
    }
    fireTableDataChanged();
  }

  public void addRow(MarkRecord record) {
    this.records.add(record);
    fireTableDataChanged();
  }
}
