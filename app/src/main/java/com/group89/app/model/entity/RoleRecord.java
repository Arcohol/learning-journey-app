package com.group89.app.model.entity;

public class RoleRecord {
  private String semester;
  private String title;
  private String content;
  private String note;

  public RoleRecord(String semester, String title, String content, String note) {
    this.semester = semester;
    this.title = title;
    this.content = content;
    this.note = note;
  }

  public RoleRecord() {
    semester = "";
    title = "";
    content = "";
    note = "";
  }

  public String getSemester() {
    return semester;
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

  public void setSemester(String semester) {
    this.semester = semester;
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
