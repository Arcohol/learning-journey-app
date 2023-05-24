package com.group89.app.controller;

import javax.swing.DefaultCellEditor;
import javax.swing.RowFilter;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.enumeration.AchievementType;
import com.group89.app.model.enumeration.ComboBoxItem;
import com.group89.app.model.enumeration.ItemAll;
import com.group89.app.model.enumeration.Semester;
import com.group89.app.model.AchievementTableModel;
import com.group89.app.model.entity.Achievement;
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
        .setCellEditor(new DefaultCellEditor(new IComboBox<>(Semester.values())));
    view.getTable().getColumn("Type")
        .setCellEditor(new DefaultCellEditor(new IComboBox<>(AchievementType.values())));

    ComboBoxItem semester = (ComboBoxItem) view.getSemesterBox().getSelectedItem();
    ComboBoxItem type = (ComboBoxItem) view.getTypeBox().getSelectedItem();
    sorter.setRowFilter(new RowFilter<ListTableModel<Achievement>, Integer>() {
      @Override
      public boolean include(
          Entry<? extends ListTableModel<Achievement>, ? extends Integer> entry) {
        ListTableModel<Achievement> model = entry.getModel();
        Achievement record = model.getItem(entry.getIdentifier());
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
    Achievement record =
        new Achievement(semester != ItemAll.ALL ? (Semester) semester : Semester.values()[0], "", "",
            type != ItemAll.ALL ? (AchievementType) type : AchievementType.OTHER);
    model.addItem(record);
  }
}
