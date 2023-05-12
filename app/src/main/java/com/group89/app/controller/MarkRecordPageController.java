package com.group89.app.controller;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import javax.swing.table.TableRowSorter;
import com.group89.app.model.CourseType;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.MarkRecord;
import com.group89.app.model.MarkRecordTableModel;
import com.group89.app.model.MarkRecordTableModelCN;
import com.group89.app.model.MarkRecordTableModelUK;
import com.group89.app.utils.JsonConverter;
import com.group89.app.view.comp.MarkRecordPage;
import com.group89.app.view.comp.MyComboBox;

// controller
public class MarkRecordPageController {
  // temporary solution for enforcing number range
  // subject to change
  class MarkEditor extends DefaultCellEditor {
    Integer value;

    public MarkEditor() {
      super(new JTextField());

      this.getComponent().setFont(this.getComponent().getFont().deriveFont(16f));
      ((JTextField) getComponent()).setHorizontalAlignment(JTextField.RIGHT);
    }

    @Override
    public boolean stopCellEditing() {
      String s = (String) super.getCellEditorValue();
      try {
        value = Integer.parseInt(s);
        if (value < 0 || value > 100) {
          throw new NumberFormatException();
        }
      } catch (NumberFormatException e) {
        ((JComponent) getComponent()).setBorder(new LineBorder(Color.red));
        return false;
      }
      return super.stopCellEditing();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
        int row, int column) {
      value = null;
      ((JComponent) getComponent()).setBorder(new LineBorder(Color.black));
      return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }

    @Override
    public Object getCellEditorValue() {
      return value;
    }
  }

  // model
  private ListTableModel<MarkRecord> model;
  // view
  private MarkRecordPage view;

  private JsonConverter<MarkRecord> converter;
  private TableRowSorter<ListTableModel<MarkRecord>> sorter;
  // actual data
  private List<MarkRecord> list;

  public MarkRecordPageController(MarkRecordPage page) {
    view = page;

    sorter = new TableRowSorter<>();
    converter = new JsonConverter<>("marks.json", MarkRecord[].class);
    list = converter.toArrayList();

    init();
  }

  public void init() {
    view.getQueryButton().addActionListener(e -> query());
    view.getSaveButton().addActionListener(e -> save());
    view.getDeleteButton().addActionListener(e -> delete());
    view.getAddButton().addActionListener(e -> add());
    view.getTable().setRowSorter(sorter);

    query();
  }

  private void query() {
    String scale = (String) view.getScaleBox().getSelectedItem();
    String semester = (String) view.getSemesterBox().getSelectedItem();
    CourseType type = (CourseType) view.getTypeBox().getSelectedItem();

    JTable table = view.getTable();

    switch (scale) {
      case "BOTH" -> {
        model = new MarkRecordTableModel(list);
        table.setModel(model);
        table.getColumn("Mark (CN)").setCellEditor(new MarkEditor());
        table.getColumn("Mark (UK)").setCellEditor(new MarkEditor());
      }
      case "CN" -> {
        model = new MarkRecordTableModelCN(list);
        table.setModel(model);
        table.getColumn("Mark (CN)").setCellEditor(new MarkEditor());
      }
      case "UK" -> {
        model = new MarkRecordTableModelUK(list);
        table.setModel(model);
        table.getColumn("Mark (UK)").setCellEditor(new MarkEditor());
      }
      default -> throw new IllegalArgumentException("Unexpected value: " + scale);
    }
    model.addTableModelListener(e -> view.getSaveButton().setEnabled(true));
    model.addTableModelListener(e -> updateLabels());
    table.getColumn("Title").setPreferredWidth(200);
    table.getColumn("Semester")
        .setCellEditor(new DefaultCellEditor(new MyComboBox<>(MarkRecordPage.SEMESTERS)));
    table.getColumn("Type").setCellEditor(new DefaultCellEditor(new MyComboBox<>(
        new CourseType[] {CourseType.COMPULSORY, CourseType.ELECTIVE, CourseType.OPTIONAL})));


    sorter.setRowFilter(new RowFilter<ListTableModel<MarkRecord>, Object>() {
      @Override
      public boolean include(Entry<? extends ListTableModel<MarkRecord>, ? extends Object> entry) {
        ListTableModel<MarkRecord> model = entry.getModel();
        MarkRecord record = model.getItem(entry.getIdentifier());
        return (semester.equals("all") || record.getSemester().equals(semester))
            && (type == CourseType.ALL || record.getType() == type);
      }
    });
    sorter.setModel(model);

    updateLabels();
  }

  private void updateLabels() {
    JTable table = view.getTable();

    int size = table.getRowCount();
    double totalCreditsCN = 0;
    double gpa = 0.0;
    double averageCN = 0.0;
    for (int viewRow = 0; viewRow < size; viewRow++) {
      MarkRecord record = model.getItem(table.convertRowIndexToModel(viewRow));

      totalCreditsCN += record.getCreditsCN();
      averageCN += (double) record.getMarkCN() * record.getCreditsCN();
      gpa += record.getGradePoint() * record.getCreditsCN();
    }
    averageCN /= totalCreditsCN;
    gpa /= totalCreditsCN;

    DecimalFormat df = new DecimalFormat("#.##");
    averageCN = Double.parseDouble(df.format(averageCN));
    gpa = Double.parseDouble(df.format(gpa));

    JLabel[] labels = view.getLabels();
    labels[0].setText("Semester: " + view.getSemesterBox().getSelectedItem());
    labels[1].setText("Module Count: " + size);
    labels[2].setText("Total Credits: " + totalCreditsCN);
    labels[3].setText("GPA: " + gpa);
    labels[4].setText("Average Mark: " + averageCN);
  }

  private void add() {
    model.addItem(new MarkRecord());
  }

  private void delete() {
    JTable table = view.getTable();
    int[] rows = table.getSelectedRows();
    for (int i = rows.length - 1; i >= 0; i--) {
      model.removeItem(table.convertRowIndexToModel(rows[i]));
    }
  }

  private void save() {
    converter.toFile(list);
    view.getSaveButton().setEnabled(false);
  }
}
