package com.group89.app.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;
import com.group89.app.model.ListTableModel;
import com.group89.app.utils.JsonConverter;
import com.group89.app.view.comp.tablepage.AbstractTablePage;

/**
 * This class defines and provides a skeletal implementation of a controller for a table page.
 * 
 * @param <T> the type of elements in the table
 * @param <S> the type of the table page
 */
public abstract class AbstractTablePageController<T, S extends AbstractTablePage> {
  /**
   * The model of the table in the table page.
   */
  protected ListTableModel<T> model;

  /**
   * The table page.
   */
  protected S view;

  /**
   * The converter for converting between JSON and Java objects.
   */
  protected JsonConverter<T> converter;

  /**
   * The list of elements in the table.
   */
  protected List<T> list;

  /**
   * The sorter for the table.
   */
  protected TableRowSorter<ListTableModel<T>> sorter;

  /**
   * The class of the type of elements in the table.
   */
  protected Class<T> itemClazz;

  /**
   * Constructs a controller for a table page.
   * 
   * @param page the table page
   * @param filename the name of the JSON file
   * @param listClazz the class of the array type of elements in the JSON file
   * @param itemClazz the class of the type of elements in the table
   */
  public AbstractTablePageController(S page, String filename, Class<T[]> listClazz,
      Class<T> itemClazz) {
    view = page;
    this.itemClazz = itemClazz;

    converter = new JsonConverter<T>(filename, listClazz);
    list = converter.toArrayList();

    sorter = new TableRowSorter<ListTableModel<T>>();
  }

  protected void init() {
    view.getAddButton().addActionListener(e -> add());
    view.getDeleteButton().addActionListener(e -> delete());
    view.getSaveButton().addActionListener(e -> save());

    view.getTable().setRowSorter(sorter);
  }

  /**
   * Queries the data set and updates the page.
   */
  protected abstract void query();

  /**
   * Adds a new element to the table.
   */
  protected void add() {
    T item;
    try {
      item = itemClazz.getDeclaredConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
      e.printStackTrace();
      return;
    }
    model.addItem(item);
  }

  /**
   * Deletes the selected elements from the table.
   */
  protected void delete() {
    JTable table = view.getTable();
    int[] rows = table.getSelectedRows();
    for (int i = rows.length - 1; i >= 0; i--) {
      model.removeItem(table.convertRowIndexToModel(rows[i]));
    }
  }

  /**
   * Saves the table to the JSON file.
   */
  protected void save() {
    converter.toFile(list);
    view.getSaveButton().setEnabled(false);
  }
}
