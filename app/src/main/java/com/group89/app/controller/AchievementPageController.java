package com.group89.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultCellEditor;
import javax.swing.RowFilter;
import com.group89.app.model.AchievementRecordTableModel;
import com.group89.app.model.AchievementType;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.SemesterList;
import com.group89.app.model.entity.AchievementRecord;
// import com.group89.app.utils.SemesterGenerator;
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
        .setCellEditor(new DefaultCellEditor(new IComboBox<>(new SemesterList(false).toArray())));

    ArrayList<AchievementType> types = new ArrayList<>(Arrays.asList(AchievementType.values()));
    types.remove(AchievementType.ALL);
    view.getTable().getColumn("Type").setCellEditor(
        new DefaultCellEditor(new IComboBox<>(types.toArray(new AchievementType[0]))));

    String semester = (String) view.getSemesterBox().getSelectedItem();
    AchievementType type = (AchievementType) view.getTypeBox().getSelectedItem();
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

  @Override
  protected void add() {
    String semester = (String) view.getSemesterBox().getSelectedItem();
    AchievementType type = (AchievementType) view.getTypeBox().getSelectedItem();
    AchievementRecord record = new AchievementRecord(!semester.equals("All") ? semester : "", "",
        "", type != AchievementType.ALL ? type : AchievementType.OTHER);
    model.addItem(record);
  }
}
