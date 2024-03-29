package com.group89.app.model;

import java.util.List;
import com.group89.app.model.enumeration.AchievementType;
import com.group89.app.model.enumeration.Semester;
import com.group89.app.model.entity.Achievement;

/**
 * A table model for achievements.
 */
public class AchievementTableModel extends ListTableModel<Achievement> {
  private static final String[] COLUMN_NAMES = {"Semester", "Title", "Detail", "Type"};

  /**
   * Constructs an achievement table model.
   *
   * @param achievementRecords the list of achievements
   */
  public AchievementTableModel(List<Achievement> achievementRecords) {
    super(Achievement.class, COLUMN_NAMES, achievementRecords);
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Achievement achievementRecord = getItem(rowIndex);
    return switch (columnIndex) {
      case 0 -> achievementRecord.getSemester();
      case 1 -> achievementRecord.getTitle();
      case 2 -> achievementRecord.getDetails();
      case 3 -> achievementRecord.getType();
      default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
    };
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    Achievement achievementRecord = getItem(rowIndex);
    switch (columnIndex) {
      case 0 -> achievementRecord.setSemester((Semester) aValue);
      case 1 -> achievementRecord.setTitle((String) aValue);
      case 2 -> achievementRecord.setDetails((String) aValue);
      case 3 -> achievementRecord.setType((AchievementType) aValue);
      default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
    }
    fireTableRowsUpdated(rowIndex, rowIndex);
  }
}
