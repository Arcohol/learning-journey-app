package com.group89.app.controller;

import java.util.List;
import javax.swing.JTable;
import com.group89.app.model.Task;
import com.group89.app.model.TaskTableModel;
import com.group89.app.utils.JsonConverter;
import com.group89.app.view.comp.TaskPage;

public class TaskPageController {
  private TaskPage page;
  private JsonConverter<Task> converter;
  private List<Task> list;

  public TaskPageController(TaskPage page) {
    this.page = page;
    this.converter = new JsonConverter<Task>("tasks.json", Task[].class);
    this.list = this.converter.toArrayList();

    init();
  }

  private void init() {
    this.page.getAddButton().addActionListener(e -> add());
    this.page.getDeleteButton().addActionListener(e -> delete());
    this.page.getSaveButton().addActionListener(e -> save());

    TaskTableModel model = new TaskTableModel(this.list);
    this.page.getTable().setModel(model);
    model.addTableModelListener(e -> this.page.getSaveButton().setEnabled(true));
  }

  private void add() {
    ((TaskTableModel) this.page.getTable().getModel()).addItem(new Task());
  }

  private void delete() {
    JTable table = this.page.getTable();
    TaskTableModel model = (TaskTableModel) this.page.getTable().getModel();
    int[] rows = this.page.getTable().getSelectedRows();
    for (int i = rows.length - 1; i >= 0; i--) {
      model.removeItem(table.convertRowIndexToModel(rows[i]));
    }
  }

  public void save() {
    this.converter.toFile(this.list);
    this.page.getSaveButton().setEnabled(false);
  }
}
