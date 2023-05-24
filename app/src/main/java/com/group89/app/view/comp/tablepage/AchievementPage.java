package com.group89.app.view.comp.tablepage;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import com.group89.app.controller.AchievementPageController;
import com.group89.app.model.AchievementType;
import com.group89.app.model.SemesterList;
import com.group89.app.view.comp.IComboBox;

/**
 * A table page for the achievement table.
 */
public class AchievementPage extends DefaultTablePage {
  private JComboBox<String> semesterBox;
  private JComboBox<AchievementType> typeBox;

  public AchievementPage() {
    super();

    semesterBox = new IComboBox<>(new SemesterList(true).toArray());
    typeBox = new IComboBox<>(AchievementType.values());

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

    new AchievementPageController(this);
  }

  /**
   * Returns the semester combo box.
   * 
   * @return the semester combo box
   */
  public JComboBox<String> getSemesterBox() {
    return semesterBox;
  }

  /**
   * Returns the type combo box.
   * 
   * @return the type combo box
   */
  public JComboBox<AchievementType> getTypeBox() {
    return typeBox;
  }
}
