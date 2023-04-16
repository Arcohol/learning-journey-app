package com.group89.app.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MarkRecordList extends ArrayList<MarkRecord> {
  public MarkRecordList(List<MarkRecord> list) {
    super(list);
  }

  public int getTotalCredits() {
    int total = 0;
    for (MarkRecord record : this) {
      total += record.getCredits();
    }
    return total;
  }

  public double getGPA() {
    double gpa = 0.0;
    for (MarkRecord record : this) {
      gpa += record.getGradePoint() * record.getCredits();
    }
    gpa /= this.getTotalCredits();
    DecimalFormat df = new DecimalFormat("#.##");
    return Double.parseDouble(df.format(gpa));
  }

  public double getAverageMark() {
    double average = 0;
    for (MarkRecord record : this) {
      average += (double) record.getMark() * record.getCredits();
    }
    average /= this.getTotalCredits();
    DecimalFormat df = new DecimalFormat("#.##");
    return Double.parseDouble(df.format(average));
  }
}
