package com.group89.app.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddMarkRecordFrame extends JFrame {
  private static final int LABEL_COUNT = 5;
  private static final int TEXT_FIELD_COUNT = 5;
  private JLabel[] labels;
  private JTextField[] textFields;
  private JButton submitButton;
  private JButton clearButton;

  public AddMarkRecordFrame() {
    super("Add Mark Record");
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setPreferredSize(new Dimension(400, 300));
    this.setMinimumSize(new Dimension(400, 300));
    this.setLocationRelativeTo(null);
    this.setVisible(true);
    this.setLayout(new GridBagLayout());

    this.labels = new JLabel[LABEL_COUNT];
    this.labels[0] = new JLabel("semester");
    this.labels[1] = new JLabel("module code");
    this.labels[2] = new JLabel("title");
    this.labels[3] = new JLabel("mark");
    this.labels[4] = new JLabel("credits");
    for (JLabel label : this.labels) {
      label.setPreferredSize(new Dimension(100, 20));
      label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    this.textFields = new JTextField[TEXT_FIELD_COUNT];
    this.textFields[0] = new JTextField();
    this.textFields[1] = new JTextField();
    this.textFields[2] = new JTextField();
    this.textFields[3] = new JTextField();
    this.textFields[4] = new JTextField();
    for (JTextField textField : this.textFields) {
      textField.setPreferredSize(new Dimension(200, 20));
      textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    this.submitButton = new JButton("Submit");
    this.submitButton.setPreferredSize(new Dimension(100, 20));

    this.clearButton = new JButton("Clear");
    this.clearButton.setPreferredSize(new Dimension(100, 20));

    GridBagConstraints c = new GridBagConstraints();

    c.insets.set(10, 10, 10, 10);

    c.gridx = 0;
    c.gridy = 0;
    this.add(this.labels[0], c);
    c.gridx = 0;
    c.gridy = 1;
    this.add(this.labels[1], c);
    c.gridx = 0;
    c.gridy = 2;
    this.add(this.labels[2], c);
    c.gridx = 0;
    c.gridy = 3;
    this.add(this.labels[3], c);
    c.gridx = 0;
    c.gridy = 4;
    this.add(this.labels[4], c);

    c.gridx = 1;
    c.gridy = 0;
    this.add(this.textFields[0], c);
    c.gridx = 1;
    c.gridy = 1;
    this.add(this.textFields[1], c);
    c.gridx = 1;
    c.gridy = 2;
    this.add(this.textFields[2], c);
    c.gridx = 1;
    c.gridy = 3;
    this.add(this.textFields[3], c);
    c.gridx = 1;
    c.gridy = 4;
    this.add(this.textFields[4], c);

    c.gridx = 0;
    c.gridy = 5;
    this.add(this.clearButton, c);

    c.gridx = 1;
    c.gridy = 5;
    this.add(this.submitButton, c);
  }

  public JButton getSubmitButton() {
    return this.submitButton;
  }

  public JButton getClearButton() {
    return this.clearButton;
  }

  public JTextField[] getTextFields() {
    return this.textFields;
  }
}
