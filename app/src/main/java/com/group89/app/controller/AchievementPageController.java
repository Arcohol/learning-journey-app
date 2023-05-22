package com.group89.app.controller;

import javax.swing.DefaultCellEditor;
import javax.swing.RowFilter;
import com.group89.app.model.AchievementRecordTableModel;
import com.group89.app.model.AchievementType;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.entity.AchievementRecord;
import com.group89.app.utils.SemesterGenerator;
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
    String semester = (String) view.getSemesterBox().getSelectedItem();
    AchievementType type = (AchievementType) view.getTypeBox().getSelectedItem();

    model = new AchievementRecordTableModel(list);
    model.addTableModelListener(e -> view.getSaveButton().setEnabled(true));

    view.getTable().setModel(model);
    view.getTable().getColumn("Semester").setCellEditor(new DefaultCellEditor(
        new IComboBox<>(SemesterGenerator.generate().toArray(new String[0]))));
    view.getTable().getColumn("Type")
        .setCellEditor(new DefaultCellEditor(new IComboBox<>(AchievementType.values())));

    sorter.setRowFilter(new RowFilter<ListTableModel<AchievementRecord>, Integer>() {
      @Override
      public boolean include(
          Entry<? extends ListTableModel<AchievementRecord>, ? extends Integer> entry) {
        ListTableModel<AchievementRecord> model = entry.getModel();
        AchievementRecord record = model.getItem(entry.getIdentifier());
        return (semester.equals("All") || record.getSemester().equals(semester))
            && (type == AchievementType.ALL || record.getType() == type);
      }
    });
    sorter.setModel(model);
  }
}
