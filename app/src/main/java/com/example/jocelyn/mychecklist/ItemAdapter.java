package com.example.jocelyn.mychecklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jocelyn on 2/21/2018.
 */

public class ItemAdapter extends ArrayAdapter<LineItem> {

    public ItemAdapter(Context context, ArrayList<LineItem> items){
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //get data item at designated position
        LineItem lineItem = getItem(position);
        Item item = lineItem.getItem();

        //checks if an existing view is being reused
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_view, parent, false);
        }


        TextView name_View = (TextView) convertView.findViewById(R.id.name_view);
        TextView quantity_view = (TextView) convertView.findViewById(R.id.quantity_view);


        name_View.setText(item.getName());
        quantity_view.setText(String.valueOf(lineItem.getQuantity()));

        //return the new view layout to show to screen
        return convertView;


    }
}
