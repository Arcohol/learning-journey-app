package com.group89.app.model.entity;

import com.group89.app.model.enumeration.ApplicationStatus;

public class Application {
  private ApplicationStatus status;
  private String country;
  private String university;
  private String program;
  private Double gpa;
  private Double languageScore;
  private Integer gre;
  private String note;

  public Application(ApplicationStatus status, String country, String university,
      String program, Double gpa, Double languageScore, Integer gre, String note) {
    this.status = status;
    this.country = country;
    this.university = university;
    this.program = program;
    this.gpa = gpa;
    this.languageScore = languageScore;
    this.gre = gre;
    this.note = note;
  }

  public Application() {
    status = ApplicationStatus.PENDING;
    country = "";
    university = "";
    program = "";
    gpa = 0.0;
    languageScore = 0.0;
    gre = 0;
    note = "";
  }

  public ApplicationStatus getStatus() {
    return status;
  }

  public String getCountry() {
    return country;
  }

  public String getUniversity() {
    return university;
  }

  public String getProgram() {
    return program;
  }

  public Double getGpa() {
    return gpa;
  }

  public Double getLanguageScore() {
    return languageScore;
  }

  public Integer getGre() {
    return gre;
  }

  public String getNote() {
    return note;
  }

  public void setStatus(ApplicationStatus status) {
    this.status = status;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setUniversity(String university) {
    this.university = university;
  }

  public void setProgram(String program) {
    this.program = program;
  }

  public void setGpa(Double gpa) {
    this.gpa = gpa;
  }

  public void setLanguageScore(Double languageScore) {
    this.languageScore = languageScore;
  }

  public void setGre(Integer gre) {
    this.gre = gre;
  }

  public void setNote(String note) {
    this.note = note;
  }
}
