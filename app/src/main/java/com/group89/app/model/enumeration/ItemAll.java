package com.group89.app.model.enumeration;

public enum ItemAll implements ComboBoxItem {
  ALL;

  @Override
  public String toString() {
    return name().charAt(0) + name().substring(1).toLowerCase();
  }
}
