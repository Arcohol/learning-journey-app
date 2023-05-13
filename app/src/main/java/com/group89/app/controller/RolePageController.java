package com.group89.app.controller;

import java.util.List;
import java.util.Objects;
import javax.swing.table.TableRowSorter;

import com.group89.app.model.ListTableModel;
import com.group89.app.model.Role;
import com.group89.app.model.RoleTableModel;
import com.group89.app.utils.JsonConverter;
import com.group89.app.view.comp.RolePage;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.RowFilter;

public class RolePageController {
    private ListTableModel<Role> model;
    private RolePage view;
    private JsonConverter<Role> converter;
    private TableRowSorter<ListTableModel<Role>> sorter;
    private List<Role> list;

    public RolePageController(RolePage rolePage) {
        view = rolePage;
        converter = new JsonConverter<>("roles.json", Role[].class);
        list = converter.toArrayList();
        sorter = new TableRowSorter<>();
        initializeComponents();
    }

    public void initializeComponents() {
        JButton queryButton = view.getQueryButton();
        queryButton.addActionListener(actionEvent -> executeQuery());

        JTable roleTable = view.getTable();
        roleTable.setRowSorter(sorter);

        executeQuery();
    }

    private void executeQuery() {
        String selectedSemester = (String) view.getSemesterBox().getSelectedItem();

        JTable roleTable = view.getTable();
        model = new RoleTableModel(list);
        roleTable.setModel(model);
        // Using an anonymous class for setRowFilter()
        sorter.setRowFilter(new RowFilter<ListTableModel<Role>, Object>() {
            @Override
            public boolean include(RowFilter.Entry<? extends ListTableModel<Role>, ?> entry) {
                ListTableModel<Role> model = entry.getModel();
                Role roleEntry = model.getItem(entry.getIdentifier());
                return Objects.equals(selectedSemester, "all") || roleEntry.getSemester().equals(selectedSemester);
            }
        });
        sorter.setModel(model);
    }
}
