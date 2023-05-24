package com.group89.app.view.comp.tablepage;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import com.group89.app.controller.RolePageController;
import com.group89.app.model.enumeration.ComboBoxItem;
import com.group89.app.model.enumeration.Semester;
import com.group89.app.utils.EnumFactory;
import com.group89.app.view.comp.IComboBox;

public class RolePage extends DefaultTablePage {
  private JComboBox<ComboBoxItem> semesterBox;

  public RolePage() {
    super();

    semesterBox = new IComboBox<>(EnumFactory.addItemAll(Semester.values()));

    GridBagConstraints c = new GridBagConstraints();

    c.insets.set(10, 10, 0, 0);

    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.WEST;
    add(semesterBox, c);

    new RolePageController(this);
  }

  public JComboBox<ComboBoxItem> getSemesterBox() {
    return semesterBox;
  }
}
