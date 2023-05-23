package com.group89.app.model.enumeration;

public class TaskStatusComboBoxItemType extends AbstractComboBoxItemType<String> {
  @Override
  public String[] values() {
    return new String[] {"All", "Completed", "In Progress"};
  }

  @Override
  public String getItemAll() {
    return "All";
  }
}
