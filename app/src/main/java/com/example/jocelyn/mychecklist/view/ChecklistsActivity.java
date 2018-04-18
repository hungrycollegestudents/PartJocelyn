package com.example.jocelyn.mychecklist.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jocelyn.mychecklist.Controller;
import com.example.jocelyn.mychecklist.model.Checklist;
import com.example.jocelyn.mychecklist.R;

public class ChecklistsActivity extends AppCompatActivity {

    ListView listView;
    ChecklistAdapter adapter;
    Controller controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklists);
        setTitle("My Checklists");

        controller = Controller.getInstance();

        listView = (ListView) findViewById(R.id.listChecklists);

        adapter = new ChecklistAdapter(this, controller.getChecklists());
        listView.setAdapter(adapter);

        if (controller.getChecklists().size() == 0) {
            addChecklist("Test Checklist");
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Checklist checklist = adapter.getItem(i);
                System.out.println(view);
                test(i);
            }
        });

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    public void test(int i) {
        //pass checklist items to new activity
        Intent intent = new Intent(this, ItemsActivity.class);
        intent.putExtra("checklist", controller.getChecklist(i));
        startActivity(intent);
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
                promptAddChecklist();
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
        controller.addChecklist(checklist);
        adapter.notifyDataSetChanged();
    }

    public void promptAddChecklist(){
        AddChecklistDialog newFragment = new AddChecklistDialog();
        newFragment.thingy = new AddChecklistDialog.Thingy() {
            @Override
            public void idk(String name) {
                addChecklist(name);
            }
        };
        newFragment.show(getFragmentManager(), "adding");
    }



}
