package com.example.willi.buddy;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /* MainActivity main function
       1. When app first boot, will check if any To-do record exist in DB, dialog box appear if not.
    */

    /*Declaring of objects and variables
        including bottom navigation bar
    */
    private final String PREFS_NAME = "MyPrefsFile";
    public static final String WITHSPACE = "com.example.willi.buddy.MESSAGE";

    private EditText meTxtSearch;
    private Button mbtn_search, mbtn_Quiz, mbtn_todo;

    //Navigation bar, on item select listener
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_Quiz:
                    startActivity(new Intent(MainActivity.this, quiz_home.class));
                    return true;
                case R.id.navigation_todo:
                    startActivity(new Intent(MainActivity.this, to_do.class));
                    return true;
            }
            return false;
        }
    };

    /*
    Methods
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbtn_search = findViewById(R.id.btn_search);
        mbtn_Quiz = findViewById(R.id.home_btnQuiz);
        mbtn_todo = findViewById(R.id.home_btnTodo);
        meTxtSearch = findViewById(R.id.etxtSearch);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Button on click listener
        mbtn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String schWspace  = meTxtSearch.getText().toString().trim();
                if(!schWspace.equals("")){
                    Intent intent = new Intent(MainActivity.this, searchresult.class);
                    intent.putExtra(WITHSPACE, schWspace);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please enter something to search", Toast.LENGTH_SHORT).show();
                    meTxtSearch.requestFocus();
                }
            }
        });

        mbtn_Quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, quiz_home.class));
            }
        });

        mbtn_todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, to_do.class));
            }
        });

    }
    //Call checkRowexist method and show dialog box if method return false

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getBoolean("my_first_time", true))
        {
            if(checkRowexist() == true){
                finish();
            }
            else
            {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                mBuilder.setMessage("Seem like you have no task on your To-DO list. \nSelect Quiz to start testing your knowledge!").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = mBuilder.create();
                alert.show();
            }
            settings.edit().putBoolean("my_first_time", false).commit();
        }
    }
    //Check DB for records, return true any records found
    public boolean checkRowexist(){
        QuizDbHelper mDatabaseHelper;
        mDatabaseHelper = new QuizDbHelper(this);
        boolean check = mDatabaseHelper.checkAnyRowexist();
        return check;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
