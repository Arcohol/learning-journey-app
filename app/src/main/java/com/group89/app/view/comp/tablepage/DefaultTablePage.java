package com.group89.app.view.comp.tablepage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import com.group89.app.view.comp.Footer;
import com.group89.app.view.comp.Header;
import com.group89.app.view.comp.ITable;

/**
 * A table page with a header and a footer.
 */
public class DefaultTablePage extends AbstractTablePage {
  private Header header;
  private Footer footer;

  public DefaultTablePage() {
    super();

    setLayout(new GridBagLayout());

    header = new Header();
    footer = new Footer();

    table = new ITable();
    scrollPane = new JScrollPane(table);

    addButton = footer.getAddButton();
    deleteButton = footer.getDeleteButton();
    saveButton = footer.getSaveButton();


    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;

    c.insets.set(10, 10, 10, 10);

    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1;
    c.weighty = 0;
    add(header, c);

    c.insets.set(0, 10, 10, 10);

    c.gridx = 0;
    c.gridy = 1;
    c.weightx = 1;
    c.weighty = 1;
    add(scrollPane, c);

    c.gridx = 0;
    c.gridy = 2;
    c.weightx = 1;
    c.weighty = 0;
    add(footer, c);
  }

  /**
   * Returns the header.
   * 
   * @return the header
   */
  public Header getHeader() {
    return header;
  }

  /**
   * Returns the footer.
   * 
   * @return the footer
   */
  public Footer getFooter() {
    return footer;
  }
}
