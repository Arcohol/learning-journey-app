package com.group89.app.controller;

import com.group89.app.model.Task;
import com.group89.app.model.TaskTableModel;
import com.group89.app.view.comp.TaskPage;

public class TaskPageController extends AbstractTablePageController<Task, TaskPage>{
  public TaskPageController(TaskPage page) {
    super(page, "tasks.json", Task[].class, Task.class);

    init();
  }

  protected void init() {
    super.init();
    
    query();
  }

  @Override
  protected void query() {
    model = new TaskTableModel(list);
    view.getTable().setModel(model);
  }
}
