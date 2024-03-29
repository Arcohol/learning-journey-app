package com.group89.app.controller;

import javax.swing.RowFilter;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.enumeration.ComboBoxItem;
import com.group89.app.model.enumeration.ItemAll;
import com.group89.app.model.enumeration.TaskStatus;
import com.group89.app.model.TaskTableModel;
import com.group89.app.model.entity.Task;
import com.group89.app.view.comp.tablepage.TaskPage;

/**
 * A controller class that controls the task page.
 */
public class TaskPageController extends AbstractTablePageController<Task, TaskPage> {
  /**
   * Constructs a task page controller.
   * 
   * @param page the task page
   */
  public TaskPageController(TaskPage page) {
    super(page, "tasks.json", Task[].class, Task.class);
    init();
  }

  protected void init() {
    super.init();

    view.getStatusBox().addActionListener(e -> query());

    query();
  }

  @Override
  protected void query() {
    model = new TaskTableModel(list);
    model.addTableModelListener(e -> view.getSaveButton().setEnabled(true));

    view.getTable().setModel(model);

    ComboBoxItem status = (ComboBoxItem) view.getStatusBox().getSelectedItem();
    sorter.setRowFilter(new RowFilter<ListTableModel<Task>, Integer>() {
      @Override
      public boolean include(Entry<? extends ListTableModel<Task>, ? extends Integer> entry) {
        ListTableModel<Task> model = entry.getModel();
        Task record = model.getItem(entry.getIdentifier());
        return status == ItemAll.ALL || (record.getStatus() && status == TaskStatus.COMPLETED)
            || (!record.getStatus() && status == TaskStatus.OPEN);
      }
    });
    sorter.setModel(model);
  }

  /**
   * Add a new element with the selected items in the combo boxes to the table. If {@code All} is
   * selected, the default value is used instead.
   */
  @Override
  protected void add() {
    ComboBoxItem status = (ComboBoxItem) view.getStatusBox().getSelectedItem();
    Task record = new Task("", "", status == TaskStatus.COMPLETED);
    model.addItem(record);
  }
}
