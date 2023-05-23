package com.group89.app.model;

import java.util.List;
import com.group89.app.model.entity.ApplicationRecord;
import com.group89.app.model.enumeration.ApplicationStatus;

public class ApplicationRecordTableModel extends ListTableModel<ApplicationRecord> {
  private static final String[] COLUMN_NAMES =
      {"Status", "Country", "University", "Program", "GPA", "Language", "GRE", "Note"};

  public ApplicationRecordTableModel(List<ApplicationRecord> applicationRecords) {
    super(ApplicationRecord.class, COLUMN_NAMES, applicationRecords);
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    ApplicationRecord applicationRecord = getItem(rowIndex);
    return switch (columnIndex) {
      case 0 -> applicationRecord.getStatus();
      case 1 -> applicationRecord.getCountry();
      case 2 -> applicationRecord.getUniversity();
      case 3 -> applicationRecord.getProgram();
      case 4 -> applicationRecord.getGpa();
      case 5 -> applicationRecord.getLanguageScore();
      case 6 -> applicationRecord.getGre();
      case 7 -> applicationRecord.getNote();
      default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
    };
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    ApplicationRecord applicationRecord = getItem(rowIndex);
    switch (columnIndex) {
      case 0 -> applicationRecord.setStatus((ApplicationStatus) aValue);
      case 1 -> applicationRecord.setCountry((String) aValue);
      case 2 -> applicationRecord.setUniversity((String) aValue);
      case 3 -> applicationRecord.setProgram((String) aValue);
      case 4 -> applicationRecord.setGpa((Double) aValue);
      case 5 -> applicationRecord.setLanguageScore((Double) aValue);
      case 6 -> applicationRecord.setGre((Integer) aValue);
      case 7 -> applicationRecord.setNote((String) aValue);
      default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
    }
    fireTableRowsUpdated(rowIndex, rowIndex);
  }
}
