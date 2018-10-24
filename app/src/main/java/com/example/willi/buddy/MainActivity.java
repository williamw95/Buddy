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

public class MainActivity extends AppCompatActivity {

    private final String PREFS_NAME = "MyPrefsFile";

    public static final String WITHSPACE = "com.example.willi.buddy.MESSAGE";

    private EditText meTxtSearch;
    private Button mbtn_search, mbtn_Quiz, mbtn_todo;

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

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbtn_search = findViewById(R.id.btn_search);
        mbtn_Quiz = findViewById(R.id.home_btnQuiz);
        mbtn_todo = findViewById(R.id.home_btnTodo);

        mbtn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, searchresult.class);
                  String schWspace  = meTxtSearch.getText().toString();
                intent.putExtra(WITHSPACE, schWspace);
                startActivity(intent);
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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    public boolean checkRowexist(){
        QuizDbHelper mDatabaseHelper;
        mDatabaseHelper = new QuizDbHelper(this);
        boolean check = mDatabaseHelper.checkAnyRowexist();
        return check;
    }
}
