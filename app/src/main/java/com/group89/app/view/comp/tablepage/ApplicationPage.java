package com.group89.app.view.comp.tablepage;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import com.group89.app.model.enumeration.ApplicationStatus;
import com.group89.app.model.enumeration.ComboBoxItem;
import com.group89.app.utils.EnumFactory;
import com.group89.app.view.comp.IComboBox;
import com.group89.app.controller.ApplicationPageController;

/**
 * A table page for the application table.
 */
public class ApplicationPage extends DefaultTablePage {
  private JComboBox<ComboBoxItem> statusBox;

  public ApplicationPage() {
    super();

    statusBox = new IComboBox<>(EnumFactory.addItemAll(ApplicationStatus.values()));

    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.WEST;

    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1;
    getHeader().add(statusBox, c);

    new ApplicationPageController(this);
  }

  /**
   * Returns the status combo box.
   * 
   * @return the status combo box
   */
  public JComboBox<ComboBoxItem> getStatusBox() {
    return statusBox;
  }
}
