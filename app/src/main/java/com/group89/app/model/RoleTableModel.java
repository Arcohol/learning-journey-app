package com.group89.app.model;

import java.util.List;
import com.group89.app.model.enumeration.Semester;
import com.group89.app.model.entity.Role;

/**
 * A table model for roles.
 */
public class RoleTableModel extends ListTableModel<Role> {
  private static final String[] COLUMN_NAMES = {"Semester", "Title", "Content", "Note"};

  /**
   * Constructs a role table model.
   *
   * @param roleRecords the list of roles
   */
  public RoleTableModel(List<Role> roleRecords) {
    super(Role.class, COLUMN_NAMES, roleRecords);
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Role roleRecord = getItem(rowIndex);
    return switch (columnIndex) {
      case 0 -> roleRecord.getSemester();
      case 1 -> roleRecord.getTitle();
      case 2 -> roleRecord.getContent();
      case 3 -> roleRecord.getNote();
      default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
    };
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    Role roleRecord = getItem(rowIndex);
    switch (columnIndex) {
      case 0 -> roleRecord.setSemester((Semester) aValue);
      case 1 -> roleRecord.setTitle((String) aValue);
      case 2 -> roleRecord.setContent((String) aValue);
      case 3 -> roleRecord.setNote((String) aValue);
      default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
    }
    fireTableRowsUpdated(rowIndex, rowIndex);
  }
}
