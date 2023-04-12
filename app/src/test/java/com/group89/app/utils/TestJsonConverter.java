package com.group89.app.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.group89.app.model.MarkRecord;

public class TestJsonConverter {
  // These tests are actually useless. Don't know how to test them.

  @Test
  public void readMarkRecord() {
    JsonConverter<MarkRecord> converter = new JsonConverter<>("marks.json", MarkRecord[].class);
    List<MarkRecord> list = converter.toList();
    if (list == null) {
      System.out.println("No data");
      return;
    }
    for (MarkRecord record : list) {
      System.out.println(record);
    }
  }

  @Test
  public void writeMarkRecord() {
    JsonConverter<MarkRecord> converter = new JsonConverter<>("marks.json", MarkRecord[].class);
    List<MarkRecord> list = new ArrayList<>();
    list.add(new MarkRecord("2022-2023-1", "EBU4203", "Software Engineering", 80, 15));
    list.add(new MarkRecord("2021-2022-2", "EBU4224", "Middleware", 76, 15));
    converter.toFile(list);

    // delete the file
    File file = new File("marks.json");
    file.delete();
  }
}
