package com.group89.app.model.enumeration;

public class ApplicationStatusItemType extends AbstractComboBoxItemType<ApplicationStatus> {
  @Override
  public ApplicationStatus[] values() {
    return ApplicationStatus.values();
  }

  @Override
  public ApplicationStatus getItemAll() {
    return ApplicationStatus.ALL;
  }
}
