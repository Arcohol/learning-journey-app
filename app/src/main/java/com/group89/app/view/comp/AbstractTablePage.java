package com.group89.app.view.comp;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public abstract class AbstractTablePage extends JPanel {
  protected JTable table;
  protected JScrollPane scrollPane;

  public JTable getTable() {
    return table;
  }

  public JScrollPane getScrollPane() {
    return scrollPane;
  }
}
