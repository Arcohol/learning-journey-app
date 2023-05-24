package com.group89.app.view.comp.tablepage;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import com.group89.app.controller.RolePageController;
import com.group89.app.model.SemesterList;
import com.group89.app.view.comp.IComboBox;

public class RolePage extends DefaultTablePage {
  private JComboBox<String> semesterBox;

  public RolePage() {
    super();

    semesterBox = new IComboBox<>(new SemesterList(true).toArray());

    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.WEST;

    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1;
    getHeader().add(semesterBox, c);

    new RolePageController(this);
  }

  public JComboBox<String> getSemesterBox() {
    return semesterBox;
  }
}
