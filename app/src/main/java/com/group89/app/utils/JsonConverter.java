package com.group89.app.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;

public class JsonConverter<T> {
  private String fileUrl;
  private final Class<T[]> type;

  public JsonConverter(String fileUrl, Class<T[]> type) {
    this.fileUrl = fileUrl;
    this.type = type;
  }

  public ArrayList<T> toArrayList() {
    Gson gson = new Gson();

    try (Reader src = new BufferedReader(new FileReader(fileUrl))) {
      return new ArrayList<T>(Arrays.asList(gson.fromJson(src, type)));
    } catch (IOException e) {
      if (e instanceof FileNotFoundException) {
        // file not found, return empty list
        return new ArrayList<T>();
      } else {
        e.printStackTrace();
        return null;
      }
    }
  }

  public void toFile(List<T> list) {
    Gson gson = new Gson();

    try (Writer dst = new BufferedWriter(new FileWriter(fileUrl))) {
      gson.toJson(list.toArray(), dst);
      dst.write(System.lineSeparator());
      dst.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
