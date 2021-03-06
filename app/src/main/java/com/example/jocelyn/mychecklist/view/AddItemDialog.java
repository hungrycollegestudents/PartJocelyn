package com.example.jocelyn.mychecklist.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.example.jocelyn.mychecklist.R;

/**
 * Created by Jocelyn on 2/22/2018.
 */

public class AddItemDialog extends DialogFragment {

    public Thingy thingy;

    public abstract static class Thingy {
        public abstract void idk(String name, int quantity);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.add_item_dialog_layout, null))
                .setMessage(R.string.userAddItem)
                .setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText nameField = (EditText) ((Dialog)
                                dialogInterface).findViewById(R.id.itemEntry);
                        Editable nameEditable = nameField.getText();
                        String itemName = nameEditable.toString();

                        EditText quantityField = (EditText) ((Dialog)
                                dialogInterface).findViewById(R.id.quantityEntry);
                        Editable quantityEditable = quantityField.getText();
                        String quantity_string = quantityEditable.toString();

                        if (thingy != null) {
                            thingy.idk(itemName, Integer.valueOf(quantity_string));
                        }

                    }
                })
                .setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return builder.create();
    }

}
