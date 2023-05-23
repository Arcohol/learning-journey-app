package com.group89.app.view.comp.tablepage;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import com.group89.app.controller.AchievementPageController;
import com.group89.app.model.SemesterList;
import com.group89.app.model.enumeration.AchievementType;
// import com.group89.app.utils.SemesterGenerator;
import com.group89.app.view.comp.IComboBox;

public class AchievementPage extends DefaultTablePage {
  // public static final String[] SEMESTERS = {"All", "2020-2021-1", "2020-2021-2", "2021-2022-1",
  // "2021-2022-2", "2022-2023-1", "2022-2023-2", "2023-2024-1", "2023-2024-2"};
  private JComboBox<String> semesterBox;
  private JComboBox<AchievementType> typeBox;

  public AchievementPage() {
    super();

    semesterBox = new IComboBox<>(new SemesterList(true).toArray());
    typeBox = new IComboBox<>(AchievementType.values());

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

    new AchievementPageController(this);
  }

  public JComboBox<String> getSemesterBox() {
    return semesterBox;
  }

  public JComboBox<AchievementType> getTypeBox() {
    return typeBox;
  }
}
