package com.group89.app.view.comp;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.group89.app.view.AppColor;

/**
 * A JPanel with a fixed background color. This is the footer of the table page. It contains three
 * buttons: add, delete, and save.
 * 
 * @see com.group89.app.view.comp.tablepage.DefaultTablePage
 */
public class Footer extends JPanel {
  private JButton addButton, deleteButton, saveButton;

  /**
   * Constructs a footer.
   */
  public Footer() {
    super(new GridBagLayout());

    setBackground(AppColor.WHITE);

    addButton = new IButton("Add");
    deleteButton = new IButton("Delete");
    saveButton = new IButton("Save");

    GridBagConstraints c = new GridBagConstraints();
    c.weightx = 1;

    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.WEST;
    add(addButton, c);

    c.gridx = 1;
    c.gridy = 0;
    c.anchor = GridBagConstraints.CENTER;
    add(deleteButton, c);

    c.gridx = 2;
    c.gridy = 0;
    c.anchor = GridBagConstraints.EAST;
    add(saveButton, c);
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
