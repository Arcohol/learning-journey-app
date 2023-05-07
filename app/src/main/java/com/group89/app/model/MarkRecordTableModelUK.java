package com.group89.app.model;

import java.util.List;

public class MarkRecordTableModelUK extends ListTableModel<MarkRecord> {
  private static final String[] COLUMN_NAMES =
      {"Semester", "Module Code", "Title", "Mark (UK)", "Credits (UK)"};

  public MarkRecordTableModelUK(List<MarkRecord> list) {
    super(MarkRecord.class, COLUMN_NAMES, list);
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    MarkRecord record = getItem(rowIndex);
    return switch (columnIndex) {
      case 0 -> record.getSemester();
      case 1 -> record.getModuleCode();
      case 2 -> record.getTitle();
      case 3 -> record.getMarkUK();
      case 4 -> record.getCreditsUK();
      default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
    };
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    MarkRecord record = getItem(rowIndex);
    switch (columnIndex) {
      case 0 -> record.setSemester((String) aValue);
      case 1 -> record.setModuleCode((String) aValue);
      case 2 -> record.setTitle((String) aValue);
      case 3 -> record.setMarkUK((int) aValue);
      case 4 -> record.setCreditsUK((int) aValue);
      default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
    }
    fireTableRowsUpdated(rowIndex, rowIndex);
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    return switch (columnIndex) {
      case 0 -> String.class;
      case 1 -> String.class;
      case 2 -> String.class;
      case 3 -> Integer.class;
      case 4 -> Integer.class;
      default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
    };
  }
}
