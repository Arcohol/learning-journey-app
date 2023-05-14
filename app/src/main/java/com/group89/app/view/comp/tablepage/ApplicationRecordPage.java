package com.group89.app.view.comp.tablepage;

import com.group89.app.controller.ApplicationRecordPageController;

public class ApplicationRecordPage extends DefaultTablePage {
  public ApplicationRecordPage() {
    super();

    new ApplicationRecordPageController(this);
  }
}
