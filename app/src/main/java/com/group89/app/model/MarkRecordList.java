package com.group89.app.model;

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

  // TODO: gpa calculation
  public double getGPA() {
    return 0;
  }

  // TODO: average mark calculation
  public double getAverageMark() {
    return 0;
  }
}
