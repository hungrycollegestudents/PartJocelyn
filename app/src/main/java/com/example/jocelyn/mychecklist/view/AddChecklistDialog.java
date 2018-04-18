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

public class AddChecklistDialog extends DialogFragment {

    public Thingy thingy;

    public abstract static class Thingy {
        public abstract void idk(String name);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.add_checklist_dialog_layout, null))
                .setMessage(R.string.userAddItem)
                .setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText nameField = (EditText) ((Dialog)
                                dialogInterface).findViewById(R.id.checklistEntry);
                        Editable nameEditable = nameField.getText();
                        String itemName = nameEditable.toString();

                        if (thingy != null) {
                            thingy.idk(itemName);
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
