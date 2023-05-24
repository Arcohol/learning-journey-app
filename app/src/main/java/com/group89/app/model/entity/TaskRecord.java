package com.group89.app.model.entity;

public class TaskRecord {
  private String content;
  private String due;
  private Boolean status;

  public TaskRecord(String content, String due, boolean status) {
    this.content = content;
    this.due = due;
    this.status = status;
  }

  public TaskRecord() {
    content = "";
    due = "";
    status = false;
  }

  public String getContent() {
    return content;
  }

  public String getDue() {
    return due;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setDue(String due) {
    this.due = due;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }
}
