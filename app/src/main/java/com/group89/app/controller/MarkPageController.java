package com.group89.app.controller;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import com.group89.app.model.CourseType;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.MarkTableModel;
import com.group89.app.model.MarkTableModelCN;
import com.group89.app.model.MarkTableModelUK;
import com.group89.app.model.SemesterList;
import com.group89.app.model.entity.Mark;
// import com.group89.app.utils.SemesterGenerator;
import com.group89.app.view.comp.IComboBox;
import com.group89.app.view.comp.tablepage.MarkPage;

public class MarkPageController
    extends AbstractTablePageController<Mark, MarkPage> {
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
      JTextField comp =
          (JTextField) super.getTableCellEditorComponent(table, value, isSelected, row, column);
      comp.setBorder(new LineBorder(Color.black));
      comp.selectAll();
      return comp;
    }

    @Override
    public Object getCellEditorValue() {
      return value;
    }
  }

  public MarkPageController(MarkPage page) {
    super(page, "marks.json", Mark[].class, Mark.class);

    init();
  }

  protected void init() {
    super.init();

    view.getScaleBox().addActionListener(e -> query());
    view.getSemesterBox().addActionListener(e -> query());
    view.getTypeBox().addActionListener(e -> query());

    query();
  }

  @Override
  protected void query() {
    String scale = (String) view.getScaleBox().getSelectedItem();

    JTable table = view.getTable();

    switch (scale) {
      case "BOTH" -> {
        model = new MarkTableModel(list);
        model.addTableModelListener(e -> updateLabels());
        table.setModel(model);
        table.getColumn("Mark (CN)").setCellEditor(new MarkEditor());
        table.getColumn("Mark (UK)").setCellEditor(new MarkEditor());
      }
      case "CN" -> {
        model = new MarkTableModelCN(list);
        model.addTableModelListener(e -> updateLabels());
        table.setModel(model);
        table.getColumn("Mark (CN)").setCellEditor(new MarkEditor());
      }
      case "UK" -> {
        model = new MarkTableModelUK(list);
        model.addTableModelListener(e -> updateLabels());
        table.setModel(model);
        table.getColumn("Mark (UK)").setCellEditor(new MarkEditor());
      }
      default -> throw new IllegalArgumentException("Unexpected value: " + scale);
    }
    model.addTableModelListener(e -> view.getSaveButton().setEnabled(true));
    table.getColumn("Title").setPreferredWidth(200);
    table.getColumn("Semester")
        .setCellEditor(new DefaultCellEditor(new IComboBox<>(new SemesterList(false).toArray())));

    ArrayList<CourseType> types = new ArrayList<>(Arrays.asList(CourseType.values()));
    types.remove(CourseType.ALL);
    table.getColumn("Type")
        .setCellEditor(new DefaultCellEditor(new IComboBox<>(types.toArray(new CourseType[0]))));

    String semester = (String) view.getSemesterBox().getSelectedItem();
    CourseType type = (CourseType) view.getTypeBox().getSelectedItem();
    sorter.setRowFilter(new RowFilter<ListTableModel<Mark>, Integer>() {
      @Override
      public boolean include(Entry<? extends ListTableModel<Mark>, ? extends Integer> entry) {
        ListTableModel<Mark> model = entry.getModel();
        Mark record = model.getItem(entry.getIdentifier());
        return (semester.equals("All") || record.getSemester().equals(semester))
            && (type == CourseType.ALL || record.getType() == type);
      }
    });
    sorter.setModel(model);

    updateLabels();
  }

  @Override
  protected void add() {
    String semester = (String) view.getSemesterBox().getSelectedItem();
    CourseType type = (CourseType) view.getTypeBox().getSelectedItem();
    Mark record = new Mark(!semester.equals("All") ? semester : "", "", "", 0, 0, 0.0,
        0, type != CourseType.ALL ? type : CourseType.COMPULSORY);
    model.addItem(record);
  }

  private void updateLabels() {
    JTable table = view.getTable();

    int size = table.getRowCount();
    double totalCreditsCN = 0;
    double gpa = 0.0;
    double averageCN = 0.0;
    for (int viewRow = 0; viewRow < size; viewRow++) {
      Mark record = model.getItem(table.convertRowIndexToModel(viewRow));

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
}