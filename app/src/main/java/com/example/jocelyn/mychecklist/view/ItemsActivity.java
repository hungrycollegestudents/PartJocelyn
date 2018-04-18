package com.example.jocelyn.mychecklist.view;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.jocelyn.mychecklist.Controller;
import com.example.jocelyn.mychecklist.api.APIAdapter;
import com.example.jocelyn.mychecklist.model.Checklist;
import com.example.jocelyn.mychecklist.model.Item;
import com.example.jocelyn.mychecklist.model.LineItem;
import com.example.jocelyn.mychecklist.R;

import java.util.Iterator;

public class ItemsActivity extends AppCompatActivity {

    ListView listView;
    View totalView;
    //ArrayList<LineItem> itemsArray = new ArrayList<>();
    Checklist checklist;
    ItemAdapter adapter;

    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = Controller.getInstance();
        controller.setupAPI(getApplicationContext());

        checklist = (Checklist) getIntent().getSerializableExtra("checklist");
        controller.setCurrentChecklist(checklist);
        setTitle(checklist.getName());

        listView = (ListView) findViewById(R.id.listItems);

        totalView = getLayoutInflater().inflate(R.layout.total_view, null);
        listView.addFooterView(totalView);

        adapter = new ItemAdapter(this, checklist.getLineItems());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox);

                boolean checked = !checkbox.isChecked();

                checkbox.setChecked(checked);

                LineItem lineItem = adapter.getItem(i);
                lineItem.setChecked(checked);

                adapter.notifyDataSetChanged();

            }
        });

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        for (LineItem lineItem : checklist.getLineItems()) {
            checklist.add(lineItem);
        }
        refreshPrices();
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
                promptAddItem();
                return true;

            case R.id.action_deleteItem:
                deleteCheckedItems();
                return true;

            case R.id.action_refreshPrices:
                refreshPrices();
                return true;

            case R.id.action_clearList:
                clearList();
                return true;
        }
        return false;

    }

    public void promptAddItem(){
        AddItemDialog newFragment = new AddItemDialog();
        newFragment.thingy = new AddItemDialog.Thingy() {
            @Override
            public void idk(String name, int quantity) {
                addItem(name, quantity);
            }
        };
        newFragment.show(getFragmentManager(), "adding");
    }

    public void addItem(String name, int quantity) {

        controller.addItem(name, quantity);
        adapter.notifyDataSetChanged();

        ((TextView) totalView.findViewById(R.id.total_text)).setText(String.format("Total: %.2f", getTotal()));
    }

    public float getTotal() {
        float total = 0;
        for (LineItem lineItem : checklist.getLineItems()) {
            total += lineItem.getQuantity() * lineItem.getItem().getPrice().getAmount();
        }
        return total;
    }

    public void refreshPrices() {
        controller.refreshPrices();
        adapter.notifyDataSetChanged();
    }

    public void clearList() {
        controller.clearChecklist();
        adapter.notifyDataSetChanged();
    }

    public void deleteCheckedItems() {
        controller.deleteCheckedItems();
        adapter.notifyDataSetChanged();
        listView.invalidate();
    }
}
