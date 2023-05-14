package com.group89.app.utils;

import java.util.ArrayList;
import java.util.List;

import com.group89.app.model.*;
import com.group89.app.model.entity.AchievementRecord;
import com.group89.app.model.entity.MarkRecord;
import com.group89.app.model.entity.PortfolioRecord;
import org.junit.jupiter.api.Test;

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

    list.add(new MarkRecord("2020-2021-1", "EBU4203", "Software Engineering", 82, 0, 2, 15,
        CourseType.COMPULSORY));
    list.add(
        new MarkRecord("2020-2021-1", "EBU4224", "Middleware", 76, 0, 2, 15, CourseType.ELECTIVE));
    list.add(new MarkRecord("2020-2021-1", "EBU4204", "Software Engineering Practice", 93, 0, 1.5,
        10, CourseType.COMPULSORY));
    list.add(new MarkRecord("2020-2021-1", "EBU4205", "Software Engineering Project", 64, 0, 3.5,
        20, CourseType.COMPULSORY));
    list.add(new MarkRecord("2020-2021-1", "EBU4206", "IoT Product Development", 68, 0, 4, 20,
        CourseType.ELECTIVE));
    list.add(new MarkRecord("2020-2021-2", "EBU5203", "Operating System", 80, 0, 2.5, 15,
        CourseType.COMPULSORY));
    list.add(new MarkRecord("2020-2021-2", "EBU5214", "Computer Network", 76, 0, 2, 15,
        CourseType.ELECTIVE));
    list.add(new MarkRecord("2020-2021-2", "EBU5204", "Computer Architecture", 93, 0, 1, 10,
        CourseType.COMPULSORY));
    list.add(new MarkRecord("2020-2021-2", "EBU5205", "Database", 64, 0, 0.5, 20,
        CourseType.COMPULSORY));
    list.add(new MarkRecord("2020-2021-2", "EBU5206", "Computer Graphics", 68, 0, 5, 20,
        CourseType.ELECTIVE));

    converter.toFile(list);
  }

  // @Test
  // public void generateSampleAchievementRecord() {
  //   // JsonConverter<AchievementRecord> converter = new JsonConverter<>("achievement.json",
  //   // MarkRecord[].class);
  //   List<AchievementRecord> list = new ArrayList<>();

  //   list.add(new AchievementRecord("National first prize", 2018,
  //       "China \"Internet Plus\" College Student Innovation and Entrepreneurship Competition",
  //       AchievementType.AWARD));
  //   list.add(new AchievementRecord("National third prize", 2019,
  //       "Challenge Cup\" National College students extracurricular academic science and technology works competition",
  //       AchievementType.AWARD));
  //   list.add(new AchievementRecord("Municipal third prize", 2022,
  //       "Challenge Cup\" Business Plan Competition for Chinese College students ACM-ICPC International College Student Programming Competition",
  //       AchievementType.AWARD));
  //   list.add(new AchievementRecord("University-level first prize", 2021,
  //       "National Electronic Design Competition for College students", AchievementType.Award));
  //   list.add(new AchievementRecord("Outstanding student leaders", 2020,
  //       "School Youth League committee assessment", AchievementType.Honour));
  //   list.add(new AchievementRecord("Excellent Communist Youth League member", 2018,
  //       "School Youth League committee assessment", AchievementType.Honour));
  //   list.add(new AchievementRecord("Advanced individual in volunteer service", 2020,
  //       "Assessed by the Youth League Committee of the College", AchievementType.Honour));
  //   list.add(new AchievementRecord("Advanced individual in social practice", 2022,
  //       "Assessed by the Youth League Committee of the College", AchievementType.Honour));

  //   // converter.toFile(list);
  // }

  @Test
  public void generateSamplePortfolioRecord() {
    // JsonConverter<AchievementRecord> converter = new JsonConverter<>("achievement.json",
    // MarkRecord[].class);
    List<PortfolioRecord> list = new ArrayList<>();

    list.add(
        new PortfolioRecord("Canvas: How we crafted Facebook's new immersive ads", "2020-2021-1",
            PortfolioType.POSTERS, "https://pan.baidu.com/open/platform?_at_=1683963428751"));
    list.add(new PortfolioRecord("Reaction: Not everything in life is likable", "2020-2021-1",
        PortfolioType.DESIGN, "https://pan.baidu.com/open/platform?_at_=1683963428752"));
    list.add(
        new PortfolioRecord("I visualized a parking kiosk for people in wheelchair.", "2020-2021-2",
            PortfolioType.VIDEOS, "https://pan.baidu.com/open/platform?_at_=1683963428753"));

    // converter.toFile(list);
  }
}
