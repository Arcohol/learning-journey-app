package com.group89.app.model;

import java.util.List;
import com.group89.app.model.enumeration.PortfolioType;
import com.group89.app.model.enumeration.Semester;
import com.group89.app.model.entity.Portfolio;

/**
 * A table model for portfolios.
 */
public class PortfolioTableModel extends ListTableModel<Portfolio> {
  private static final String[] COLUMN_NAMES = {"Semester", "Type", "Exhibition", "Detail"};

  /**
   * Constructs a portfolio table model.
   *
   * @param portfolioRecords the list of portfolios
   */
  public PortfolioTableModel(List<Portfolio> portfolioRecords) {
    super(Portfolio.class, COLUMN_NAMES, portfolioRecords);
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Portfolio portfolioRecord = getItem(rowIndex);
    return switch (columnIndex) {
      case 0 -> portfolioRecord.getSemester();
      case 1 -> portfolioRecord.getType();
      case 2 -> portfolioRecord.getExhibition();
      case 3 -> portfolioRecord.getDetails();
      default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
    };
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    Portfolio portfolioRecord = getItem(rowIndex);
    switch (columnIndex) {
      case 0 -> portfolioRecord.setSemester((Semester) aValue);
      case 1 -> portfolioRecord.setType((PortfolioType) aValue);
      case 2 -> portfolioRecord.setExhibition((String) aValue);
      case 3 -> portfolioRecord.setDetails((String) aValue);
      default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
    }
    fireTableRowsUpdated(rowIndex, rowIndex);
  }
}
