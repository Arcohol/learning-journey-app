package com.group89.app.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.group89.app.model.MarkRecord;

public class TestJsonConverter {
  @Test
  public void readMarkRecord() {
    JsonConverter<MarkRecord> converter = new JsonConverter<>("marks.json", MarkRecord[].class);
    List<MarkRecord> list = converter.toList();
    for (MarkRecord record : list) {
      System.out.println(record);
    }
  }

  @Test
  public void writeMarkRecord() {
    JsonConverter<MarkRecord> converter = new JsonConverter<>("marks.json", MarkRecord[].class);
    List<MarkRecord> list = new ArrayList<>();
    list.add(new MarkRecord("EBU4203", "Software Engineering", 80, 15));
    list.add(new MarkRecord("EBU4224", "Middleware", 76, 15));
    converter.toFile(list);

    // delete the file
    File file = new File("marks.json");
    file.delete();
  }
}
