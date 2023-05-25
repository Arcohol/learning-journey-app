package com.group89.app.model.enumeration;

public enum AchievementType implements ComboBoxItem {
  AWARD, PRIZE, HONOUR, OTHER;

  @Override
  public String toString() {
    return name().charAt(0) + name().substring(1).toLowerCase();
  }
}
