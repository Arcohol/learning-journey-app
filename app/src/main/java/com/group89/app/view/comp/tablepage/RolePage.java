package com.group89.app.view.comp.tablepage;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import com.group89.app.controller.RolePageController;
import com.group89.app.model.enumeration.ComboBoxItem;
import com.group89.app.model.enumeration.Semester;
import com.group89.app.utils.EnumFactory;
import com.group89.app.view.comp.IComboBox;

/**
 * A table page for the role table.
 */
public class RolePage extends DefaultTablePage {
  private JComboBox<ComboBoxItem> semesterBox;

  public RolePage() {
    super();

    semesterBox = new IComboBox<>(EnumFactory.addItemAll(Semester.values()));

    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.WEST;

    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1;
    getHeader().add(semesterBox, c);

    new RolePageController(this);
  }

  /**
   * Returns the semester combo box.
   * 
   * @return the semester combo box
   */
  public JComboBox<ComboBoxItem> getSemesterBox() {
    return semesterBox;
  }
}
