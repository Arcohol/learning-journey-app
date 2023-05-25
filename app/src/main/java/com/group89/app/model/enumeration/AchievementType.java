package com.group89.app.model.enumeration;

/**
 * An enum class that represents type of achievement.
 */
public enum AchievementType implements ComboBoxItem {
  AWARD, PRIZE, HONOUR, OTHER;

  @Override
  public String toString() {
    return name().charAt(0) + name().substring(1).toLowerCase();
  }
}
