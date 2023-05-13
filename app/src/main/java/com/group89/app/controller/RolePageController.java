package com.group89.app.controller;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.TableRowSorter;

import com.group89.app.model.ListTableModel;
import com.group89.app.model.Role;
import com.group89.app.model.RoleTableModel;
import com.group89.app.utils.JsonConverter;
import com.group89.app.view.comp.MyComboBox;
import com.group89.app.view.comp.RolePage;

// controller
public class RolePageController {
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
    private ListTableModel<Role> model;
    // view
    private RolePage view;

    private JsonConverter<Role> converter;
    private TableRowSorter<ListTableModel<Role>> sorter;
    // actual data
    private List<Role> list;

    public RolePageController(RolePage page) {
        view = page;
        sorter = new TableRowSorter<>();
        converter = new JsonConverter<>("roles.json", Role[].class);
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
        String semester = (String) view.getSemesterBox().getSelectedItem();

        JTable table = view.getTable();
        model = new RoleTableModel(list);
        table.setModel(model);

        model.addTableModelListener(e -> view.getSaveButton().setEnabled(true));
        model.addTableModelListener(e -> updateLabels());

//        System.out.println("table: "+table.get());
//        System.out.println("RowCount: " +table.getRowCount());
//        System.out.println("ColCount: "+table.getColumnCount());
//        System.out.println("name1:"+table.getColumnName(0));
//        System.out.println("name2:"+table.getColumnName(1));

        table.getColumn("Type").setPreferredWidth(200);
        table.getColumn("Semester")
                .setCellEditor(new DefaultCellEditor(new MyComboBox<>(RolePage.SEMESTERS)));

        sorter.setRowFilter(new RowFilter<ListTableModel<Role>, Object>() {
            @Override
            public boolean include(Entry<? extends ListTableModel<Role>, ? extends Object> entry) {
                ListTableModel<Role> model = entry.getModel();
                Role record = model.getItem(entry.getIdentifier());
                return (semester.equals("all") || record.getSemester().equals(semester));
            }
        });
        sorter.setModel(model);

        updateLabels();
    }

    private void updateLabels() {
        JTable table = view.getTable();

        int size = table.getRowCount();

        System.out.println("size: " + size);

        for (int viewRow = 0; viewRow < size; viewRow++) {
            Role record = model.getItem(table.convertRowIndexToModel(viewRow));
        }

        DecimalFormat df = new DecimalFormat("#.##");

        JLabel[] labels = view.getLabels();
        labels[0].setText("Semester: " + view.getSemesterBox().getSelectedItem());
    }

    private void add() {
        model.addItem(new Role());
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
