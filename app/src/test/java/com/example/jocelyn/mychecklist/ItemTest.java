package com.example.jocelyn.mychecklist;

import com.example.jocelyn.mychecklist.model.Checklist;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by jacob on 4/18/18.
 */

public class ItemTest {

    @Test
    public void success_create() throws Exception {
        Checklist cl = new Checklist("Test");
        Controller.getInstance().setCurrentChecklist(cl);

        Controller.getInstance().addItem("test", 1);

        assertEquals(cl.getLineItems().size(), 1);
    }

    @Test
    public void success_edit() throws Exception {
        Checklist cl = new Checklist("Test");
        Controller.getInstance().setCurrentChecklist(cl);

        Controller.getInstance().addItem("test", 1);

        Controller.getInstance().editItem(0, "new");
        assertEquals(Controller.getInstance().getLineItem(0).getItem().getName(), "new");
    }

    @Test
    public void success_delete() throws Exception {
        Checklist cl = new Checklist("Test");
        Controller.getInstance().setCurrentChecklist(cl);

        Controller.getInstance().addItem("test", 1);

        Controller.getInstance().getLineItem(0).setChecked(true);
        Controller.getInstance().deleteCheckedItems();

        assertEquals(Controller.getInstance().getLineItems().size(), 0);
    }

    @Test
    public void failure_no_name() throws Exception {
        Checklist cl = new Checklist("Test");
        Controller.getInstance().setCurrentChecklist(cl);

        Controller.getInstance().addItem("test", 1);

        Controller.getInstance().editItem(0, "");
        assertNotEquals(Controller.getInstance().getLineItem(0).getItem().getName(), "");
    }
}
