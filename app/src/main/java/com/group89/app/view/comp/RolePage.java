package com.group89.app.view.comp;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.group89.app.controller.MarkRecordPageController;
import com.group89.app.controller.RolePageController;
import com.group89.app.model.CourseType;
import com.group89.app.view.AppColor;

public class RolePage extends JPanel {
    class ILabel extends JLabel {
        public ILabel(String text) {
            super(text);

            setPreferredSize(LABEL_SIZE);
            setFont(getFont().deriveFont(FONT_SIZE));
            setHorizontalAlignment(JLabel.CENTER);
        }
    }

    class IButton extends JButton {
        public IButton(String text) {
            super(text);

            setPreferredSize(BUTTON_SIZE);
            setBackground(AppColor.LIGHT_GREY);
            setForeground(AppColor.BLACK);
            setFont(getFont().deriveFont(FONT_SIZE));
        }
    }

    class ITable extends JTable {
        public ITable() {
            super();

            setRowHeight(ROW_HEIGHT);
            setFont(getFont().deriveFont(FONT_SIZE));
            setFillsViewportHeight(true);

            getTableHeader().setFont(getFont().deriveFont(HEADER_FONT_SIZE));
            getTableHeader().setPreferredSize(HEADER_SIZE);

            // this is a suspicious way to change the font size while keeping the same editor
            // it overrides the default cell editor managed by JTable
            JComponent objectEditor =
                    (JComponent) ((DefaultCellEditor) (getDefaultEditor(Object.class))).getComponent();
            objectEditor.setFont(objectEditor.getFont().deriveFont(FONT_SIZE));

            JComponent numberEditor =
                    (JComponent) ((DefaultCellEditor) (getDefaultEditor(Number.class))).getComponent();
            numberEditor.setFont(numberEditor.getFont().deriveFont(FONT_SIZE));
        }
    }

    public static final String[] SEMESTERS = {"all", "2020-2021-1", "2020-2021-2", "2021-2022-1",
            "2021-2022-2", "2022-2023-1", "2022-2023-2", "2023-2024-1", "2023-2024-2"};

    private static final int LABEL_COUNT = 1;
    private static final float FONT_SIZE = 16f;
    private static final float HEADER_FONT_SIZE = 18f;
    private static final Dimension LABEL_SIZE = new Dimension(150, 30);
    private static final Dimension BUTTON_SIZE = new Dimension(100, 30);
    private static final Dimension HEADER_SIZE = new Dimension(0, 40);
    private static final int ROW_HEIGHT = 30;

    private JComboBox<String> semesterBox;
    private JButton queryButton, saveButton, deleteButton, addButton;
    private JLabel[] labels;
    private JScrollPane scrollPane;
    private JTable table;

    public RolePage() {
        super(new GridBagLayout());

        semesterBox = new MyComboBox<>(SEMESTERS);

        queryButton = new IButton("Query");
        saveButton = new IButton("Save");
        deleteButton = new IButton("Delete");
        addButton = new IButton("Add");

        labels = new JLabel[LABEL_COUNT];
        labels[0] = new ILabel("Semester:");

        scrollPane = new JScrollPane();
        table = new ITable();

        GridBagConstraints c = new GridBagConstraints();

        c.insets.set(10, 10, 0, 0);

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        add(semesterBox, c);


        c.insets.set(10, 0, 0, 10);

        c.gridx = 4;
        c.gridy = 0;
        c.anchor = GridBagConstraints.EAST;
        add(queryButton, c);

        c.weightx = 1;

        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 1;
        add(labels[0], c);

        c.weightx = 1;
        c.weighty = 1;

        c.insets.set(10, 10, 10, 10);

        scrollPane.setViewportView(table);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 5;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        add(scrollPane, c);

        c.weightx = 1;
        c.weighty = 0;
        c.gridwidth = 1;

        c.insets.set(0, 10, 10, 10);

        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        add(addButton, c);

        saveButton.setEnabled(false);
        c.gridx = 2;
        c.gridy = 3;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        add(saveButton, c);

        c.gridx = 4;
        c.gridy = 3;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        add(deleteButton, c);

        new RolePageController(this);
    }

    public JComboBox<String> getSemesterBox() {
        return semesterBox;
    }

    public JButton getQueryButton() {
        return queryButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JLabel[] getLabels() {
        return labels;
    }

    public JTable getTable() {
        return table;
    }
}
