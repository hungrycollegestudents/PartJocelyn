package com.example.jocelyn.mychecklist;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by jacob on 4/18/18.
 */

public class AccountTest {

    @Test
    public void success_registered() throws Exception {
        boolean logged_in = Controller.getInstance().login("new_user", "pass");
        assertEquals(logged_in, true);
    }

    @Test
    public void success_login() throws Exception {
        boolean logged_in = Controller.getInstance().login("test", "abc123");
        assertEquals(logged_in, true);
    }

    @Test
    public void failure_incorrect_password() throws Exception {
        boolean logged_in = Controller.getInstance().login("test", "xyz");
        assertEquals(logged_in, false);
    }
}
