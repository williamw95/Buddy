package com.example.willi.buddy;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class quiz_start extends AppCompatActivity {

    TextView ques;
    Button OptA, OptB, OptC, OptD;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setMessage("Are you sure you want to end the quiz?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(quiz_start.this, MainActivity.class));
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = mBuilder.create();
        alert.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_start);
        ques = findViewById(R.id.TxtQuestions);
        OptA = findViewById(R.id.btnOptA);
        OptB = findViewById(R.id.btnOptB);
        OptC = findViewById(R.id.btnOptC);
        OptD = findViewById(R.id.btnOptD);

        OptA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(quiz_start.this, popup.class));
            }
        });
    }
}
