package com.group89.app.view.comp.tablepage;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import com.group89.app.controller.AchievementPageController;
import com.group89.app.model.enumeration.AbstractComboBoxItemType;
import com.group89.app.model.enumeration.AchievementType;
import com.group89.app.model.enumeration.AchievementItemType;
import com.group89.app.model.enumeration.SemesterItemType;
import com.group89.app.view.comp.IComboBox;

public class AchievementPage extends DefaultTablePage {
  private JComboBox<String> semesterBox;
  private JComboBox<AchievementType> typeBox;

  public AchievementPage() {
    super();

    AbstractComboBoxItemType<String> semesterType = new SemesterItemType();
    AbstractComboBoxItemType<AchievementType> achievementType = new AchievementItemType();
    semesterBox = new IComboBox<>(semesterType.values());
    typeBox = new IComboBox<>(achievementType.values());

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
