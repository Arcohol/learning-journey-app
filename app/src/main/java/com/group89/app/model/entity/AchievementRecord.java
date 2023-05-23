package com.group89.app.model.entity;

import com.group89.app.model.enumeration.AchievementType;

public class AchievementRecord {
  private String semester;
  private String title;
  private String details;
  private AchievementType type;

  public AchievementRecord(String semester, String title, String details, AchievementType type) {
    this.semester = semester;
    this.title = title;
    this.details = details;
    this.type = type;
  }

  public AchievementRecord() {
    this("", "", "", AchievementType.OTHER);
  }

  public String getSemester() {
    return semester;
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

  public void setSemester(String semester) {
    this.semester = semester;
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
