package com.group89.app.utils;

import java.util.ArrayList;

public class MarkConverter {
  private ArrayList<Integer> BUPTToQMUL;
  private ArrayList<Integer> QMULToBUPT;

  public MarkConverter() {
    JsonConverter<Integer> converter;
    converter = new JsonConverter<>("BUPT-to-QMUL.json", Integer[].class);
    BUPTToQMUL = (ArrayList<Integer>) converter.toList();
    converter = new JsonConverter<>("QMUL-to-BUPT.json", Integer[].class);
    QMULToBUPT = (ArrayList<Integer>) converter.toList();
  }

  public int toQMUL(int BUPTMark) {
    if (BUPTMark >= 0 && BUPTMark <= 100) {
      return BUPTToQMUL.get(BUPTMark);
    } else {
      return -1;
    }
  }

  public int toBUPT(int QMULMark) {
    if (QMULMark >= 0 && QMULMark <= 100) {
      return QMULToBUPT.get(QMULMark);
    } else {
      return -1;
    }
  }
}
