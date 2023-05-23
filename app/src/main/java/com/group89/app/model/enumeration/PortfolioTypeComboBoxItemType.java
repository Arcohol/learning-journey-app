package com.group89.app.model.enumeration;

public class PortfolioTypeComboBoxItemType extends AbstractComboBoxItemType<PortfolioType> {
  @Override
  public PortfolioType[] values() {
    return PortfolioType.values();
  }

  @Override
  public PortfolioType getItemAll() {
    return PortfolioType.ALL;
  }
}
