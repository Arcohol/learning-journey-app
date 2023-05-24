package com.group89.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultCellEditor;
import javax.swing.RowFilter;
import com.group89.app.model.ApplicationTableModel;
import com.group89.app.model.ApplicationStatus;
import com.group89.app.model.ListTableModel;
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

    ArrayList<ApplicationStatus> types = new ArrayList<>(Arrays.asList(ApplicationStatus.values()));
    types.remove(ApplicationStatus.ALL);
    view.getTable().getColumn("Status").setCellEditor(
        new DefaultCellEditor(new IComboBox<>(types.toArray(new ApplicationStatus[0]))));

    ApplicationStatus status = (ApplicationStatus) view.getStatusBox().getSelectedItem();
    sorter.setRowFilter(new RowFilter<ListTableModel<Application>, Integer>() {
      @Override
      public boolean include(
          Entry<? extends ListTableModel<Application>, ? extends Integer> entry) {
        ListTableModel<Application> model = entry.getModel();
        Application record = model.getItem(entry.getIdentifier());
        return status == ApplicationStatus.ALL || record.getStatus() == status;
      }
    });
    sorter.setModel(model);
  }

  @Override
  protected void add() {
    ApplicationStatus status = (ApplicationStatus) view.getStatusBox().getSelectedItem();
    Application record =
        new Application(status != ApplicationStatus.ALL ? status : ApplicationStatus.PENDING,
            "", "", "", 0.0, 0.0, 0, "");
    model.addItem(record);
  }
}
