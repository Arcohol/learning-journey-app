package com.group89.app.view.comp;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import com.group89.app.controller.TaskPageController;
import com.group89.app.view.AppColor;

public class TaskPage extends JPanel {
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

  class ITable extends JTable {
    public ITable() {
      super();
      this.setRowHeight(ROW_HEIGHT);
      this.setFont(this.getFont().deriveFont(FONT_SIZE));
      this.setFillsViewportHeight(true);
      this.setDefaultRenderer(Object.class, new TaskRenderer());

      JComponent objectEditor =
          (JComponent) ((DefaultCellEditor) (this.getDefaultEditor(Object.class))).getComponent();
      objectEditor.setFont(objectEditor.getFont().deriveFont(FONT_SIZE));
    }
  }

  class IButton extends JButton {
    public IButton(String text) {
      super(text);
      this.setPreferredSize(new java.awt.Dimension(100, 30));
      this.setBackground(AppColor.LIGHT_GREY);
      this.setForeground(AppColor.BLACK);
      this.setFont(this.getFont().deriveFont(20f));
    }
  }

  private static final int ROW_HEIGHT = 30;
  private static final float FONT_SIZE = 24f;

  private JTable tasks;
  private JScrollPane scrollPane;
  private JButton addButton, deleteButton, saveButton;

  public TaskPage() {
    super(new GridBagLayout());

    this.tasks = new ITable();
    this.scrollPane = new JScrollPane(this.tasks);
    this.addButton = new IButton("Add");
    this.deleteButton = new IButton("Delete");
    this.saveButton = new IButton("Save");

    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(10, 10, 10, 10);

    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1;
    c.anchor = GridBagConstraints.WEST;
    this.add(this.addButton, c);

    c.gridx = 1;
    c.gridy = 0;
    c.weightx = 1;
    c.anchor = GridBagConstraints.CENTER;
    this.add(this.deleteButton, c);

    c.gridx = 2;
    c.gridy = 0;
    c.weightx = 1;
    c.anchor = GridBagConstraints.EAST;
    this.add(this.saveButton, c);

    c.insets = new Insets(0, 10, 10, 10);

    c.gridx = 0;
    c.gridy = 1;
    c.gridwidth = 3;
    c.weightx = 1;
    c.weighty = 1;
    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.BOTH;
    this.add(this.scrollPane, c);

    new TaskPageController(this);
  }

  public JTable getTable() {
    return this.tasks;
  }

  public JScrollPane getScrollPane() {
    return this.scrollPane;
  }

  public JButton getAddButton() {
    return this.addButton;
  }

  public JButton getDeleteButton() {
    return this.deleteButton;
  }

  public JButton getSaveButton() {
    return this.saveButton;
  }
}
