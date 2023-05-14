package com.group89.app.controller;

import javax.swing.DefaultCellEditor;
import com.group89.app.model.PortfolioRecordTableModel;
import com.group89.app.model.PortfolioType;
import com.group89.app.model.entity.PortfolioRecord;
import com.group89.app.view.comp.MyComboBox;
import com.group89.app.view.comp.tablepage.PortfolioPage;

public class PortfolioPageController
  extends AbstractTablePageController<PortfolioRecord, PortfolioPage> {
    public PortfolioPageController(PortfolioPage page) {
      super(page, "portfolios.json", PortfolioRecord[].class, PortfolioRecord.class);
      init();
    }
  
    protected void init() {
      super.init();
      query();
    }
  
    @Override
    protected void query() {
      model = new PortfolioRecordTableModel(list);
      view.getTable().setModel(model);
      view.getTable().getColumn("Type").setCellEditor(new DefaultCellEditor(new MyComboBox<>(PortfolioType.values())));
    }
  }
  