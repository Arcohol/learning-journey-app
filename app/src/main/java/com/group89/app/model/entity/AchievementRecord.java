package com.group89.app.model.entity;

import com.group89.app.model.AchievementType;

public class AchievementRecord {
  private Integer year;
  private String title;
  private String details;
  private AchievementType achievementType;

  public AchievementRecord(String details, Integer year, String title,
      AchievementType achievementType) {
    this.details = details;
    this.year = year;
    this.title = title;
    this.achievementType = achievementType;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public AchievementType getAchievementType() {
    return achievementType;
  }

  public void setAchievementType(AchievementType achievementType) {
    this.achievementType = achievementType;
  }
}
