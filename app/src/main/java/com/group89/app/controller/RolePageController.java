package com.group89.app.controller;

import javax.swing.DefaultCellEditor;
import javax.swing.RowFilter;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.RoleRecordTableModel;
import com.group89.app.model.entity.RoleRecord;
import com.group89.app.model.enumeration.ComboBoxItem;
import com.group89.app.model.enumeration.ItemAll;
import com.group89.app.model.enumeration.Semester;
import com.group89.app.view.comp.IComboBox;
import com.group89.app.view.comp.tablepage.RolePage;

public class RolePageController extends AbstractTablePageController<RoleRecord, RolePage> {
  public RolePageController(RolePage page) {
    super(page, "roles.json", RoleRecord[].class, RoleRecord.class);
    init();
  }

  protected void init() {
    super.init();

    view.getSemesterBox().addActionListener(e -> query());

    query();
  }

  @Override
  protected void query() {
    model = new RoleRecordTableModel(list);
    model.addTableModelListener(e -> view.getSaveButton().setEnabled(true));

    view.getTable().setModel(model);
    view.getTable().getColumn("Semester")
        .setCellEditor(new DefaultCellEditor(new IComboBox<>(Semester.values())));

    ComboBoxItem semester = (ComboBoxItem) view.getSemesterBox().getSelectedItem();
    sorter.setRowFilter(new RowFilter<ListTableModel<RoleRecord>, Integer>() {
      @Override
      public boolean include(Entry<? extends ListTableModel<RoleRecord>, ? extends Integer> entry) {
        ListTableModel<RoleRecord> model = entry.getModel();
        RoleRecord record = model.getItem(entry.getIdentifier());
        return semester == ItemAll.ALL || semester == record.getSemester();
      }
    });
    sorter.setModel(model);
  }

  @Override
  protected void add() {
    ComboBoxItem semester = (ComboBoxItem) view.getSemesterBox().getSelectedItem();
    RoleRecord record = new RoleRecord(
        semester != ItemAll.ALL ? (Semester) semester : Semester.values()[0], "", "", "");
    model.addItem(record);
  }
}
