package com.group89.app.model;

import java.util.List;

public class TaskTableModel extends ListTableModel<Task> {
  private static final String[] COLUMN_NAMES = {"Content", "Due", "Status"};

  public TaskTableModel(List<Task> tasks) {
    super(Task.class, COLUMN_NAMES, tasks);
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Task task = getItem(rowIndex);
    return switch (columnIndex) {
      case 0 -> task.getContent();
      case 1 -> task.getDue();
      case 2 -> task.getStatus();
      default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
    };
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    Task task = getItem(rowIndex);
    switch (columnIndex) {
      case 0 -> task.setContent((String) aValue);
      case 1 -> task.setDue((String) aValue);
      case 2 -> task.setStatus((Boolean) aValue);
      default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
    }
    fireTableRowsUpdated(rowIndex, rowIndex);
  }
}
