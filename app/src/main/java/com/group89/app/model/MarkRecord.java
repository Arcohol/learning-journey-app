package com.group89.app.model;

public class MarkRecord {
  private String semester;
  private String moduleCode;
  private String title;
  private Integer mark;
  private Integer markQMUL;
  private Double credits;
  private Integer creditsQMUL;

  public MarkRecord(String semester, String moduleCode, String title, int mark, int markQMUL, double credits, int creditsQMUL) {
    this.semester = semester;
    this.moduleCode = moduleCode;
    this.title = title;
    this.mark = mark;
    this.markQMUL = markQMUL;
    this.credits = credits;
    this.creditsQMUL = creditsQMUL;
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

  public Integer getMarkQMUL() {
    return markQMUL;
  }

  public double getCredits() {
    return credits;
  }

  public int getCreditsQMUL() {
    return creditsQMUL;
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

  public void setMarkQMUL(int markQMUL) {
    this.markQMUL = markQMUL;
  }

  public void setCredits(double credits) {
    this.credits = credits;
  }

  public void setCreditsQMUL(int creditsQMUL) {
    this.creditsQMUL = creditsQMUL;
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
