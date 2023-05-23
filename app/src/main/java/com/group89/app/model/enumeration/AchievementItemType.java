package com.group89.app.model.enumeration;

public class AchievementItemType extends AbstractComboBoxItemType<AchievementType> {
  @Override
  public AchievementType[] values() {
    return AchievementType.values();
  }

  @Override
  public AchievementType getItemAll() {
    return AchievementType.ALL;
  }
}
