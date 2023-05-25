package com.group89.app.model.enumeration;

public enum CourseType implements ComboBoxItem {
  COMPULSORY, ELECTIVE, OPTIONAL;

  @Override
  public String toString() {
    return name().charAt(0) + name().substring(1).toLowerCase();
  }
}
