package com.group89.app.controller;

import javax.swing.DefaultCellEditor;
import javax.swing.RowFilter;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.RoleRecordTableModel;
import com.group89.app.model.entity.RoleRecord;
import com.group89.app.model.enumeration.AbstractComboBoxItemType;
import com.group89.app.model.enumeration.SemesterComboBoxItemType;
import com.group89.app.view.comp.IComboBox;
import com.group89.app.view.comp.tablepage.RolePage;

public class RolePageController extends AbstractTablePageController<RoleRecord, RolePage> {
  private AbstractComboBoxItemType<String> semesterType;

  public RolePageController(RolePage page) {
    super(page, "roles.json", RoleRecord[].class, RoleRecord.class);
    init();
  }

  protected void init() {
    super.init();

    view.getSemesterBox().addActionListener(e -> query());

    semesterType = new SemesterComboBoxItemType();

    query();
  }

  @Override
  protected void query() {
    model = new RoleRecordTableModel(list);
    model.addTableModelListener(e -> view.getSaveButton().setEnabled(true));

    view.getTable().setModel(model);
    view.getTable().getColumn("Semester")
        .setCellEditor(new DefaultCellEditor(new IComboBox<>(semesterType.valuesWithoutAll())));

    String semester = (String) view.getSemesterBox().getSelectedItem();
    sorter.setRowFilter(new RowFilter<ListTableModel<RoleRecord>, Integer>() {
      @Override
      public boolean include(Entry<? extends ListTableModel<RoleRecord>, ? extends Integer> entry) {
        ListTableModel<RoleRecord> model = entry.getModel();
        RoleRecord record = model.getItem(entry.getIdentifier());
        return semester.equals(semesterType.getItemAll()) || record.getSemester().equals(semester);
      }
    });
    sorter.setModel(model);
  }

  @Override
  protected void add() {
    String semester = (String) view.getSemesterBox().getSelectedItem();
    RoleRecord record =
        new RoleRecord(!semester.equals(semesterType.getItemAll()) ? semester : "", "", "", "");
    model.addItem(record);
  }
}
