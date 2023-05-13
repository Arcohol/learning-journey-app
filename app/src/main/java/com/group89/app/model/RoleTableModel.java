package com.group89.app.model;

import java.util.List;

public class RoleTableModel extends ListTableModel<Role> {
    private static final String[] COLUMN_NAMES = {"Semester", "Type", "Details"};

    public RoleTableModel(List<Role> roles) {
        super(Role.class, COLUMN_NAMES, roles);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Role role = getItem(rowIndex);
        if (columnIndex == 0) {
            return role.getSemester();
        } else if (columnIndex == 1) {
            return role.getType();
        } else {
            return role.getDetails();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Role role = getItem(rowIndex);
        if (columnIndex == 0) {
            role.setSemester((String) aValue);
        } else if (columnIndex == 1) {
            role.setType((String) aValue);
        } else {
            role.setDetails((String) aValue);
        }
        fireTableRowsUpdated(rowIndex, rowIndex);
    }
}




