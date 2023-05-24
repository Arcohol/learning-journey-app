package com.group89.app.model.entity;

import com.group89.app.model.enumeration.Semester;

public class Role {
  private Semester semester;
  private String title;
  private String content;
  private String note;

  public Role(Semester semester, String title, String content, String note) {
    setSemester(semester);
    this.title = title;
    this.content = content;
    this.note = note;
  }

  public Role() {
    this(Semester.values()[0], "", "", "");
  }

  public Semester getSemester() {
    return semester.intern();
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public String getNote() {
    return note;
  }

  public void setSemester(Semester semester) {
    this.semester = semester.intern();
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setNote(String note) {
    this.note = note;
  }
}
