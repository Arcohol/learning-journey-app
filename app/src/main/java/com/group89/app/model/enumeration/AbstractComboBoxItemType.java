package com.group89.app.model.enumeration;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractComboBoxItemType<T> {
  public abstract T[] values();

  public abstract T getItemAll();

  @SuppressWarnings("unchecked")
  public T[] valuesWithoutAll() {
    T[] values = values();
    List<T> valuesWithoutAll = new LinkedList<>(Arrays.asList(values));
    valuesWithoutAll.remove(getItemAll());
    return valuesWithoutAll
        .toArray((T[]) Array.newInstance(values.getClass().getComponentType(), 0));
  }
}
