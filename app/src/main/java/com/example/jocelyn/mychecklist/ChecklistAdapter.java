package com.example.jocelyn.mychecklist;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jacob on 4/17/18.
 */

public class ChecklistAdapter extends ArrayAdapter<Checklist> {

    public ChecklistAdapter(Context context, ArrayList<Checklist> items) {
        super(context, 0, items);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //get data item at designated position
        final Checklist checklist = getItem(position);

        //checks if an existing view is being reused
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.checklist_view, parent, false);
        }

        TextView name_view = (TextView) convertView.findViewById(R.id.name_view);
        name_view.setText(checklist.getName());
        Button delete = (Button) convertView.findViewById(R.id.delete_button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(checklist);
                notifyDataSetChanged();
            }
        });


        //return the new view layout to show to screen
        return convertView;


    }
}
