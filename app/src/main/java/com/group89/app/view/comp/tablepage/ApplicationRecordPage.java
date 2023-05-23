package com.group89.app.view.comp.tablepage;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import com.group89.app.controller.ApplicationRecordPageController;
import com.group89.app.model.enumeration.ApplicationStatus;
import com.group89.app.model.enumeration.ApplicationStatusComboBoxItemType;
import com.group89.app.view.comp.IComboBox;

public class ApplicationRecordPage extends DefaultTablePage {
  private JComboBox<ApplicationStatus> statusBox;

  public ApplicationRecordPage() {
    super();

    statusBox = new IComboBox<>(new ApplicationStatusComboBoxItemType().values());

    GridBagConstraints c = new GridBagConstraints();

    c.insets.set(10, 10, 0, 0);

    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.WEST;
    add(statusBox, c);

    new ApplicationRecordPageController(this);
  }

  public JComboBox<ApplicationStatus> getStatusBox() {
    return statusBox;
  }
}
