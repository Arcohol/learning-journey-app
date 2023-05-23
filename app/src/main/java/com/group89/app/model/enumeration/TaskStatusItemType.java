package com.group89.app.model.enumeration;

public class TaskStatusItemType extends AbstractComboBoxItemType<String> {
  @Override
  public String[] values() {
    return new String[] {"All", "Completed", "In progress"};
  }

  @Override
  public String getItemAll() {
    return "All";
  }
}
