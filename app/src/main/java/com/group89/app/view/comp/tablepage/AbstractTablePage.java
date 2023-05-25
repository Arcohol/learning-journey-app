package com.group89.app.view.comp.tablepage;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * This class defines and provides a skeletal implementation of a table page.
 * 
 * @see com.group89.app.view.comp.tablepage.DefaultTablePage
 */
public abstract class AbstractTablePage extends JPanel {
  protected JButton addButton, deleteButton, saveButton;
  protected JTable table;
  protected JScrollPane scrollPane;

  /**
   * Returns the table.
   * 
   * @return the table
   */
  public JTable getTable() {
    return table;
  }

  /**
   * Returns the scroll pane.
   * 
   * @return the scroll pane
   */
  public JScrollPane getScrollPane() {
    return scrollPane;
  }

  /**
   * Returns the add button.
   * 
   * @return the add button
   */
  public JButton getAddButton() {
    return addButton;
  }

  /**
   * Returns the delete button.
   * 
   * @return the delete button
   */
  public JButton getDeleteButton() {
    return deleteButton;
  }

  /**
   * Returns the save button.
   * 
   * @return the save button
   */
  public JButton getSaveButton() {
    return saveButton;
  }
}
