package com.group89.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultCellEditor;
import javax.swing.RowFilter;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.PortfolioTableModel;
import com.group89.app.model.PortfolioType;
import com.group89.app.model.SemesterList;
import com.group89.app.model.entity.Portfolio;
import com.group89.app.view.comp.IComboBox;
import com.group89.app.view.comp.tablepage.PortfolioPage;

public class PortfolioPageController
    extends AbstractTablePageController<Portfolio, PortfolioPage> {
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
        .setCellEditor(new DefaultCellEditor(new IComboBox<>(new SemesterList(false).toArray())));

    ArrayList<PortfolioType> types = new ArrayList<>(Arrays.asList(PortfolioType.values()));
    types.remove(PortfolioType.ALL);
    view.getTable().getColumn("Type")
        .setCellEditor(new DefaultCellEditor(new IComboBox<>(types.toArray(new PortfolioType[0]))));

    String semester = (String) view.getSemesterBox().getSelectedItem();
    PortfolioType type = (PortfolioType) view.getTypeBox().getSelectedItem();
    sorter.setRowFilter(new RowFilter<ListTableModel<Portfolio>, Integer>() {
      @Override
      public boolean include(
          Entry<? extends ListTableModel<Portfolio>, ? extends Integer> entry) {
        ListTableModel<Portfolio> model = entry.getModel();
        Portfolio record = model.getItem(entry.getIdentifier());
        return (semester.equals("All") || record.getSemester().equals(semester))
            && (type == PortfolioType.ALL || record.getType() == type);
      }
    });
    sorter.setModel(model);
  }

  @Override
  protected void add() {
    String semester = (String) view.getSemesterBox().getSelectedItem();
    PortfolioType type = (PortfolioType) view.getTypeBox().getSelectedItem();
    Portfolio record = new Portfolio(!semester.equals("All") ? semester : "",
        type != PortfolioType.ALL ? type : PortfolioType.OTHER, "", "");
    model.addItem(record);
  }
}
