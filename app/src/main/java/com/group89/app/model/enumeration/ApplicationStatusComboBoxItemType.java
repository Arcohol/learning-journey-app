package com.group89.app.model.enumeration;

public class ApplicationStatusComboBoxItemType extends AbstractComboBoxItemType<ApplicationStatus> {
  @Override
  public ApplicationStatus[] values() {
    return ApplicationStatus.values();
  }
}
