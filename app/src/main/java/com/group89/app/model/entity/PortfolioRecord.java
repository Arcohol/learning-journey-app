package com.group89.app.model.entity;

import com.group89.app.model.PortfolioType;

public class PortfolioRecord {
  private String semester;
  private PortfolioType type;
  private String exhibition;
  private String details;

  public PortfolioRecord(String details, String semester, PortfolioType courseType,
      String exhibition) {
    this.details = details;
    this.exhibition = exhibition;
    this.type = courseType;
    this.semester = semester;
  }

  public PortfolioRecord() {
    this("", "", PortfolioType.OTHER, "");
  }

  public PortfolioType getType() {
    return type;
  }

  public String getSemester() {
    return semester;
  }

  public String getExhibition() {
    return exhibition;
  }

  public String getDetails() {
    return details;
  }

  public void setType(PortfolioType type) {
    this.type = type;
  }

  public void setSemester(String semester) {
    this.semester = semester;
  }

  public void setExhibition(String exhibition) {
    this.exhibition = exhibition;
  }

  public void setDetails(String details) {
    this.details = details;
  }
}
