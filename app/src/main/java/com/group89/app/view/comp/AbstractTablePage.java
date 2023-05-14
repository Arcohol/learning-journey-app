package com.group89.app.view.comp;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public abstract class AbstractTablePage extends JPanel {
  // assume that all table pages should have three buttons: add, delete, and save
  protected JButton addButton, deleteButton, saveButton;
  protected JTable table;
  protected JScrollPane scrollPane;

  public JTable getTable() {
    return table;
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
