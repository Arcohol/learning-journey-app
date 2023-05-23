package com.group89.app.view.comp.tablepage;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import com.group89.app.controller.TaskPageController;
import com.group89.app.model.enumeration.AbstractComboBoxItemType;
import com.group89.app.model.enumeration.TaskStatusComboBoxItemType;
import com.group89.app.view.comp.IComboBox;

public class TaskPage extends DefaultTablePage {
  class TaskRenderer extends DefaultTableCellRenderer {
    // make the entire row green if the task is done
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

  private JComboBox<String> statusBox;

  public TaskPage() {
    super();

    AbstractComboBoxItemType<String> statusType = new TaskStatusComboBoxItemType();
    statusBox = new IComboBox<>(statusType.values());

    GridBagConstraints c = new GridBagConstraints();

    c.insets.set(10, 10, 0, 0);

    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.WEST;
    add(statusBox, c);

    table.setDefaultRenderer(Object.class, new TaskRenderer());

    new TaskPageController(this);
  }

  public JComboBox<String> getStatusBox() {
    return statusBox;
  }
}
