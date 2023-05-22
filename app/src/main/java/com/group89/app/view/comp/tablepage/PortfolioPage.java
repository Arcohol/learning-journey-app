package com.group89.app.view.comp.tablepage;

import java.awt.GridBagConstraints;
import java.util.ArrayList;
import javax.swing.JComboBox;
import com.group89.app.controller.PortfolioPageController;
import com.group89.app.model.PortfolioType;
import com.group89.app.utils.SemesterGenerator;
import com.group89.app.view.comp.IComboBox;

public class PortfolioPage extends DefaultTablePage {
  // public static final String[] SEMESTERS = {"All", "2020-2021-1", "2020-2021-2", "2021-2022-1",
  //     "2021-2022-2", "2022-2023-1", "2022-2023-2", "2023-2024-1", "2023-2024-2"};
  private JComboBox<String> semesterBox;
  private JComboBox<PortfolioType> typeBox;

  public PortfolioPage() {
    super();

    ArrayList<String> semesters = SemesterGenerator.generate();
    semesters.add(0, "All");
    semesterBox = new IComboBox<>(semesters.toArray(new String[0]));
    typeBox = new IComboBox<>(PortfolioType.values());

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
