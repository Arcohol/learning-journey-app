package com.group89.app.model.entity;

import com.group89.app.model.enumeration.PortfolioType;
import com.group89.app.model.enumeration.Semester;

/**
 * An entity class whose instance represents a portfolio.
 */
public class Portfolio {
  private Semester semester;
  private PortfolioType type;
  private String exhibition;
  private String details;

  public Portfolio(Semester semester, PortfolioType type, String exhibition, String details) {
    this.semester = semester;
    this.type = type;
    this.exhibition = exhibition;
    this.details = details;
  }

  public Portfolio() {
    this(Semester.values()[0], PortfolioType.OTHER, "", "");
  }

  public Semester getSemester() {
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

  public void setSemester(Semester semester) {
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
