package com.group89.app.controller;

import javax.swing.RowFilter;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.TaskRecordTableModel;
import com.group89.app.model.entity.TaskRecord;
import com.group89.app.view.comp.tablepage.TaskPage;

public class TaskPageController extends AbstractTablePageController<TaskRecord, TaskPage> {
  public TaskPageController(TaskPage page) {
    super(page, "tasks.json", TaskRecord[].class, TaskRecord.class);
    init();
  }

  protected void init() {
    super.init();

    view.getStatusBox().addActionListener(e -> query());

    query();
  }

  @Override
  protected void query() {
    String status = (String) view.getStatusBox().getSelectedItem();

    model = new TaskRecordTableModel(list);
    model.addTableModelListener(e -> view.getSaveButton().setEnabled(true));

    view.getTable().setModel(model);

    sorter.setRowFilter(new RowFilter<ListTableModel<TaskRecord>, Integer>() {
      @Override
      public boolean include(Entry<? extends ListTableModel<TaskRecord>, ? extends Integer> entry) {
        ListTableModel<TaskRecord> model = entry.getModel();
        TaskRecord record = model.getItem(entry.getIdentifier());
        return status.equals("All") || (record.getStatus() && status.equals("Completed"))
            || (!record.getStatus() && status.equals("In progress"));
      }
    });
    sorter.setModel(model);
  }
}
