package com.group89.app.controller;

import java.util.List;
import com.group89.app.model.ListTableModel;
import com.group89.app.model.Outlook;
import com.group89.app.model.OutlookTableModel;
import com.group89.app.view.comp.OutlookPage;
import com.group89.app.utils.JsonConverter;


public class OutlookPageController {

    // model
    private ListTableModel<Outlook> model;
    // view
    private OutlookPage view;
    private JsonConverter<Outlook> converter;
    // actual data
    private List<Outlook> list;

    public OutlookPageController(OutlookPage page) {
        view = page;

        converter = new JsonConverter<>("outlooks.json", Outlook[].class);
        list = converter.toArrayList();

        init();
    }

    private void init() {
        model = new OutlookTableModel(list);
        view.getTable().setModel(model);
    }
}
