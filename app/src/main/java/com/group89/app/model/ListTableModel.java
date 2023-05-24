package com.group89.app.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public abstract class ListTableModel<T> extends AbstractTableModel {
  // things that are common to all list table models
  // (i.e. all list table models have these things)
  // are defined here
  // so that we don't have to repeat ourselves

  protected final Class<T> clazz;
  protected final String[] columnNames;
  protected List<T> list;

  public ListTableModel(Class<T> clazz, String[] columnNames, List<T> list) {
    this.clazz = clazz;
    this.columnNames = columnNames;
    this.list = list;
  }

  @Override
  public abstract Object getValueAt(int rowIndex, int columnIndex);

  @Override
  public abstract void setValueAt(Object aValue, int rowIndex, int columnIndex);

  @Override
  public int getRowCount() {
    return list.size();
  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  @Override
  public String getColumnName(int columnIndex) {
    return columnNames[columnIndex];
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    return clazz.getDeclaredFields()[columnIndex].getType();
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return true;
  }

  public List<T> getList() {
    return list;
  }

  public T getItem(Integer identifier) {
    return list.get(identifier);
  }

  public void addItem(T item) {
    list.add(item);
    fireTableRowsInserted(list.size() - 1, list.size() - 1);
  }

  public void removeItem(int index) {
    list.remove(index);
    fireTableRowsDeleted(index, index);
  }
}
