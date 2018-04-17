package com.example.jocelyn.mychecklist;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by jacob on 4/17/18.
 */

public class ChecklistAdapter extends ArrayAdapter<Checklist> {

    public ChecklistAdapter(Context context, ArrayList<Checklist> items) {
        super(context, 0, items);
    }
}
