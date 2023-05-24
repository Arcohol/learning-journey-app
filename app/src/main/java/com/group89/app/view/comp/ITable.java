package com.group89.app.view.comp;

import java.awt.Dimension;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;

/**
 * Custom table component.
 */
public class ITable extends JTable {
  private static final float FONT_SIZE = 16f;
  private static final float HEADER_FONT_SIZE = 18f;
  private static final Dimension HEADER_SIZE = new Dimension(0, 40);
  private static final int ROW_HEIGHT = 30;

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
