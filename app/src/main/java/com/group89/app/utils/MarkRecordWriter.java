package com.group89.app.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import com.google.gson.Gson;
import com.group89.app.model.MarkRecord;

public class MarkRecordWriter {
  private List<MarkRecord> marks;

  public MarkRecordWriter(List<MarkRecord> marks) {
    this.marks = marks;
  }

  public void toJsonFile(String fileUrl) {
    Gson gson = new Gson();

    try (Writer dst = new BufferedWriter(new FileWriter(fileUrl))) {
      gson.toJson(marks.toArray(), dst);
      dst.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
