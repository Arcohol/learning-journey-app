package com.group89.app.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MarkRecordTest {
  @Test
  public void testMarkRecord() {
    MarkRecord markRecord = new MarkRecord("CS101", "Programming", 80, 20);
    
    assertEquals("CS101", markRecord.getModuleCode());
    assertEquals("Programming", markRecord.getTitle());
    assertEquals(80, markRecord.getMark());
    assertEquals(20, markRecord.getCredits());
  }
}
