package com.group89.app.model;

import java.util.List;
import com.group89.app.model.enumeration.CourseType;
import com.group89.app.model.enumeration.Semester;
import com.group89.app.model.entity.Mark;

public class MarkTableModel extends ListTableModel<Mark> {
  private static final String[] COLUMN_NAMES = {"Semester", "Module Code", "Title", "Mark (CN)",
      "Mark (UK)", "Credits (CN)", "Credits (UK)", "Type"};

  public MarkTableModel(List<Mark> records) {
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
      case 4 -> record.getMarkUK();
      case 5 -> record.getCreditsCN();
      case 6 -> record.getCreditsUK();
      case 7 -> record.getType();
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
      case 3 -> record.setMarkCN((int) aValue);
      case 4 -> record.setMarkUK((int) aValue);
      case 5 -> record.setCreditsCN((double) aValue);
      case 6 -> record.setCreditsUK((int) aValue);
      case 7 -> record.setType((CourseType) aValue);
      default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
    }
    fireTableRowsUpdated(rowIndex, rowIndex);
  }
}
