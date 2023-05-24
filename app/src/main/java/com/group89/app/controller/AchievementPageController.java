package com.group89.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultCellEditor;
import javax.swing.RowFilter;
import com.group89.app.model.AchievementTableModel;
import com.group89.app.model.AchievementType;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.SemesterList;
import com.group89.app.model.entity.Achievement;
// import com.group89.app.utils.SemesterGenerator;
import com.group89.app.view.comp.IComboBox;
import com.group89.app.view.comp.tablepage.AchievementPage;

public class AchievementPageController
    extends AbstractTablePageController<Achievement, AchievementPage> {
  public AchievementPageController(AchievementPage page) {
    super(page, "achievements.json", Achievement[].class, Achievement.class);
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
    model = new AchievementTableModel(list);
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
    sorter.setRowFilter(new RowFilter<ListTableModel<Achievement>, Integer>() {
      @Override
      public boolean include(
          Entry<? extends ListTableModel<Achievement>, ? extends Integer> entry) {
        ListTableModel<Achievement> model = entry.getModel();
        Achievement record = model.getItem(entry.getIdentifier());
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
    Achievement record = new Achievement(!semester.equals("All") ? semester : "", "",
        "", type != AchievementType.ALL ? type : AchievementType.OTHER);
    model.addItem(record);
  }
}
