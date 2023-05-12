package com.group89.app.view.comp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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

      setRowHeight(ROW_HEIGHT);
      setFont(getFont().deriveFont(FONT_SIZE));
      setFillsViewportHeight(true);
      setDefaultRenderer(Object.class, new TaskRenderer());

      getTableHeader().setFont(getFont().deriveFont(HEADER_FONT_SIZE));
      getTableHeader().setPreferredSize(HEADER_SIZE);

      JComponent objectEditor =
          (JComponent) ((DefaultCellEditor) (getDefaultEditor(Object.class))).getComponent();
      objectEditor.setFont(objectEditor.getFont().deriveFont(FONT_SIZE));
    }
  }

  class IButton extends JButton {
    public IButton(String text) {
      super(text);

      setPreferredSize(new java.awt.Dimension(100, 30));
      setBackground(AppColor.LIGHT_GREY);
      setForeground(AppColor.BLACK);
      setFont(getFont().deriveFont(20f));
    }
  }

  private static final int ROW_HEIGHT = 30;
  private static final float FONT_SIZE = 24f;
  private static final float HEADER_FONT_SIZE = 18f;
  private static final Dimension HEADER_SIZE = new Dimension(0, 40);

  private JTable tasks;
  private JScrollPane scrollPane;
  private JButton addButton, deleteButton, saveButton;

  public TaskPage() {
    super(new GridBagLayout());

    tasks = new ITable();
    scrollPane = new JScrollPane(tasks);
    addButton = new IButton("Add");
    deleteButton = new IButton("Delete");
    saveButton = new IButton("Save");

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

  public JTable getTable() {
    return tasks;
  }

  public JScrollPane getScrollPane() {
    return scrollPane;
  }

  public JButton getAddButton() {
    return addButton;
  }

  public JButton getDeleteButton() {
    return deleteButton;
  }

  public JButton getSaveButton() {
    return saveButton;
  }
}
