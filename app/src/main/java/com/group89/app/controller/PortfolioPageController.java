package com.group89.app.controller;

import javax.swing.DefaultCellEditor;
import javax.swing.RowFilter;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.PortfolioRecordTableModel;
import com.group89.app.model.entity.PortfolioRecord;
import com.group89.app.model.enumeration.ComboBoxItem;
import com.group89.app.model.enumeration.ItemAll;
import com.group89.app.model.enumeration.PortfolioType;
import com.group89.app.model.enumeration.Semester;
import com.group89.app.view.comp.IComboBox;
import com.group89.app.view.comp.tablepage.PortfolioPage;

public class PortfolioPageController
    extends AbstractTablePageController<PortfolioRecord, PortfolioPage> {
  public PortfolioPageController(PortfolioPage page) {
    super(page, "portfolios.json", PortfolioRecord[].class, PortfolioRecord.class);
    init();
  }

  protected void init() {
    super.init();

    view.getSemesterBox().addActionListener(e -> query());
    view.getTypeBox().addActionListener(e -> query());

    query();
  }

  @Override
  protected void query() {
    model = new PortfolioRecordTableModel(list);
    model.addTableModelListener(e -> view.getSaveButton().setEnabled(true));

    view.getTable().setModel(model);
    view.getTable().getColumn("Semester")
        .setCellEditor(new DefaultCellEditor(new IComboBox<>(Semester.values())));
    view.getTable().getColumn("Type")
        .setCellEditor(new DefaultCellEditor(new IComboBox<>(PortfolioType.values())));

    ComboBoxItem semester = (ComboBoxItem) view.getSemesterBox().getSelectedItem();
    ComboBoxItem type = (ComboBoxItem) view.getTypeBox().getSelectedItem();
    sorter.setRowFilter(new RowFilter<ListTableModel<PortfolioRecord>, Integer>() {
      @Override
      public boolean include(
          Entry<? extends ListTableModel<PortfolioRecord>, ? extends Integer> entry) {
        ListTableModel<PortfolioRecord> model = entry.getModel();
        PortfolioRecord record = model.getItem(entry.getIdentifier());
        return (semester == ItemAll.ALL || semester == record.getSemester())
            && (type == ItemAll.ALL || type == record.getType());
      }
    });
    sorter.setModel(model);
  }

  @Override
  protected void add() {
    ComboBoxItem semester = (ComboBoxItem) view.getSemesterBox().getSelectedItem();
    ComboBoxItem type = (ComboBoxItem) view.getTypeBox().getSelectedItem();
    PortfolioRecord record =
        new PortfolioRecord(semester != ItemAll.ALL ? (Semester) semester : Semester.values()[0],
            type != ItemAll.ALL ? (PortfolioType) type : PortfolioType.OTHER, "", "");
    model.addItem(record);
  }
}
