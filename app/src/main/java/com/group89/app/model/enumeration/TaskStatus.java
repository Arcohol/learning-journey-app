package com.group89.app.model.enumeration;
/**
 * An enum class that represents status of task.
 */
public enum TaskStatus implements ComboBoxItem {
  OPEN, COMPLETED;

  @Override
  public String toString() {
    return name().charAt(0) + name().substring(1).toLowerCase();
  }
}
