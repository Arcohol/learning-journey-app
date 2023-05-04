package com.group89.app.view.comp;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultCellEditor;

public class TaskPage extends JPanel {
  class TaskRenderer extends DefaultTableCellRenderer {
    // make the entire row green if the task is done
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
        boolean hasFocus, int row, int column) {
      Component c =
          super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      if ((boolean) table.getValueAt(row, 2)) {
        c.setBackground(Color.green);
      } else {
        c.setBackground(Color.white);
      }
      return c;
    }
  }

  class ITable extends JTable {
    public ITable() {
      super();
      this.setRowHeight(30);
      this.setFont(this.getFont().deriveFont(FONT_SIZE));
      this.setFillsViewportHeight(true);

      // this is a suspicious way to change the font size while keeping the same editor
      // it overrides the default cell editor managed by JTable
      JComponent objectEditor =
          (JComponent) ((DefaultCellEditor) (this.getDefaultEditor(Object.class))).getComponent();
      objectEditor.setFont(objectEditor.getFont().deriveFont(FONT_SIZE));

      JComponent numberEditor =
          (JComponent) ((DefaultCellEditor) (this.getDefaultEditor(Number.class))).getComponent();
      numberEditor.setFont(numberEditor.getFont().deriveFont(FONT_SIZE));

      // use the custom renderer
      this.setDefaultRenderer(Object.class, new TaskRenderer());
    }
  }

  private static final float FONT_SIZE = 20f;
  private JTable tasks;
  private JScrollPane scrollPane;

  public TaskPage() {
    super(new GridBagLayout());

    // make sample data
    String[] columnNames = {"Task", "Due Date", "Status"};
    Object[][] data = {{"Task 1", "2023-03-12", false}, {"Task 2", "2023-04-12", false},
        {"Task 3", "2024-01-21", false},};

    // set table model
    DefaultTableModel model = new DefaultTableModel(data, columnNames) {
      @Override
      public Class<?> getColumnClass(int column) {
        if (column == 2) {
          return Boolean.class;
        }
        return String.class;
      }
      
      @Override
      public void setValueAt(Object aValue, int row, int column) {
        super.setValueAt(aValue, row, column);
        if (column == 2) {
          fireTableRowsUpdated(row, row);
        }
      }
    };

    // add table to panel
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1;
    c.weighty = 1;

    tasks = new ITable();
    tasks.setModel(model);
    // enable sort
    tasks.setAutoCreateRowSorter(true);


    scrollPane = new JScrollPane(tasks);

    this.add(scrollPane, c);
  }
}
