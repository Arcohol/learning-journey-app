package com.group89.app.model;

import java.util.List;
import com.group89.app.model.entity.TaskRecord;

public class TaskRecordTableModel extends ListTableModel<TaskRecord> {
  private static final String[] COLUMN_NAMES = {"Content", "Due", "Status"};

  public TaskRecordTableModel(List<TaskRecord> tasks) {
    super(TaskRecord.class, COLUMN_NAMES, tasks);
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    TaskRecord task = getItem(rowIndex);
    return switch (columnIndex) {
      case 0 -> task.getContent();
      case 1 -> task.getDue();
      case 2 -> task.getStatus();
      default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
    };
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    TaskRecord task = getItem(rowIndex);
    switch (columnIndex) {
      case 0 -> task.setContent((String) aValue);
      case 1 -> task.setDue((String) aValue);
      case 2 -> task.setStatus((Boolean) aValue);
      default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
    }
    fireTableRowsUpdated(rowIndex, rowIndex);
  }
}
