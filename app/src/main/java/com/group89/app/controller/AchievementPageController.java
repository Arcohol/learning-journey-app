package com.group89.app.controller;

import javax.swing.DefaultCellEditor;
import com.group89.app.model.AchievementRecordTableModel;
import com.group89.app.model.AchievementType;
import com.group89.app.model.entity.AchievementRecord;
import com.group89.app.view.comp.MyComboBox;
import com.group89.app.view.comp.tablepage.AchievementPage;

public class AchievementPageController
    extends AbstractTablePageController<AchievementRecord, AchievementPage> {
  public AchievementPageController(AchievementPage page) {
    super(page, "achievements.json", AchievementRecord[].class, AchievementRecord.class);
    init();
  }

  protected void init() {
    super.init();
    query();
  }

  @Override
  protected void query() {
    model = new AchievementRecordTableModel(list);
    view.getTable().setModel(model);
    view.getTable().getColumn("Type")
        .setCellEditor(new DefaultCellEditor(new MyComboBox<>(AchievementType.values())));
  }
}
