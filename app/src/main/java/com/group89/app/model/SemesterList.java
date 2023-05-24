package com.group89.app.model;

import java.util.LinkedList;

public class SemesterList extends LinkedList<String> {
  public SemesterList(boolean withAll) {
    super();

    if (withAll) {
      add("All");
    }

    for (int year = java.time.LocalDate.now().getYear() + 4; year >= 2000; year--) {
      add(year + "-" + (year + 1) + "-2");
      add(year + "-" + (year + 1) + "-1");
    }
  }

  public String[] toArray() {
    return toArray(new String[0]);
  }
}
