package com.group89.app.view.comp.tablepage;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import com.group89.app.controller.TaskPageController;
import com.group89.app.view.comp.IButton;
import com.group89.app.view.comp.ITable;

public class TaskPage extends AbstractTablePage {
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

  public TaskPage() {
    super();

    setLayout(new GridBagLayout());

    table = new ITable();
    scrollPane = new JScrollPane(table);
    addButton = new IButton("Add");
    deleteButton = new IButton("Delete");
    saveButton = new IButton("Save");

    table.setDefaultRenderer(Object.class, new TaskRenderer());

    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(10, 10, 10, 10);

    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1;
    c.anchor = GridBagConstraints.WEST;
    add(addButton, c);

    c.gridx = 1;
    c.gridy = 0;
    c.weightx = 1;
    c.anchor = GridBagConstraints.CENTER;
    add(deleteButton, c);

    c.gridx = 2;
    c.gridy = 0;
    c.weightx = 1;
    c.anchor = GridBagConstraints.EAST;
    add(saveButton, c);

    c.insets = new Insets(0, 10, 10, 10);

    c.gridx = 0;
    c.gridy = 1;
    c.gridwidth = 3;
    c.weightx = 1;
    c.weighty = 1;
    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.BOTH;
    add(scrollPane, c);

    new TaskPageController(this);
  }
}
