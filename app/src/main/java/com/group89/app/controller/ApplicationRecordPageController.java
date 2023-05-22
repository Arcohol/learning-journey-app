package com.group89.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultCellEditor;
import javax.swing.RowFilter;
import com.group89.app.model.ApplicationRecordTableModel;
import com.group89.app.model.ApplicationStatus;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.entity.ApplicationRecord;
import com.group89.app.view.comp.IComboBox;
import com.group89.app.view.comp.tablepage.ApplicationRecordPage;

public class ApplicationRecordPageController
    extends AbstractTablePageController<ApplicationRecord, ApplicationRecordPage> {
  public ApplicationRecordPageController(ApplicationRecordPage page) {
    super(page, "applications.json", ApplicationRecord[].class, ApplicationRecord.class);
    init();
  }

  protected void init() {
    super.init();

    view.getStatusBox().addActionListener(e -> query());

    query();
  }

  @Override
  protected void query() {
    model = new ApplicationRecordTableModel(list);
    model.addTableModelListener(e -> view.getSaveButton().setEnabled(true));

    view.getTable().setModel(model);

    ArrayList<ApplicationStatus> types = new ArrayList<>(Arrays.asList(ApplicationStatus.values()));
    types.remove(ApplicationStatus.ALL);
    view.getTable().getColumn("Status").setCellEditor(
        new DefaultCellEditor(new IComboBox<>(types.toArray(new ApplicationStatus[0]))));

    ApplicationStatus status = (ApplicationStatus) view.getStatusBox().getSelectedItem();
    sorter.setRowFilter(new RowFilter<ListTableModel<ApplicationRecord>, Integer>() {
      @Override
      public boolean include(
          Entry<? extends ListTableModel<ApplicationRecord>, ? extends Integer> entry) {
        ListTableModel<ApplicationRecord> model = entry.getModel();
        ApplicationRecord record = model.getItem(entry.getIdentifier());
        return status == ApplicationStatus.ALL || record.getStatus() == status;
      }
    });
    sorter.setModel(model);
  }
}
