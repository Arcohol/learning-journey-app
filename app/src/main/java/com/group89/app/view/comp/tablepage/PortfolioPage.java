package com.group89.app.view.comp.tablepage;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import com.group89.app.controller.PortfolioPageController;
import com.group89.app.model.enumeration.AbstractComboBoxItemType;
import com.group89.app.model.enumeration.PortfolioType;
import com.group89.app.model.enumeration.PortfolioItemType;
import com.group89.app.model.enumeration.SemesterItemType;
import com.group89.app.view.comp.IComboBox;

public class PortfolioPage extends DefaultTablePage {
  private JComboBox<String> semesterBox;
  private JComboBox<PortfolioType> typeBox;

  public PortfolioPage() {
    super();

    AbstractComboBoxItemType<String> semesterType = new SemesterItemType();
    AbstractComboBoxItemType<PortfolioType> portfolioType = new PortfolioItemType();
    semesterBox = new IComboBox<>(semesterType.values());
    typeBox = new IComboBox<>(portfolioType.values());

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

  public JComboBox<String> getSemesterBox() {
    return semesterBox;
  }

  public JComboBox<PortfolioType> getTypeBox() {
    return typeBox;
  }
}
