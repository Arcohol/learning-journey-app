package com.group89.app.model.enumeration;

public class SemesterItemType extends AbstractComboBoxItemType<String> {
  @Override
  public String[] values() {
    int year = java.time.LocalDate.now().getYear() + 4;
    String[] semesters = new String[(year - 2000 + 1) * 2 + 1];

    semesters[0] = "All";
    for (int i = 1; i < semesters.length; i += 2, year--) {
      semesters[i] = year + "-" + (year + 1) + "-2";
      semesters[i + 1] = year + "-" + (year + 1) + "-1";
    }

    return semesters;
  }

  @Override
  public String getItemAll() {
    return "All";
  }
}
