package com.group89.app.model.entity;

import com.group89.app.model.PortfolioType;

public class PortfolioRecord {
  private String semester;
  private PortfolioType type;
  private String exhibition;
  private String details;

  public PortfolioRecord(String semester, PortfolioType type, String exhibition, String details) {
    this.semester = semester;
    this.type = type;
    this.exhibition = exhibition;
    this.details = details;
  }

  public PortfolioRecord() {
    this.semester = "";
    this.type = PortfolioType.OTHER;
    this.exhibition = "";
    this.details = "";
  }

  public String getSemester() {
    return semester;
  }

  public PortfolioType getType() {
    return type;
  }

  public String getExhibition() {
    return exhibition;
  }

  public String getDetails() {
    return details;
  }

  public void setSemester(String semester) {
    this.semester = semester;
  }

  public void setType(PortfolioType type) {
    this.type = type;
  }

  public void setExhibition(String exhibition) {
    this.exhibition = exhibition;
  }

  public void setDetails(String details) {
    this.details = details;
  }
}
