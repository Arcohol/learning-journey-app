package com.group89.app.model.enumeration;

import java.util.Arrays;

public class Semester implements ComboBoxItem, Comparable<Semester> {
  private static final Semester[] semesters;

  static {
    int year = java.time.LocalDate.now().getYear() + 4;
    semesters = new Semester[(year - 2000 + 1) * 2];
    for (int i = 0; i < semesters.length; i += 2, year--) {
      semesters[i] = new Semester(year + "-" + (year + 1) + "-2");
      semesters[i + 1] = new Semester(year + "-" + (year + 1) + "-1");
    }
  }

  public static Semester[] values() {
    return Arrays.copyOf(semesters, semesters.length);
  }

  public static Semester valueOf(String name) {
    return Arrays.stream(semesters).filter(semester -> semester.toString().equals(name)).findAny()
        .orElseThrow(IllegalArgumentException::new);
  }

  private final String semester;

  private Semester(String semester) {
    this.semester = semester;
  }

  public final String name() {
    return semester;
  }

  public Semester intern() {
    return Semester.valueOf(name());
  }

  @Override
  public String toString() {
    return name();
  }

  @Override
  public int compareTo(Semester o) {
    return name().compareTo(o.name());
  }
}
