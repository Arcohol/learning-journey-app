package com.group89.app.model.enumeration;

import java.lang.reflect.Array;

public abstract class AbstractComboBoxItemType<T> {
  public abstract T[] values();

  public abstract T getItemAll();

  public T[] valuesWithoutAll() {
    T[] values = values();
    @SuppressWarnings("unchecked")
    T[] valuesWithoutAll =
        (T[]) Array.newInstance(values.getClass().getComponentType(), values.length - 1);
    System.arraycopy(values, 1, valuesWithoutAll, 0, values.length - 1);
    return valuesWithoutAll;
  }
}
