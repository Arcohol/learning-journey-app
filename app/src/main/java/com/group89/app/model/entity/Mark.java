package com.group89.app.model.entity;

import com.group89.app.model.enumeration.CourseType;
import com.group89.app.model.enumeration.Semester;
import com.group89.app.utils.MarkConverter;

/**
 * An entity class whose instance represents a mark.
 */
public class Mark {
  private Semester semester;
  private String moduleCode;
  private String title;
  private Integer markCN;
  private Integer markUK;
  private Double creditsCN;
  private Integer creditsUK;
  private CourseType type;

  public Mark(Semester semester, String moduleCode, String title, int markCN, int markUK,
      double creditsCN, int creditsUK, CourseType type) {
    this.semester = semester;
    this.moduleCode = moduleCode;
    this.title = title;
    this.markCN = markCN;
    this.markUK = markUK;
    this.creditsCN = creditsCN;
    this.creditsUK = creditsUK;
    this.type = type;
  }

  public Mark() {
    this(Semester.values()[0], "", "", 0, 0, 0.0, 0, CourseType.COMPULSORY);
  }

  public Semester getSemester() {
    return semester;
  }

  public String getModuleCode() {
    return moduleCode;
  }

  public String getTitle() {
    return title;
  }

  public Integer getMarkCN() {
    return markCN;
  }

  public Integer getMarkUK() {
    return markUK;
  }

  public Double getCreditsCN() {
    return creditsCN;
  }

  public Integer getCreditsUK() {
    return creditsUK;
  }

  public CourseType getType() {
    return type;
  }

  public void setSemester(Semester semester) {
    this.semester = semester;
  }

  public void setModuleCode(String moduleCode) {
    this.moduleCode = moduleCode;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Set the mark in the Chinese scale. If this is a {@code Mark} instance of a Chinese module, the
   * mark will also be automatically converted to update the mark in the UK scale.
   * 
   * @param markCN the mark in Chinese scale
   */
  public void setMarkCN(int markCN) {
    MarkConverter converter = new MarkConverter();

    this.markCN = markCN;
    if (moduleCode.startsWith("BBC")) {
      this.markUK = converter.getUK(markCN);
    }
  }

  /**
   * Set the mark in the UK scale. If this is a {@code Mark} instance of a UK module, the mark will
   * also be automatically converted to update the mark in the Chinese scale.
   * 
   * @param markUK the mark in UK scale
   */
  public void setMarkUK(int markUK) {
    MarkConverter converter = new MarkConverter();

    this.markUK = markUK;
    if (moduleCode.startsWith("EBU")) {
      this.markCN = converter.getCN(markUK);
    }
  }

  public void setCreditsCN(double creditsCN) {
    this.creditsCN = creditsCN;
  }

  public void setCreditsUK(int creditsUK) {
    this.creditsUK = creditsUK;
  }

  public void setType(CourseType type) {
    this.type = type;
  }

  /**
   * Get the grade point of this instance.
   * 
   * @return the grade point
   */
  public double getGradePoint() {
    double gradePoint = 0.0;
    if (markCN >= 60) {
      gradePoint = 4.0 - 3.0 * (100.0 - markCN) * (100.0 - markCN) / 1600.0;
    } else {
      gradePoint = 0.0;
    }
    return gradePoint;
  }
}
