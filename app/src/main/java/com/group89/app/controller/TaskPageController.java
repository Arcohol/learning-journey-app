package com.group89.app.controller;

import javax.swing.RowFilter;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.TaskRecordTableModel;
import com.group89.app.model.entity.TaskRecord;
import com.group89.app.model.enumeration.ComboBoxItem;
import com.group89.app.model.enumeration.ItemAll;
import com.group89.app.model.enumeration.TaskStatus;
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
    model = new TaskRecordTableModel(list);
    model.addTableModelListener(e -> view.getSaveButton().setEnabled(true));

    view.getTable().setModel(model);

    ComboBoxItem status = (ComboBoxItem) view.getStatusBox().getSelectedItem();
    sorter.setRowFilter(new RowFilter<ListTableModel<TaskRecord>, Integer>() {
      @Override
      public boolean include(Entry<? extends ListTableModel<TaskRecord>, ? extends Integer> entry) {
        ListTableModel<TaskRecord> model = entry.getModel();
        TaskRecord record = model.getItem(entry.getIdentifier());
        return status == ItemAll.ALL || (record.getStatus() && status == TaskStatus.COMPLETED)
            || (!record.getStatus() && status == TaskStatus.OPEN);
      }
    });
    sorter.setModel(model);
  }

  @Override
  protected void add() {
    ComboBoxItem status = (ComboBoxItem) view.getStatusBox().getSelectedItem();
    TaskRecord record = new TaskRecord("", "", status == TaskStatus.COMPLETED);
    model.addItem(record);
  }
}
