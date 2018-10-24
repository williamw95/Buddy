package com.example.willi.buddy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import java.util.List;

public class quiz_home extends AppCompatActivity {


    Button c1, c2, c3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_home);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        c1 = findViewById(R.id.b1);
        c2 = findViewById(R.id.b2);
        c3 = findViewById(R.id.b3);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(quiz_home.this, quiz_start.class);
                intent.putExtra("quizCat", 1);
                startActivity(intent);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(quiz_home.this, quiz_start.class);
                intent.putExtra("quizCat", 2);
                startActivity(intent);
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(quiz_home.this, quiz_start.class);
                intent.putExtra("quizCat", 3);
                startActivity(intent);
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(quiz_home.this, MainActivity.class));
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_Quiz:
                    //mTextMessage.setText(R.string.title_quiz);
                    return true;
                case R.id.navigation_todo:
                    //mTextMessage.setText(R.string.title_Todo);
                    startActivity(new Intent(quiz_home.this, to_do.class));
                    return true;
            }
            return false;
        }
    };

}