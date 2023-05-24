package com.group89.app.view.comp.tablepage;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import com.group89.app.controller.PortfolioPageController;
import com.group89.app.model.enumeration.ComboBoxItem;
import com.group89.app.model.enumeration.PortfolioType;
import com.group89.app.model.enumeration.Semester;
import com.group89.app.utils.EnumFactory;
import com.group89.app.view.comp.IComboBox;

public class PortfolioPage extends DefaultTablePage {
  private JComboBox<ComboBoxItem> semesterBox, typeBox;

  public PortfolioPage() {
    super();

    semesterBox = new IComboBox<>(EnumFactory.addItemAll(Semester.values()));
    typeBox = new IComboBox<>(EnumFactory.addItemAll(PortfolioType.values()));

    GridBagConstraints c = new GridBagConstraints();

    c.insets.set(10, 10, 0, 0);

    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.WEST;
    add(semesterBox, c);

    c.gridx = 1;
    c.gridy = 0;
    c.anchor = GridBagConstraints.WEST;
    add(typeBox, c);

    new PortfolioPageController(this);
  }

  public JComboBox<ComboBoxItem> getSemesterBox() {
    return semesterBox;
  }

  public JComboBox<ComboBoxItem> getTypeBox() {
    return typeBox;
  }
}
