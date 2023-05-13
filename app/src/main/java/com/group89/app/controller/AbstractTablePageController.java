package com.group89.app.controller;

import java.util.List;
import javax.swing.JTable;
import com.group89.app.model.ListTableModel;
import com.group89.app.utils.JsonConverter;
import com.group89.app.view.comp.AbstractTablePage;

public abstract class AbstractTablePageController<T, S extends AbstractTablePage> {
  protected JsonConverter<T> converter;
  protected List<T> list;
  protected ListTableModel<T> model;
  protected S view;

  public AbstractTablePageController(S page, String filename, Class<T[]> clazz) {
    view = page;
    converter = new JsonConverter<T>(filename, clazz);
    list = converter.toArrayList();
    init();
  }

  protected abstract void init();

  public void add(T item) {
    model.addItem(item);
  }

  public void delete() {
    JTable table = view.getTable();
    int[] rows = table.getSelectedRows();
    for (int i = rows.length - 1; i >= 0; i--) {
      model.removeItem(table.convertRowIndexToModel(rows[i]));
    }
  }

  public void save() {
    converter.toFile(list);
  }
}
