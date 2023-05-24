package com.group89.app.controller;

import javax.swing.DefaultCellEditor;
import javax.swing.RowFilter;
import com.group89.app.model.AchievementRecordTableModel;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.entity.AchievementRecord;
import com.group89.app.model.enumeration.AchievementType;
import com.group89.app.model.enumeration.ComboBoxItem;
import com.group89.app.model.enumeration.ItemAll;
import com.group89.app.model.enumeration.Semester;
import com.group89.app.view.comp.IComboBox;
import com.group89.app.view.comp.tablepage.AchievementPage;

public class AchievementPageController
    extends AbstractTablePageController<AchievementRecord, AchievementPage> {
  public AchievementPageController(AchievementPage page) {
    super(page, "achievements.json", AchievementRecord[].class, AchievementRecord.class);
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
    model = new AchievementRecordTableModel(list);
    model.addTableModelListener(e -> view.getSaveButton().setEnabled(true));

    view.getTable().setModel(model);
    view.getTable().getColumn("Semester")
        .setCellEditor(new DefaultCellEditor(new IComboBox<>(Semester.values())));
    view.getTable().getColumn("Type")
        .setCellEditor(new DefaultCellEditor(new IComboBox<>(AchievementType.values())));

    ComboBoxItem semester = (ComboBoxItem) view.getSemesterBox().getSelectedItem();
    ComboBoxItem type = (ComboBoxItem) view.getTypeBox().getSelectedItem();
    sorter.setRowFilter(new RowFilter<ListTableModel<AchievementRecord>, Integer>() {
      @Override
      public boolean include(
          Entry<? extends ListTableModel<AchievementRecord>, ? extends Integer> entry) {
        ListTableModel<AchievementRecord> model = entry.getModel();
        AchievementRecord record = model.getItem(entry.getIdentifier());
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
    AchievementRecord record =
        new AchievementRecord(semester != ItemAll.ALL ? (Semester) semester : Semester.values()[0], "", "",
            type != ItemAll.ALL ? (AchievementType) type : AchievementType.OTHER);
    model.addItem(record);
  }
}
