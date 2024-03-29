package com.group89.app.controller;

import javax.swing.DefaultCellEditor;
import javax.swing.RowFilter;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.enumeration.ComboBoxItem;
import com.group89.app.model.enumeration.ItemAll;
import com.group89.app.model.enumeration.Semester;
import com.group89.app.model.RoleTableModel;
import com.group89.app.model.entity.Role;
import com.group89.app.view.comp.IComboBox;
import com.group89.app.view.comp.tablepage.RolePage;

/**
 * A controller class that controls the role page.
 */
public class RolePageController extends AbstractTablePageController<Role, RolePage> {
  /**
   * Constructs a role page controller.
   * 
   * @param page the role page
   */
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
        .setCellEditor(new DefaultCellEditor(new IComboBox<>(Semester.values())));

    ComboBoxItem semester = (ComboBoxItem) view.getSemesterBox().getSelectedItem();
    sorter.setRowFilter(new RowFilter<ListTableModel<Role>, Integer>() {
      @Override
      public boolean include(Entry<? extends ListTableModel<Role>, ? extends Integer> entry) {
        ListTableModel<Role> model = entry.getModel();
        Role record = model.getItem(entry.getIdentifier());
        return semester == ItemAll.ALL || semester == record.getSemester();
      }
    });
    sorter.setModel(model);
  }

  /**
   * Add a new element with the selected items in the combo boxes to the table. If {@code All} is
   * selected, the default value is used instead.
   */
  @Override
  protected void add() {
    ComboBoxItem semester = (ComboBoxItem) view.getSemesterBox().getSelectedItem();
    Role record =
        new Role(semester != ItemAll.ALL ? (Semester) semester : Semester.values()[0], "", "", "");
    model.addItem(record);
  }
}
