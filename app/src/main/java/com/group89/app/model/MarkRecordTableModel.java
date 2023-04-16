package com.group89.app.model;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class MarkRecordTableModel implements TableModel {
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
    return this.records.get(0).getClass().getDeclaredFields().length;
  }

  @Override
  public String getColumnName(int columnIndex) {
    return this.records.get(0).getClass().getDeclaredFields()[columnIndex].getName();
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    return this.records.get(0).getClass().getDeclaredFields()[columnIndex].getType();
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
  }

  @Override
  public void addTableModelListener(TableModelListener l) {
  }

  @Override
  public void removeTableModelListener(TableModelListener l) {
  }

  public MarkRecordList getMarkRecordList() {
    return this.records;
  }
}
