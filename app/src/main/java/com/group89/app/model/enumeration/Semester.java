package com.group89.app.model.enumeration;

public enum Semester implements ComboBoxItem {
  Y2020_2021_1,
  Y2020_2021_2,
  Y2021_2022_1,
  Y2021_2022_2,
  Y2022_2023_1,
  Y2022_2023_2,
  Y2023_2024_1,
  Y2023_2024_2;

  @Override
  public String toString() {
    return name().substring(1).replace("_", "-");
  }
}
