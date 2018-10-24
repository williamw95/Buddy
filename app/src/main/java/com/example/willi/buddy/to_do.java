package com.example.willi.buddy;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class to_do extends AppCompatActivity {

    QuizDbHelper mDatabaseHelper;
    private ListView mListView;
    private static final String TAG = "To-DO";

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(to_do.this, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        mListView = findViewById(R.id.todo_Listview);
        mDatabaseHelper = new QuizDbHelper(this);
        populateListView();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_todo);
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getTODOData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }
        //create the list adapter and set the adapter
        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String titlename = adapterView.getItemAtPosition(position).toString();
                Log.d(TAG, "onItemClick: you clicked on " + titlename);
                Cursor data2 = mDatabaseHelper.getRowData(titlename);
                int itemID =-1;
                String title = null;
                String URL = null;
                while(data2.moveToNext()){
                    itemID = data2.getInt(0);
                    title = data2.getString(1);
                    URL = data2.getString(2);
                }
                if(itemID > -1){
                    Log.d(TAG, "onItemClick: This ID is: " + itemID);
                }
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_Quiz:
                    startActivity(new Intent(to_do.this, quiz_home.class));
                    return true;
                case R.id.navigation_todo:
                    //mTextMessage.setText(R.string.title_Todo);
                    return true;
            }
            return false;
        }
    };




}
