package com.group89.app.model;

import java.util.List;
import com.group89.app.model.enumeration.CourseType;
import com.group89.app.model.enumeration.Semester;
import com.group89.app.model.entity.Mark;

public class MarkTableModelCN extends ListTableModel<Mark> {
  private static final String[] COLUMN_NAMES =
      {"Semester", "Module Code", "Title", "Mark (CN)", "Credits (CN)", "Type"};

  public MarkTableModelCN(List<Mark> records) {
    super(Mark.class, COLUMN_NAMES, records);
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Mark record = getItem(rowIndex);
    return switch (columnIndex) {
      case 0 -> record.getSemester();
      case 1 -> record.getModuleCode();
      case 2 -> record.getTitle();
      case 3 -> record.getMarkCN();
      case 4 -> record.getCreditsCN();
      case 5 -> record.getType();
      default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
    };
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    Mark record = getItem(rowIndex);
    switch (columnIndex) {
      case 0 -> record.setSemester((Semester) aValue);
      case 1 -> record.setModuleCode((String) aValue);
      case 2 -> record.setTitle((String) aValue);
      case 3 -> record.setMarkCN((Integer) aValue);
      case 4 -> record.setCreditsCN((Double) aValue);
      case 5 -> record.setType((CourseType) aValue);
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
      case 4 -> Double.class;
      case 5 -> CourseType.class;
      default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
    };
  }
}
