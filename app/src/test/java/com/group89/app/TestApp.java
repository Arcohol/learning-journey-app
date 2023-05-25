package com.group89.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import com.group89.app.model.PortfolioTableModel;
import com.group89.app.model.entity.Mark;
import com.group89.app.model.entity.Portfolio;
import com.group89.app.model.enumeration.PortfolioType;
import com.group89.app.model.enumeration.Semester;
import com.group89.app.view.comp.tablepage.MarkPage;

class TestApp {
  @Test
  public void testEntity() {
    Mark mark = new Mark();
    mark.setSemester(Semester.values()[0]);
    mark.setModuleCode("BBC1001");
    mark.setMarkCN(50);
    assertEquals(mark.getMarkCN(), 50);
  }

  @Test
  void testEntity2() {
    Mark mark = new Mark();
    mark.setModuleCode("EBU1001");
    mark.setMarkUK(50);
    assertEquals(mark.getMarkCN(), 69);
  }

  @Test
  void testPortfolio() {
    PortfolioTableModel model = new PortfolioTableModel(new ArrayList<>());
    model.addItem(new Portfolio());
    assertEquals(model.getItem(0).getType(), PortfolioType.OTHER);
  }
  
  @Test
  void testMark() {
    MarkPage markPage = new MarkPage();
    markPage.getAddButton().doClick();
    assertEquals(markPage.getLabels()[1].getText(), "Module Count: 42");
  }
}
