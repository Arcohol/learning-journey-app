package com.group89.app.view.comp;

import com.group89.app.controller.RolePageController;
import javax.swing.*;
import java.awt.*;

public class RolePage extends JPanel {
    public static final String[] SEMESTERS = {"all", "2020-2021-1", "2020-2021-2", "2021-2022-1",
            "2021-2022-2", "2022-2023-1", "2022-2023-2", "2023-2024-1", "2023-2024-2"};
    private static final Dimension LABEL_SIZE = new Dimension(200, 40);
    private static final Dimension BUTTON_SIZE = new Dimension(150, 40);
    private static final Dimension HEADER_SIZE = new Dimension(0, 40);
    private static final int FONT_SIZE = 20;
    private static final int HEADER_FONT_SIZE = 25;
    private static final int Height = 40;
    private static final int LABEL_NUM = 2;

    private JLabel[] labels;
    private JComboBox<String> semesterBox;
    private JScrollPane scrollPane;
    private JTable table;
    private JButton queryButton;


    class myLabel extends JLabel {
        public myLabel(String text) {
            super(text);
            setHorizontalAlignment(JLabel.CENTER);
            setPreferredSize(LABEL_SIZE);
            setFont(getFont().deriveFont(FONT_SIZE));
        }
    }

    class myButton extends JButton {
        public myButton(String text) {
            super(text);
            setPreferredSize(BUTTON_SIZE);
            setFont(getFont().deriveFont(FONT_SIZE));
        }
    }

    class myTable extends JTable {
        public myTable() {
            super();
            setFont(getFont().deriveFont(FONT_SIZE));
            setRowHeight(Height);
            getTableHeader().setFont(getFont().deriveFont(HEADER_FONT_SIZE));
            getTableHeader().setPreferredSize(HEADER_SIZE);
            setFillsViewportHeight(true);
        }
    }

    public RolePage() {
        super(new GridBagLayout());
        labels = new JLabel[LABEL_NUM];
        labels[0] = new myLabel("Please choose the semester");
        labels[1] = new myLabel("Semester:");

        queryButton = new myButton("Query");
        semesterBox = new MyComboBox<>(SEMESTERS);
        table = new myTable();
        scrollPane= new JScrollPane(table);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;
        add(labels[0], gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(semesterBox, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(queryButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);

        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridwidth = 1;
        gbc.insets.set(0, 10, 10, 10);

        setVisible(true);

        new RolePageController(this);
    }

    public JLabel[] getLabels() {
        return labels;
    }

    public void setLabels(JLabel[] labels) {
        this.labels = labels;
    }

    public JComboBox<String> getSemesterBox() {
        return semesterBox;
    }

    public void setSemesterBox(JComboBox<String> semesterBox) {
        this.semesterBox = semesterBox;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JButton getQueryButton() {
        return queryButton;
    }

    public void setQueryButton(JButton queryButton) {
        this.queryButton = queryButton;
    }
}
