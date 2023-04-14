package com.group89.app.model;

public class MarkRecord {
  private String semester;
  private String moduleCode;
  private String title;
  private int mark;
  private int credits;

  public MarkRecord(String semester, String moduleCode, String title, int mark, int credits) {
    this.semester = semester;
    this.moduleCode = moduleCode;
    this.title = title;
    this.mark = mark;
    this.credits = credits;
  }

  public String getSemester() {
    return semester;
  }

  public String getModuleCode() {
    return moduleCode;
  }

  public String getTitle() {
    return title;
  }

  public int getMark() {
    return mark;
  }

  public int getCredits() {
    return credits;
  }

  public void setSemester(String semester) {
    this.semester = semester;
  }

  public void setModuleCode(String moduleCode) {
    this.moduleCode = moduleCode;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setMark(int mark) {
    this.mark = mark;
  }

  public void setCredits(int credits) {
    this.credits = credits;
  }

  // calculate grade point
  public double getGradePoint() {
    double gradePoint = 0.0;
    if (this.mark >= 60) {
      gradePoint = 4.0 - 3.0 * (100.0 - this.mark) * (100.0 - this.mark) / 1600.0;
    } else {
      gradePoint = 0.0;
    }
    return gradePoint;
  }
}
