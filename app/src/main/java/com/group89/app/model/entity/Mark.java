package com.group89.app.model.entity;

import com.group89.app.model.CourseType;
import com.group89.app.utils.MarkConverter;

public class Mark {
  private String semester;
  private String moduleCode;
  private String title;
  private Integer markCN;
  private Integer markUK;
  private Double creditsCN;
  private Integer creditsUK;
  private CourseType type;

  public Mark(String semester, String moduleCode, String title, int markCN, int markUK,
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
    semester = "";
    moduleCode = "";
    title = "";
    markCN = 0;
    markUK = 0;
    creditsCN = 0.0;
    creditsUK = 0;
    type = CourseType.COMPULSORY;
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

  public void setSemester(String semester) {
    this.semester = semester;
  }

  public void setModuleCode(String moduleCode) {
    this.moduleCode = moduleCode;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setMarkCN(int markCN) {
    MarkConverter converter = new MarkConverter();

    this.markCN = markCN;
    if (moduleCode.startsWith("BBC")) {
      this.markUK = converter.getUK(markCN);
    }
  }

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
