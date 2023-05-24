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

/**
 * A class that converts a JSON file to an ArrayList and vice versa.
 * 
 * @param <T> The type of the items
 */
public class JsonConverter<T> {
  private String fileUrl;
  private final Class<T[]> type;

  /**
   * Constructor of the class.
   * 
   * @param fileUrl The URL of the JSON file
   * @param type The type of the items
   */
  public JsonConverter(String fileUrl, Class<T[]> type) {
    this.fileUrl = fileUrl;
    this.type = type;
  }

  /**
   * Read the JSON file and convert it to an ArrayList.
   * 
   * @return The ArrayList
   */
  public ArrayList<T> toArrayList() {
    Gson gson = new Gson();

    try (Reader src = new BufferedReader(new FileReader(fileUrl))) {
      return new ArrayList<T>(Arrays.asList(gson.fromJson(src, type)));
    } catch (IOException e) {
      if (e instanceof FileNotFoundException) {
        return new ArrayList<T>();
      } else {
        e.printStackTrace();
        return null;
      }
    }
  }

  /**
   * Write a list of items to the JSON file.
   * 
   * @param list The ArrayList
   */
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
