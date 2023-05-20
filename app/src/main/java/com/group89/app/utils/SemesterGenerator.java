package com.group89.app.utils;

import java.util.ArrayList;

public class SemesterGenerator {
  public static ArrayList<String> generate() {
    ArrayList<String> semesters = new ArrayList<>();
    for (int year = java.time.LocalDate.now().getYear() + 4; year >= 2000; year--) {
      semesters.add(year + "-" + (year + 1) + "-1");
      semesters.add(year + "-" + (year + 1) + "-2");
    }
    return semesters;
  }
}
