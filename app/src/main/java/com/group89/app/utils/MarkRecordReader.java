package com.group89.app.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import com.group89.app.model.MarkRecord;

public class MarkRecordReader {
  private String fileUrl;

  public MarkRecordReader(String fileUrl) {
    this.fileUrl = fileUrl;
  }

  public List<MarkRecord> toList() {
    Gson gson = new Gson();
    
    try (Reader src = new BufferedReader(new FileReader(fileUrl))) {
      return new ArrayList<MarkRecord>(Arrays.asList(gson.fromJson(src, MarkRecord[].class)));
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
