package com.group89.app.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import com.group89.app.model.MarkRecord;
import com.group89.app.model.MarkRecordTableModel;
import com.group89.app.utils.JsonConverter;
import com.group89.app.view.comp.MarkRecordPage;

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
      this.value = null;
      ((JComponent) getComponent()).setBorder(new LineBorder(Color.black));
      return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }

    @Override
    public Object getCellEditorValue() {
      return value;
    }
  }

  private MarkRecordPage page;
  private JsonConverter<MarkRecord> converter;
  private List<MarkRecord> list;
  private TableRowSorter<MarkRecordTableModel> sorter;

  public MarkRecordPageController(MarkRecordPage page) {
    this.page = page;
    this.converter = new JsonConverter<>("marks.json", MarkRecord[].class);
    this.list = this.converter.toArrayList();
    this.sorter = new TableRowSorter<>();

    init();
  }

  public void init() {
    this.page.getQueryButton().addActionListener(e -> this.query());
    this.page.getSaveButton().addActionListener(e -> this.save());
    this.page.getDeleteButton().addActionListener(e -> this.delete());
    this.page.getAddButton().addActionListener(e -> this.add());
    this.page.getTable().setRowSorter(sorter);

    query();
  }

  private void query() {
    MarkRecordTableModel model = new MarkRecordTableModel(this.list);
    model.addTableModelListener(e -> this.page.getSaveButton().setEnabled(true));
    model.addTableModelListener(e -> updateLabels());

    String semester = (String) this.page.getSemesterBox().getSelectedItem();
    this.sorter.setRowFilter(new RowFilter<MarkRecordTableModel, Object>() {
      @Override
      public boolean include(Entry<? extends MarkRecordTableModel, ? extends Object> entry) {
        MarkRecordTableModel model = entry.getModel();
        MarkRecord record = model.getItem(entry.getIdentifier());
        return semester.equals("all") || record.getSemester().equals(semester);
      }
    });
    sorter.setModel(model);

    JTable table = this.page.getTable();
    table.setModel(model);
    table.getColumnModel().getColumn(3).setCellEditor(new MarkEditor());
    table.getColumnModel().getColumn(4).setCellEditor(new MarkEditor());
    table.getColumnModel().getColumn(2).setPreferredWidth(200);

    JTableHeader header = table.getTableHeader();
    header.setPreferredSize(new Dimension(0, 30));
    header.setFont(header.getFont().deriveFont(16f));

    updateLabels();
  }

  private void updateLabels() {
    JTable table = this.page.getTable();
    MarkRecordTableModel model = (MarkRecordTableModel) table.getModel();

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

    JLabel[] labels = this.page.getLabels();
    labels[0].setText("Semester: " + this.page.getSemesterBox().getSelectedItem());
    labels[1].setText("Module Count: " + size);
    labels[2].setText("Total Credits: " + totalCreditsCN);
    labels[3].setText("GPA: " + gpa);
    labels[4].setText("Average Mark: " + averageCN);
  }

  private void save() {
    this.converter.toFile(this.list);
    this.page.getSaveButton().setEnabled(false);
  }

  private void delete() {
    JTable table = this.page.getTable();

    int[] rows = table.getSelectedRows();
    for (int i = 0; i < rows.length; i++) {
      rows[i] = table.convertRowIndexToModel(rows[i]);
    }

    ((MarkRecordTableModel) table.getModel()).removeItems(rows);
  }

  private void add() {
    ((MarkRecordTableModel) this.page.getTable().getModel()).addItem(new MarkRecord());
  }
}
