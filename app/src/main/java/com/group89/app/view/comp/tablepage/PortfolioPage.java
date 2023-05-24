package com.group89.app.view.comp.tablepage;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import com.group89.app.controller.PortfolioPageController;
import com.group89.app.model.PortfolioType;
import com.group89.app.model.SemesterList;
import com.group89.app.view.comp.IComboBox;

public class PortfolioPage extends DefaultTablePage {
  private JComboBox<String> semesterBox;
  private JComboBox<PortfolioType> typeBox;

  public PortfolioPage() {
    super();

    semesterBox = new IComboBox<>(new SemesterList(true).toArray());
    typeBox = new IComboBox<>(PortfolioType.values());

    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.WEST;

    c.gridx = 0;
    c.gridy = 0;
    getHeader().add(semesterBox, c);

    c.insets.set(0, 10, 0, 0);

    c.gridx = 1;
    c.gridy = 0;
    c.weightx = 1;
    getHeader().add(typeBox, c);

    new PortfolioPageController(this);
  }

  public JComboBox<String> getSemesterBox() {
    return semesterBox;
  }

  public JComboBox<PortfolioType> getTypeBox() {
    return typeBox;
  }
}
