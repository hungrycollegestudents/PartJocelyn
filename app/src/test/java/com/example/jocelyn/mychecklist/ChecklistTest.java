package com.example.jocelyn.mychecklist;

import com.example.jocelyn.mychecklist.model.Checklist;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by jacob on 4/18/18.
 */

public class ChecklistTest {

    @Test
    public void success_create() throws Exception {
        int initialSize = Controller.getInstance().getChecklists().size();
        Checklist newCL = new Checklist("Test");
        Controller.getInstance().addChecklist(newCL);

        //Checklists size increase by 1
        assertEquals(Controller.getInstance().getChecklists().size(), initialSize+1);

        //Last checklist is new checklist
        assertEquals(Controller.getInstance().getChecklist(initialSize), newCL);
    }

    @Test
    public void success_edit() throws Exception {
        Checklist newCL = new Checklist("Test");
        Controller.getInstance().addChecklist(newCL);

        Checklist cl = Controller.getInstance().getChecklist(0);
        String initialName = cl.getName();
        Controller.getInstance().editChecklist(0, "Wow");

        assertNotEquals(initialName, cl.getName());
        assertEquals(cl.getName(), "Wow");
    }

    @Test
    public void success_delete() throws Exception {
        Checklist newCL = new Checklist("Test");
        Controller.getInstance().addChecklist(newCL);
        Checklist newCL2 = new Checklist("Test2");
        Controller.getInstance().addChecklist(newCL2);

        int size = Controller.getInstance().getChecklists().size();

        Controller.getInstance().deleteChecklist(1);
        assertEquals(Controller.getInstance().getChecklists().size(), size-1);
    }

    @Test
    public void success_delete_last() throws Exception {

        Checklist newCL = new Checklist("Test");
        Controller.getInstance().addChecklist(newCL);

        int s = Controller.getInstance().getChecklists().size();
        for (int i = 0; i < s; i++) {
            Controller.getInstance().deleteChecklist(0);
        }

        assertEquals(Controller.getInstance().getChecklists().size(), 1);
        Controller.getInstance().deleteChecklist(0);
        assertEquals(Controller.getInstance().getChecklists().size(), 1);
        assertEquals(Controller.getInstance().getChecklist(0).getLineItems().size(), 0);
    }

    @Test
    public void failure_no_name() throws Exception {
        Checklist newCL = new Checklist("Test");
        Controller.getInstance().addChecklist(newCL);


        Checklist cl = Controller.getInstance().getChecklist(0);
        String initialName = cl.getName();
        Controller.getInstance().editChecklist(0, "");

        assertEquals(initialName, cl.getName());
        assertNotEquals(cl.getName(), "");
    }
}
