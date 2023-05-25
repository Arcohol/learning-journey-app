package com.group89.app.model.enumeration;

/**
 * An enum class that contains the item {@code ALL}.
 */
public enum ItemAll implements ComboBoxItem {
  ALL;

  @Override
  public String toString() {
    return name().charAt(0) + name().substring(1).toLowerCase();
  }
}
