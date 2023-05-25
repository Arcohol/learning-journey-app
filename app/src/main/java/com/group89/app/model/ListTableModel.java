package com.group89.app.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * This abstract class provides default implementations for table models that are backed by a list.
 *
 * @param <T> the type of items in the list
 */
public abstract class ListTableModel<T> extends AbstractTableModel {
  /**
   * The class of the type of items in the list.
   */
  protected final Class<T> clazz;

  /**
   * The names of the columns in the table.
   */
  protected final String[] columnNames;

  /**
   * The list of items in the table model.
   */
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

  /**
   * Returns the list of items in the table model.
   *
   * @return the list of items in the table model
   */
  public List<T> getList() {
    return list;
  }

  /**
   * Returns the item of the specified identifier.
   * 
   * @param identifier the identifier of the item
   * @return the item of the specified identifier
   */
  public T getItem(Integer identifier) {
    return list.get(identifier);
  }

  /**
   * Adds an item to the table model.
   *
   * @param item the item to be added
   */
  public void addItem(T item) {
    list.add(item);
    fireTableRowsInserted(list.size() - 1, list.size() - 1);
  }

  /**
   * Removes the item of the specified index from the table model.
   *
   * @param index the index of the item to be removed
   */
  public void removeItem(int index) {
    list.remove(index);
    fireTableRowsDeleted(index, index);
  }
}
