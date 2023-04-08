package com.group89.app.model;

public class MarkRecord {
  private String moduleCode;
  private String title;
  private int mark;
  private int credits;

  public MarkRecord(String moduleCode, String title, int mark, int credits) {
    this.moduleCode = moduleCode;
    this.title = title;
    this.mark = mark;
    this.credits = credits;
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
}
