package com.group89.app.view.comp.tablepage;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import com.group89.app.controller.RolePageController;
import com.group89.app.model.SemesterList;
import com.group89.app.model.enumeration.AbstractComboBoxItemType;
import com.group89.app.model.enumeration.SemesterComboBoxItemType;
import com.group89.app.view.comp.IComboBox;

public class RolePage extends DefaultTablePage {
  private JComboBox<String> semesterBox;

  public RolePage() {
    super();

    AbstractComboBoxItemType<String> semesterType = new SemesterComboBoxItemType();
    semesterBox = new IComboBox<>(semesterType.values());

    GridBagConstraints c = new GridBagConstraints();

    c.insets.set(10, 10, 0, 0);

    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.WEST;
    add(semesterBox, c);

    new RolePageController(this);
  }

  public JComboBox<String> getSemesterBox() {
    return semesterBox;
  }
}
