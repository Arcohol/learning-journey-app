package com.group89.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.group89.app.model.entity.Mark;
import com.group89.app.model.enumeration.Semester;

class test {
  @Test
  void testEntity() {
    Mark mark = new Mark();
    mark.setSemester(Semester.values()[0]);
    mark.setModuleCode("BBC1001");
    mark.setMarkCN(50);
    assertEquals(mark.getMarkCN(), 50);
  }
}
