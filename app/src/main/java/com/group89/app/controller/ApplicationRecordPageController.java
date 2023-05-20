package com.group89.app.controller;

import javax.swing.DefaultCellEditor;
import com.group89.app.model.ApplicationRecordTableModel;
import com.group89.app.model.ApplicationStatus;
import com.group89.app.model.entity.ApplicationRecord;
import com.group89.app.view.comp.IComboBox;
import com.group89.app.view.comp.tablepage.ApplicationRecordPage;

public class ApplicationRecordPageController
    extends AbstractTablePageController<ApplicationRecord, ApplicationRecordPage> {
  public ApplicationRecordPageController(ApplicationRecordPage page) {
    super(page, "applications.json", ApplicationRecord[].class, ApplicationRecord.class);
    init();
  }

  protected void init() {
    super.init();
    query();
  }

  @Override
  protected void query() {
    model = new ApplicationRecordTableModel(list);
    view.getTable().setModel(model);
    view.getTable().getColumn("Status")
        .setCellEditor(new DefaultCellEditor(new IComboBox<>(ApplicationStatus.values())));
  }
}
