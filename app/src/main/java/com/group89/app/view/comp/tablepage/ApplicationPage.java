package com.group89.app.view.comp.tablepage;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import com.group89.app.controller.ApplicationPageController;
import com.group89.app.model.ApplicationStatus;
import com.group89.app.view.comp.IComboBox;

/**
 * A table page for the application table.
 */
public class ApplicationPage extends DefaultTablePage {
  private JComboBox<ApplicationStatus> statusBox;

  public ApplicationPage() {
    super();

    statusBox = new IComboBox<>(ApplicationStatus.values());

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
  public JComboBox<ApplicationStatus> getStatusBox() {
    return statusBox;
  }
}
