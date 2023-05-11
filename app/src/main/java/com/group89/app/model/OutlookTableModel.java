package com.group89.app.model;

import java.util.List;

public class OutlookTableModel extends ListTableModel<Outlook> {
    private static final String[] COLUMN_NAMES = {"Name", "Project", "Type", "Rank", "Country", "Cost"};

    public OutlookTableModel(List<Outlook> outlooks) {
        super(Outlook.class, COLUMN_NAMES, outlooks);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Outlook Outlook = getItem(rowIndex);
        return switch (columnIndex) {
            case 0 -> Outlook.getName();
            case 1 -> Outlook.getProject();
            case 2 -> Outlook.getType();
            case 3 -> Outlook.getRank();
            case 4 -> Outlook.getCountry();
            case 5 -> Outlook.getCost();
            default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
        };
    }

    @Override
    //deleted
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Outlook Outlook = getItem(rowIndex);
        switch (columnIndex) {
            case 0 -> Outlook.setName((String) aValue);
            case 1 -> Outlook.setProject((String) aValue);
            case 2 -> Outlook.setType((String) aValue);
            case 3 -> Outlook.setRank((String) aValue);
            case 4 -> Outlook.setCountry((String) aValue);
            case 5 -> Outlook.setCost((String) aValue);
            default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
        }
        fireTableRowsUpdated(rowIndex, rowIndex);
    }
}
