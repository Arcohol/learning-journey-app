package com.group89.app.controller;

import java.util.List;
import javax.swing.JTable;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.Task;
import com.group89.app.model.TaskTableModel;
import com.group89.app.utils.JsonConverter;
import com.group89.app.view.comp.TaskPage;

// controller
public class TaskPageController {
  // model
  private ListTableModel<Task> model;

  // view
  private TaskPage view;

  private JsonConverter<Task> converter;
  private List<Task> list;

  public TaskPageController(TaskPage page) {
    view = page;

    converter = new JsonConverter<Task>("tasks.json", Task[].class);
    list = converter.toArrayList();

    init();
  }

  private void init() {
    view.getAddButton().addActionListener(e -> add());
    view.getDeleteButton().addActionListener(e -> delete());
    view.getSaveButton().addActionListener(e -> save());

    model = new TaskTableModel(list);
    model.addTableModelListener(e -> view.getSaveButton().setEnabled(true));

    view.getTable().setModel(model);
  }

  private void add() {
    model.addItem(new Task());
  }

  private void delete() {
    JTable table = view.getTable();
    int[] rows = view.getTable().getSelectedRows();
    for (int i = rows.length - 1; i >= 0; i--) {
      model.removeItem(table.convertRowIndexToModel(rows[i]));
    }
  }

  public void save() {
    converter.toFile(list);
    view.getSaveButton().setEnabled(false);
  }
}
