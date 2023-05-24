package com.group89.app.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;
import com.group89.app.model.ListTableModel;
import com.group89.app.utils.JsonConverter;
import com.group89.app.view.comp.tablepage.AbstractTablePage;

public abstract class AbstractTablePageController<T, S extends AbstractTablePage> {
  protected ListTableModel<T> model;
  protected S view;

  protected JsonConverter<T> converter;
  protected List<T> list;

  protected TableRowSorter<ListTableModel<T>> sorter;

  protected Class<T> itemClazz;

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

  protected abstract void query();

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

  protected void delete() {
    JTable table = view.getTable();
    int[] rows = table.getSelectedRows();
    for (int i = rows.length - 1; i >= 0; i--) {
      model.removeItem(table.convertRowIndexToModel(rows[i]));
    }
  }

  protected void save() {
    converter.toFile(list);
    view.getSaveButton().setEnabled(false);
  }
}
