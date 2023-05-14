package com.group89.app.model.entity;

import com.group89.app.model.AchievementType;

public class AchievementRecord {
  private String semester;
  private String title;
  private String details;
  private AchievementType achievementType;

  public AchievementRecord(String details, String semester, String title,
      AchievementType achievementType) {
    this.details = details;
    this.semester = semester;
    this.title = title;
    this.achievementType = achievementType;
  }

  public AchievementRecord() {
    this("", "", "", AchievementType.OTHER);
  }

  public String getDetails() {
    return details;
  }

  public String getTitle() {
    return title;
  }

  public String getSemester() {
    return semester;
  }

  public AchievementType getAchievementType() {
    return achievementType;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setSemester(String semester) {
    this.semester = semester;
  }

  public void setAchievementType(AchievementType achievementType) {
    this.achievementType = achievementType;
  }
}
