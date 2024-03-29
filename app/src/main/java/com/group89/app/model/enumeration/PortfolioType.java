package com.group89.app.model.enumeration;

/**
 * An enum class that represents type of portfolio.
 */
public enum PortfolioType implements ComboBoxItem {
  VIDEOS, POSTERS, PHOTOS, DESIGN, FREEHAND, OTHER;

  @Override
  public String toString() {
    return name().charAt(0) + name().substring(1).toLowerCase();
  }
}
