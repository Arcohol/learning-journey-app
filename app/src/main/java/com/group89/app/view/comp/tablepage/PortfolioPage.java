package com.group89.app.view.comp.tablepage;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import com.group89.app.controller.PortfolioPageController;
import com.group89.app.view.comp.MyComboBox;

public class PortfolioPage extends DefaultTablePage {
  public static final String[] SEMESTERS = {"all", "2020-2021-1", "2020-2021-2", "2021-2022-1",
      "2021-2022-2", "2022-2023-1", "2022-2023-2", "2023-2024-1", "2023-2024-2"};
  private JComboBox<String> semesterBox;

  public PortfolioPage() {
    super();

    semesterBox = new MyComboBox<>(SEMESTERS);

    GridBagConstraints c = new GridBagConstraints();

    c.insets.set(10, 10, 0, 0);

    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.WEST;
    add(semesterBox, c);

    new PortfolioPageController(this);
  }
}
