package com.group89.app.model.enumeration;

public class CourseTypeComboBoxItemType extends AbstractComboBoxItemType<CourseType> {
  @Override
  public CourseType[] values() {
    return CourseType.values();
  }

  @Override
  public CourseType getItemAll() {
    return CourseType.ALL;
  }
}
