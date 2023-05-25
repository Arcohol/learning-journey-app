package com.group89.app.model.entity;

/**
 * An entity class whose instance represents a task.
 */
public class Task {
  private String content;
  private String due;
  private Boolean status;

  public Task(String content, String due, boolean status) {
    this.content = content;
    this.due = due;
    this.status = status;
  }

  public Task() {
    this("", "", false);
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
