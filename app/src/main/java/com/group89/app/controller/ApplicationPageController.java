package com.group89.app.controller;

import javax.swing.DefaultCellEditor;
import javax.swing.RowFilter;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.enumeration.ApplicationStatus;
import com.group89.app.model.enumeration.ComboBoxItem;
import com.group89.app.model.enumeration.ItemAll;
import com.group89.app.model.ApplicationTableModel;
import com.group89.app.model.entity.Application;
import com.group89.app.view.comp.IComboBox;
import com.group89.app.view.comp.tablepage.ApplicationPage;

public class ApplicationPageController
    extends AbstractTablePageController<Application, ApplicationPage> {
  public ApplicationPageController(ApplicationPage page) {
    super(page, "applications.json", Application[].class, Application.class);
    init();
  }

  protected void init() {
    super.init();

    view.getStatusBox().addActionListener(e -> query());

    query();
  }

  @Override
  protected void query() {
    model = new ApplicationTableModel(list);
    model.addTableModelListener(e -> view.getSaveButton().setEnabled(true));

    view.getTable().setModel(model);
    view.getTable().getColumn("Status")
        .setCellEditor(new DefaultCellEditor(new IComboBox<>(ApplicationStatus.values())));

    ComboBoxItem status = (ComboBoxItem) view.getStatusBox().getSelectedItem();
    sorter.setRowFilter(new RowFilter<ListTableModel<Application>, Integer>() {
      @Override
      public boolean include(
          Entry<? extends ListTableModel<Application>, ? extends Integer> entry) {
        ListTableModel<Application> model = entry.getModel();
        Application record = model.getItem(entry.getIdentifier());
        return status == ItemAll.ALL || status == record.getStatus();
      }
    });
    sorter.setModel(model);
  }

  @Override
  protected void add() {
    ComboBoxItem status = (ComboBoxItem) view.getStatusBox().getSelectedItem();
    Application record = new Application(
        status != ItemAll.ALL ? (ApplicationStatus) status : ApplicationStatus.PENDING, "", "", "",
        0.0, 0.0, 0, "");
    model.addItem(record);
  }
}
