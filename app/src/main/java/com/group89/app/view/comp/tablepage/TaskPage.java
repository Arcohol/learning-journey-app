package com.group89.app.view.comp.tablepage;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import com.group89.app.controller.TaskPageController;
import com.group89.app.model.enumeration.ComboBoxItem;
import com.group89.app.model.enumeration.TaskStatus;
import com.group89.app.utils.EnumFactory;
import com.group89.app.view.comp.IComboBox;

/**
 * A table page for tasks.
 */
public class TaskPage extends DefaultTablePage {
  /**
   * A table cell renderer for tasks. It renders the cell green if the task is completed.
   */
  class TaskRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
        boolean hasFocus, int row, int column) {
      Component c =
          super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      if ((boolean) table.getValueAt(row, table.getColumn("Status").getModelIndex())) {
        c.setBackground(Color.green);
      } else {
        c.setBackground(Color.white);
      }
      return c;
    }
  }

  private JComboBox<ComboBoxItem> statusBox;

  public TaskPage() {
    super();

    statusBox = new IComboBox<>(EnumFactory.addItemAll(TaskStatus.values()));

    GridBagConstraints c = new GridBagConstraints();

    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1;
    c.anchor = GridBagConstraints.WEST;

    getHeader().add(statusBox, c);

    table.setDefaultRenderer(Object.class, new TaskRenderer());

    new TaskPageController(this);
  }

  /**
   * Returns the status box.
   * 
   * @return the status box
   */
  public JComboBox<ComboBoxItem> getStatusBox() {
    return statusBox;
  }
}
