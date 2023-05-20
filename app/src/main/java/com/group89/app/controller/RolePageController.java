package com.group89.app.controller;

import javax.swing.DefaultCellEditor;
import javax.swing.RowFilter;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.RoleRecordTableModel;
import com.group89.app.model.entity.RoleRecord;
import com.group89.app.utils.SemesterGenerator;
import com.group89.app.view.comp.MyComboBox;
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
    String semester = (String) view.getSemesterBox().getSelectedItem();

    model = new RoleRecordTableModel(list);
    model.addTableModelListener(e -> view.getSaveButton().setEnabled(true));

    view.getTable().setModel(model);
    view.getTable().getColumn("Semester").setCellEditor(
        new DefaultCellEditor(new MyComboBox<>(SemesterGenerator.generate().toArray(new String[0]))));

    sorter.setRowFilter(new RowFilter<ListTableModel<RoleRecord>, Integer>() {
      @Override
      public boolean include(Entry<? extends ListTableModel<RoleRecord>, ? extends Integer> entry) {
        ListTableModel<RoleRecord> model = entry.getModel();
        RoleRecord record = model.getItem(entry.getIdentifier());
        return semester.equals("All") || record.getSemester().equals(semester);
      }
    });
    sorter.setModel(model);
  }
}
