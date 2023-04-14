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
    return false;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
      // find the field
      MarkRecord record = this.records.get(rowIndex);
      switch (columnIndex) {
        case 0:
          return record.getSemester();
        case 1:
          return record.getModuleCode();
        case 2:
          return record.getTitle();
        case 3:
          return record.getMark();
        case 4:
          return record.getCredits();
        default:
          return null;
      }
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
  }

  @Override
  public void addTableModelListener(TableModelListener l) {
  }

  @Override
  public void removeTableModelListener(TableModelListener l) {
  }
}
