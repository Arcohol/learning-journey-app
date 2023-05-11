package com.group89.app.view.comp;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import com.group89.app.controller.OutlookPageController;
import com.group89.app.controller.TaskPageController;
import com.group89.app.view.AppColor;


public class OutlookPage extends JPanel {

    private  JTable outlooks;
    private JScrollPane scrollPane;
    private static final int ROW_HEIGHT = 30;
    private static final float FONT_SIZE = 16f;
    private JLabel[] labels;
    class ITable extends JTable {
        public ITable() {
            super();
            setRowHeight(ROW_HEIGHT);
            setFont(getFont().deriveFont(FONT_SIZE));
            setFillsViewportHeight(true);

            JComponent objectEditor =
                    (JComponent) ((DefaultCellEditor) (getDefaultEditor(Object.class))).getComponent();
            objectEditor.setFont(objectEditor.getFont().deriveFont(FONT_SIZE));
        }
    }

    class ILabel extends JLabel {
        public ILabel(String text) {
            super(text);
            setPreferredSize(new Dimension(500, 30));
            Font font = new Font("Arial", Font.BOLD, 30);
            setFont(font);;
            setHorizontalAlignment(JLabel.CENTER);
        }
    }

    public OutlookPage() {
        super(new GridBagLayout());

        outlooks = new ITable();
        scrollPane = new JScrollPane(outlooks);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(30, 10, 10, 10);

        labels = new JLabel[1];
        labels[0] = new OutlookPage.ILabel("Potential Schools");

        c.gridx = 5;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        add(labels[0], c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 6;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        add(scrollPane, c);

        new OutlookPageController(this);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JTable getTable() {
        return outlooks;
    }

}
