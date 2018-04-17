package com.example.jocelyn.mychecklist;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.nfc.Tag;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Iterator;

public class ChecklistsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Checklist> checklists = new ArrayList<>();
    ChecklistAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checklists.add(new Checklist("Default"));

        listView = (ListView) findViewById(R.id.listItems);

        adapter = new ChecklistAdapter(this, checklists);
        listView.setAdapter(adapter);

       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox);

                boolean checked = !checkbox.isChecked();

                checkbox.setChecked(checked);

                Checklist checklist = adapter.getItem(i);
                lineItem.setChecked(checked);

                adapter.notifyDataSetChanged();

            }
        });*/

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }


    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.checklist_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_addItem:
                //promptAddItem();
                return true;

            case R.id.action_deleteItem:
                //deleteCheckedItems();
                return true;

            case R.id.action_refreshPrices:
                //refreshPrices();
                return true;

            case R.id.action_clearList:
                //clearList();
                return true;
        }
        return false;

    }

    public void addChecklist(String name) {
        Checklist checklist = new Checklist(name);
        checklists.add(checklist);
        adapter.notifyDataSetChanged();
    }

    public void promptAddItem(){
        AddChecklistDialog newFragment = new AddChecklistDialog();
        newFragment.thingy = new AddChecklistDialog.Thingy() {
            @Override
            public void idk(String name) {
                System.out.println("WOW!");
                addChecklist(name);
            }
        };
        newFragment.show(getFragmentManager(), "adding");
    }



}
