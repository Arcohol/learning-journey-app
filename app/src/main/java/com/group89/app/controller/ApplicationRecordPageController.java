package com.group89.app.controller;

import javax.swing.DefaultCellEditor;
import javax.swing.RowFilter;
import com.group89.app.model.ApplicationRecordTableModel;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.entity.ApplicationRecord;
import com.group89.app.model.enumeration.AbstractComboBoxItemType;
import com.group89.app.model.enumeration.ApplicationStatus;
import com.group89.app.model.enumeration.ApplicationStatusItemType;
import com.group89.app.view.comp.IComboBox;
import com.group89.app.view.comp.tablepage.ApplicationRecordPage;

public class ApplicationRecordPageController
    extends AbstractTablePageController<ApplicationRecord, ApplicationRecordPage> {
  private AbstractComboBoxItemType<ApplicationStatus> statusType;

  public ApplicationRecordPageController(ApplicationRecordPage page) {
    super(page, "applications.json", ApplicationRecord[].class, ApplicationRecord.class);
    init();
  }

  protected void init() {
    super.init();

    view.getStatusBox().addActionListener(e -> query());

    statusType = new ApplicationStatusItemType();

    query();
  }

  @Override
  protected void query() {
    model = new ApplicationRecordTableModel(list);
    model.addTableModelListener(e -> view.getSaveButton().setEnabled(true));

    view.getTable().setModel(model);
    view.getTable().getColumn("Status")
        .setCellEditor(new DefaultCellEditor(new IComboBox<>(statusType.valuesWithoutAll())));

    ApplicationStatus status = (ApplicationStatus) view.getStatusBox().getSelectedItem();
    sorter.setRowFilter(new RowFilter<ListTableModel<ApplicationRecord>, Integer>() {
      @Override
      public boolean include(
          Entry<? extends ListTableModel<ApplicationRecord>, ? extends Integer> entry) {
        ListTableModel<ApplicationRecord> model = entry.getModel();
        ApplicationRecord record = model.getItem(entry.getIdentifier());
        return status == statusType.getItemAll() || record.getStatus() == status;
      }
    });
    sorter.setModel(model);
  }

  @Override
  protected void add() {
    ApplicationStatus status = (ApplicationStatus) view.getStatusBox().getSelectedItem();
    ApplicationRecord record = new ApplicationRecord(
        status != statusType.getItemAll() ? status : ApplicationStatus.PENDING, "", "", "", 0.0,
        0.0, 0, "");
    model.addItem(record);
  }
}
