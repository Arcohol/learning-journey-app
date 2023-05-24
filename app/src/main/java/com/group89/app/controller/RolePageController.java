package com.group89.app.controller;

import javax.swing.DefaultCellEditor;
import javax.swing.RowFilter;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.RoleTableModel;
import com.group89.app.model.SemesterList;
import com.group89.app.model.entity.Role;
// import com.group89.app.utils.SemesterGenerator;
import com.group89.app.view.comp.IComboBox;
import com.group89.app.view.comp.tablepage.RolePage;

public class RolePageController extends AbstractTablePageController<Role, RolePage> {
  public RolePageController(RolePage page) {
    super(page, "roles.json", Role[].class, Role.class);
    init();
  }

  protected void init() {
    super.init();

    view.getSemesterBox().addActionListener(e -> query());

    query();
  }

  @Override
  protected void query() {
    model = new RoleTableModel(list);
    model.addTableModelListener(e -> view.getSaveButton().setEnabled(true));

    view.getTable().setModel(model);
    view.getTable().getColumn("Semester")
        .setCellEditor(new DefaultCellEditor(new IComboBox<>(new SemesterList(false).toArray())));

    String semester = (String) view.getSemesterBox().getSelectedItem();
    sorter.setRowFilter(new RowFilter<ListTableModel<Role>, Integer>() {
      @Override
      public boolean include(Entry<? extends ListTableModel<Role>, ? extends Integer> entry) {
        ListTableModel<Role> model = entry.getModel();
        Role record = model.getItem(entry.getIdentifier());
        return semester.equals("All") || record.getSemester().equals(semester);
      }
    });
    sorter.setModel(model);
  }

  @Override
  protected void add() {
    String semester = (String) view.getSemesterBox().getSelectedItem();
    Role record = new Role(!semester.equals("All") ? semester : "", "", "", "");
    model.addItem(record);
  }
}
