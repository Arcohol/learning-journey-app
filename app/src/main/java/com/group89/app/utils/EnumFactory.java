package com.group89.app.utils;

import com.group89.app.model.enumeration.ComboBoxItem;
import com.group89.app.model.enumeration.ItemAll;

public class EnumFactory {
  public static ComboBoxItem[] addItemAll(ComboBoxItem[] items) {
    ComboBoxItem[] itemsAll = new ComboBoxItem[items.length + 1];
    itemsAll[0] = ItemAll.ALL;
    System.arraycopy(items, 0, itemsAll, 1, items.length);
    return itemsAll;
  }
}
