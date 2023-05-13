package com.group89.app.model;

import com.group89.app.controller.RolePageController;
import com.group89.app.utils.JsonConverter;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class RoleTableModel extends ListTableModel<Role> {
    private static final String[] COLUMN_NAMES = {"Semester", "Type", "Details"};

    public RoleTableModel(List<Role> records) {
        super(Role.class, COLUMN_NAMES, records);
//        System.out.println(records);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Role record = getItem(rowIndex);
        return switch (columnIndex) {
            case 0 -> record.getSemester();
            case 1 -> record.getType();
            case 2 -> record.getDetails();
            default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Role record = getItem(rowIndex);
        switch (columnIndex) {
            case 0 -> record.setSemester((String) aValue);
            case 1 -> record.setType((String) aValue);
            case 2 -> record.setDetails((String) aValue);
            default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
        }
        fireTableRowsUpdated(rowIndex, rowIndex);
    }
}




