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
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

public class quiz_home extends AppCompatActivity {

    Button c1, c2, c3;
    private ProgressDialog progressBar;

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
                //To show button click
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);
                //call dialog method
                progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                showdialog();
                //This handler will add a delay of 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(quiz_home.this, quiz_start.class);
                       //intent.putExtra(Message, "c1");//by this statement we are sending the name of the button that invoked the new Questions.java activity "Message" is defined by the name of the package + MESSAGE
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

    }

    private void showdialog() {
        progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any where on screen
        progressBar.setMessage("Getting Questions Ready ...");//Title shown in the progress bar
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
        progressBar.setProgress(0);//attributes
        progressBar.setMax(100);//attributes
        progressBar.show();//show the progress bar
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
                    startActivity(new Intent(quiz_home.this, Todo.class));
                    return true;
            }
            return false;
        }
    };

}
