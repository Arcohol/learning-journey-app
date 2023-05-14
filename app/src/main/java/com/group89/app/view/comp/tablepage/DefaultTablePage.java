package com.group89.app.view.comp.tablepage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JScrollPane;
import com.group89.app.view.comp.IButton;
import com.group89.app.view.comp.ITable;

public class DefaultTablePage extends AbstractTablePage {
  public DefaultTablePage() {
    super();

    setLayout(new GridBagLayout());

    table = new ITable();
    scrollPane = new JScrollPane(table);
    addButton = new IButton("Add");
    deleteButton = new IButton("Delete");
    saveButton = new IButton("Save");


    GridBagConstraints c = new GridBagConstraints();
  
    c.insets = new Insets(10, 10, 10, 10);

    c.gridx = 0;
    c.gridy = 1;
    c.weightx = 1;
    c.weighty = 1;
    c.gridwidth = 3;
    c.fill = GridBagConstraints.BOTH;
    add(scrollPane, c);
    
    c.gridwidth = 1;
    c.weightx = 1;
    c.weighty = 0;
    c.fill = GridBagConstraints.NONE;

    c.insets.set(0, 10, 10, 10);

    c.gridx = 0;
    c.gridy = 2;
    c.anchor = GridBagConstraints.WEST;
    add(addButton, c);

    c.gridx = 1;
    c.gridy = 2;
    c.anchor = GridBagConstraints.CENTER;
    add(deleteButton, c);

    c.gridx = 2;
    c.gridy = 2;
    c.anchor = GridBagConstraints.EAST;
    add(saveButton, c);
  }
}
