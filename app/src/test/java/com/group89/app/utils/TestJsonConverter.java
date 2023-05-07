package com.group89.app.utils;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.group89.app.model.MarkRecord;

public class TestJsonConverter {
  // These tests are actually useless. Don't know how to test them.
  @Test
  public void readMarkRecord() {
    JsonConverter<MarkRecord> converter = new JsonConverter<>("marks.json", MarkRecord[].class);
    List<MarkRecord> list = converter.toArrayList();
    if (list == null) {
      System.out.println("No data");
      return;
    }
    for (MarkRecord record : list) {
      System.out.println(record);
    }
  }

  @Test
  public void generateSampleMarkRecord() {
    JsonConverter<MarkRecord> converter = new JsonConverter<>("marks.json", MarkRecord[].class);
    List<MarkRecord> list = new ArrayList<>();

    list.add(new MarkRecord("2020-2021-1", "EBU4203", "Software Engineering", 82, 0, 2, 15));
    list.add(new MarkRecord("2020-2021-1", "EBU4224", "Middleware", 76, 0, 2, 15));
    list.add(new MarkRecord("2020-2021-1", "EBU4204", "Software Engineering Practice", 93, 0, 1.5, 10));
    list.add(new MarkRecord("2020-2021-1", "EBU4205", "Software Engineering Project", 64, 0, 3.5, 20));
    list.add(new MarkRecord("2020-2021-1", "EBU4206", "IoT Product Development", 68, 0, 4, 20));

    list.add(new MarkRecord("2020-2021-2", "EBU5203", "Operating System", 80, 0, 2.5, 15));
    list.add(new MarkRecord("2020-2021-2", "EBU5214", "Computer Network", 76, 0, 2, 15));
    list.add(new MarkRecord("2020-2021-2", "EBU5204", "Computer Architecture", 93, 0, 1, 10));
    list.add(new MarkRecord("2020-2021-2", "EBU5205", "Database", 64, 0, 0.5, 20));
    list.add(new MarkRecord("2020-2021-2", "EBU5206", "Computer Graphics", 68, 0, 5, 20));

    converter.toFile(list);
  }
}
