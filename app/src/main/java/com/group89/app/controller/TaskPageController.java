package com.group89.app.controller;

import javax.swing.RowFilter;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.TaskRecordTableModel;
import com.group89.app.model.entity.TaskRecord;
import com.group89.app.model.enumeration.AbstractComboBoxItemType;
import com.group89.app.model.enumeration.TaskStatusItemType;
import com.group89.app.view.comp.tablepage.TaskPage;

public class TaskPageController extends AbstractTablePageController<TaskRecord, TaskPage> {
  private AbstractComboBoxItemType<String> statusType;

  public TaskPageController(TaskPage page) {
    super(page, "tasks.json", TaskRecord[].class, TaskRecord.class);
    init();
  }

  protected void init() {
    super.init();

    view.getStatusBox().addActionListener(e -> query());

    statusType = new TaskStatusItemType();

    query();
  }

  @Override
  protected void query() {
    model = new TaskRecordTableModel(list);
    model.addTableModelListener(e -> view.getSaveButton().setEnabled(true));

    view.getTable().setModel(model);

    String status = (String) view.getStatusBox().getSelectedItem();
    sorter.setRowFilter(new RowFilter<ListTableModel<TaskRecord>, Integer>() {
      @Override
      public boolean include(Entry<? extends ListTableModel<TaskRecord>, ? extends Integer> entry) {
        ListTableModel<TaskRecord> model = entry.getModel();
        TaskRecord record = model.getItem(entry.getIdentifier());
        return status.equals(statusType.getItemAll())
            || (record.getStatus() && status.equals("Completed"))
            || (!record.getStatus() && status.equals("In progress"));
      }
    });
    sorter.setModel(model);
  }

  @Override
  protected void add() {
    String status = (String) view.getStatusBox().getSelectedItem();
    TaskRecord record = new TaskRecord("", "", switch (status) {
      case "All" -> false;
      case "Completed" -> true;
      case "In progress" -> false;
      default -> throw new IllegalStateException("Unexpected value: " + status);
    });
    model.addItem(record);
  }
}
