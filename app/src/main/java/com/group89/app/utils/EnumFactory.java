package com.group89.app.utils;

import com.group89.app.model.enumeration.ComboBoxItem;
import com.group89.app.model.enumeration.ItemAll;

/**
 * A factory class for enums used in combo boxes.
 */
public class EnumFactory {
  /**
   * Returns an array of the given combo box items prepended with item {@code ALL}.
   * 
   * @param items the items to be prepended
   * @return a new array of combo box items prepended with item {@code ALL}
   */
  public static ComboBoxItem[] addItemAll(ComboBoxItem[] items) {
    ComboBoxItem[] itemsAll = new ComboBoxItem[items.length + 1];
    itemsAll[0] = ItemAll.ALL;
    System.arraycopy(items, 0, itemsAll, 1, items.length);
    return itemsAll;
  }
}
