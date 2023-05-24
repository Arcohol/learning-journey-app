package com.group89.app.model.entity;

import com.group89.app.model.enumeration.AchievementType;
import com.group89.app.model.enumeration.Semester;

public class AchievementRecord {
  private Semester semester;
  private String title;
  private String details;
  private AchievementType type;

  public AchievementRecord(Semester semester, String title, String details, AchievementType type) {
    setSemester(semester);
    this.title = title;
    this.details = details;
    this.type = type;
  }

  public AchievementRecord() {
    this(Semester.values()[0], "", "", AchievementType.OTHER);
  }

  public Semester getSemester() {
    return semester.intern();
  }

  public String getTitle() {
    return title;
  }

  public String getDetails() {
    return details;
  }

  public AchievementType getType() {
    return type;
  }

  public void setSemester(Semester semester) {
    this.semester = semester.intern();
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public void setType(AchievementType type) {
    this.type = type;
  }
}