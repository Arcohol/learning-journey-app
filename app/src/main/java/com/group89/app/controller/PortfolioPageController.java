package com.group89.app.controller;

import javax.swing.DefaultCellEditor;
import javax.swing.RowFilter;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.enumeration.ComboBoxItem;
import com.group89.app.model.enumeration.ItemAll;
import com.group89.app.model.enumeration.PortfolioType;
import com.group89.app.model.enumeration.Semester;
import com.group89.app.model.PortfolioTableModel;
import com.group89.app.model.entity.Portfolio;
import com.group89.app.view.comp.IComboBox;
import com.group89.app.view.comp.tablepage.PortfolioPage;

public class PortfolioPageController extends AbstractTablePageController<Portfolio, PortfolioPage> {
  public PortfolioPageController(PortfolioPage page) {
    super(page, "portfolios.json", Portfolio[].class, Portfolio.class);
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
    model = new PortfolioTableModel(list);
    model.addTableModelListener(e -> view.getSaveButton().setEnabled(true));

    view.getTable().setModel(model);
    view.getTable().getColumn("Semester")
        .setCellEditor(new DefaultCellEditor(new IComboBox<>(Semester.values())));
    view.getTable().getColumn("Type")
        .setCellEditor(new DefaultCellEditor(new IComboBox<>(PortfolioType.values())));

    ComboBoxItem semester = (ComboBoxItem) view.getSemesterBox().getSelectedItem();
    ComboBoxItem type = (ComboBoxItem) view.getTypeBox().getSelectedItem();
    sorter.setRowFilter(new RowFilter<ListTableModel<Portfolio>, Integer>() {
      @Override
      public boolean include(Entry<? extends ListTableModel<Portfolio>, ? extends Integer> entry) {
        ListTableModel<Portfolio> model = entry.getModel();
        Portfolio record = model.getItem(entry.getIdentifier());
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
    Portfolio record =
        new Portfolio(semester != ItemAll.ALL ? (Semester) semester : Semester.values()[0],
            type != ItemAll.ALL ? (PortfolioType) type : PortfolioType.OTHER, "", "");
    model.addItem(record);
  }
}
