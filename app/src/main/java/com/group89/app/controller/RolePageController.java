package com.group89.app.controller;

import com.group89.app.model.RoleRecordTableModel;
import com.group89.app.model.entity.RoleRecord;
import com.group89.app.view.comp.tablepage.RolePage;

public class RolePageController extends AbstractTablePageController<RoleRecord, RolePage>{
  public RolePageController(RolePage page) {
    super(page, "roles.json", RoleRecord[].class, RoleRecord.class);
    init();
  }

  protected void init() {
    super.init();
    query();
  }

  @Override
  protected void query() {
    model = new RoleRecordTableModel(list);
    view.getTable().setModel(model);
  }
}
