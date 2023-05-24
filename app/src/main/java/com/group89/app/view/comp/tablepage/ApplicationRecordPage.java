package com.group89.app.view.comp.tablepage;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import com.group89.app.controller.ApplicationRecordPageController;
import com.group89.app.model.ApplicationStatus;
import com.group89.app.view.comp.IComboBox;

public class ApplicationRecordPage extends DefaultTablePage {
  private JComboBox<ApplicationStatus> statusBox;

  public ApplicationRecordPage() {
    super();

    statusBox = new IComboBox<>(ApplicationStatus.values());

    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.WEST;

    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1;
    getHeader().add(statusBox, c);

    new ApplicationRecordPageController(this);
  }

  public JComboBox<ApplicationStatus> getStatusBox() {
    return statusBox;
  }
}
