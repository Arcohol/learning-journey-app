package com.group89.app.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
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

  // @Test
  // public void writeMarkRecord() {
  //   JsonConverter<MarkRecord> converter = new JsonConverter<>("tmp.json", MarkRecord[].class);
  //   List<MarkRecord> list = new ArrayList<>();
  //   list.add(new MarkRecord("2022-2023-1", "EBU4203", "Software Engineering", 80, 15));
  //   list.add(new MarkRecord("2021-2022-2", "EBU4224", "Middleware", 76, 15));
  //   converter.toFile(list);

  //   // delete the file
  //   File file = new File("tmp.json");
  //   file.delete();
  // }

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

  @Test
  public void writeMarkConversionData() {
    ArrayList<Integer> list;
    JsonConverter<Integer> converter;
    
    list = new ArrayList<Integer>(Arrays.asList(new Integer[101]));
    list.set(0, 0);
    list.set(1, 1);
    list.set(2, 1);
    list.set(3, 2);
    list.set(4, 3);
    list.set(5, 3);
    list.set(6, 4);
    list.set(7, 5);
    list.set(8, 5);
    list.set(9, 6);
    list.set(10, 7);
    list.set(11, 7);
    list.set(12, 8);
    list.set(13, 9);
    list.set(14, 9);
    list.set(15, 10);
    list.set(16, 11);
    list.set(17, 11);
    list.set(18, 12);
    list.set(19, 13);
    list.set(20, 13);
    list.set(21, 14);
    list.set(22, 15);
    list.set(23, 15);
    list.set(24, 16);
    list.set(25, 17);
    list.set(26, 17);
    list.set(27, 18);
    list.set(28, 19);
    list.set(29, 19);
    list.set(30, 20);
    list.set(31, 21);
    list.set(32, 21);
    list.set(33, 22);
    list.set(34, 23);
    list.set(35, 23);
    list.set(36, 24);
    list.set(37, 25);
    list.set(38, 25);
    list.set(39, 26);
    list.set(40, 27);
    list.set(41, 27);
    list.set(42, 28);
    list.set(43, 29);
    list.set(44, 29);
    list.set(45, 30);
    list.set(46, 31);
    list.set(47, 31);
    list.set(48, 32);
    list.set(49, 33);
    list.set(50, 33);
    list.set(51, 34);
    list.set(52, 35);
    list.set(53, 35);
    list.set(54, 36);
    list.set(55, 37);
    list.set(56, 37);
    list.set(57, 38);
    list.set(58, 39);
    list.set(59, 39);
    list.set(60, 40);
    list.set(61, 41);
    list.set(62, 42);
    list.set(63, 43);
    list.set(64, 44);
    list.set(65, 45);
    list.set(66, 46);
    list.set(67, 47);
    list.set(68, 48);
    list.set(69, 50);
    list.set(70, 51);
    list.set(71, 52);
    list.set(72, 53);
    list.set(73, 54);
    list.set(74, 55);
    list.set(75, 57);
    list.set(76, 58);
    list.set(77, 59);
    list.set(78, 60);
    list.set(79, 62);
    list.set(80, 63);
    list.set(81, 64);
    list.set(82, 66);
    list.set(83, 67);
    list.set(84, 69);
    list.set(85, 70);
    list.set(86, 72);
    list.set(87, 73);
    list.set(88, 75);
    list.set(89, 76);
    list.set(90, 78);
    list.set(91, 80);
    list.set(92, 82);
    list.set(93, 84);
    list.set(94, 86);
    list.set(95, 88);
    list.set(96, 90);
    list.set(97, 92);
    list.set(98, 95);
    list.set(99, 97);
    list.set(100, 100);
    converter = new JsonConverter<>("BUPT-to-QMUL.json", Integer[].class);
    converter.toFile(list);

    list = new ArrayList<Integer>(Arrays.asList(new Integer[101]));
    list.set(0, 0);
    list.set(1, 2);
    list.set(2, 3);
    list.set(3, 5);
    list.set(4, 6);
    list.set(5, 8);
    list.set(6, 9);
    list.set(7, 11);
    list.set(8, 12);
    list.set(9, 14);
    list.set(10, 15);
    list.set(11, 17);
    list.set(12, 18);
    list.set(13, 20);
    list.set(14, 21);
    list.set(15, 23);
    list.set(16, 24);
    list.set(17, 26);
    list.set(18, 27);
    list.set(19, 29);
    list.set(20, 30);
    list.set(21, 32);
    list.set(22, 33);
    list.set(23, 35);
    list.set(24, 36);
    list.set(25, 38);
    list.set(26, 39);
    list.set(27, 41);
    list.set(28, 42);
    list.set(29, 44);
    list.set(30, 45);
    list.set(31, 47);
    list.set(32, 48);
    list.set(33, 50);
    list.set(34, 51);
    list.set(35, 53);
    list.set(36, 54);
    list.set(37, 56);
    list.set(38, 57);
    list.set(39, 59);
    list.set(40, 60);
    list.set(41, 61);
    list.set(42, 62);
    list.set(43, 63);
    list.set(44, 64);
    list.set(45, 65);
    list.set(46, 66);
    list.set(47, 67);
    list.set(48, 68);
    list.set(49, 69);
    list.set(50, 69);
    list.set(51, 70);
    list.set(52, 71);
    list.set(53, 72);
    list.set(54, 73);
    list.set(55, 74);
    list.set(56, 75);
    list.set(57, 75);
    list.set(58, 76);
    list.set(59, 77);
    list.set(60, 78);
    list.set(61, 79);
    list.set(62, 79);
    list.set(63, 80);
    list.set(64, 81);
    list.set(65, 82);
    list.set(66, 82);
    list.set(67, 83);
    list.set(68, 84);
    list.set(69, 84);
    list.set(70, 85);
    list.set(71, 86);
    list.set(72, 86);
    list.set(73, 87);
    list.set(74, 88);
    list.set(75, 88);
    list.set(76, 89);
    list.set(77, 89);
    list.set(78, 90);
    list.set(79, 91);
    list.set(80, 91);
    list.set(81, 92);
    list.set(82, 92);
    list.set(83, 93);
    list.set(84, 93);
    list.set(85, 94);
    list.set(86, 94);
    list.set(87, 95);
    list.set(88, 95);
    list.set(89, 96);
    list.set(90, 96);
    list.set(91, 97);
    list.set(92, 97);
    list.set(93, 97);
    list.set(94, 98);
    list.set(95, 98);
    list.set(96, 99);
    list.set(97, 99);
    list.set(98, 99);
    list.set(99, 100);
    list.set(100, 100);
    converter = new JsonConverter<>("QMUL-to-BUPT.json", Integer[].class);
    converter.toFile(list);
  }
}
