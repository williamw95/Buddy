package com.example.willi.buddy;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;

public class Todo_content extends AppCompatActivity {

    Button btn_contentview;
    Button btn_delContent;
    TextView txtTitle;
    QuizDbHelper mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_content);
        btn_contentview = findViewById(R.id.btn_viewcontent);
        btn_delContent = findViewById(R.id.btn_delcontent);
        txtTitle = findViewById(R.id.todo_txtTitle);

        //Getting intent from To_do activity
        Intent intent = getIntent();
        final int itemID = intent.getIntExtra("itemID", 0);
        final String itemTitle = intent.getStringExtra("title");
        final String itemURL = intent.getStringExtra("url");

        txtTitle.setText(itemTitle);

        //Checking for URL that and set Button logo accordingly
        if(chkURLorYT(itemURL) == true){
            Drawable top = getResources().getDrawable(R.drawable.ic_youtube);
            btn_contentview.setCompoundDrawablesWithIntrinsicBounds(null, top , null, null);
            btn_contentview.setText("Click to watch video");
            btn_contentview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2 = new Intent(Todo_content.this, Youtube.class);
                    intent2.putExtra("YTitemName",itemTitle);
                    intent2.putExtra("YTitemURL",itemURL);
                    startActivity(intent2);
                }
            });
        }else{
            Drawable top = getResources().getDrawable(R.drawable.ic_open_book);
            btn_contentview.setCompoundDrawablesWithIntrinsicBounds(null, top , null, null);
            btn_contentview.setText("Click to view content");
            btn_contentview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse(itemURL));
                    startActivity(implicit);
                }
            });
        }

        btn_delContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Todo_content.this);
                mBuilder.setMessage("Are you sure you want to clear this To do list?").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleterow(itemID, itemTitle);
                        Intent intent = new Intent(Todo_content.this, to_do.class);
                        startActivity(intent);
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
        });
    }

    private void deleterow(int id, String title){
        mDatabaseHelper = new QuizDbHelper(this);
        mDatabaseHelper.deleteName(id, title);
        Toast.makeText(this, title + " has been removed", Toast.LENGTH_SHORT).show();
        finish();
    }
    //checking for URL string and return boolean
    private boolean chkURLorYT(String chkURL) {
        boolean b = chkURL.contains("https://www.youtube.com/watch?");
     return b;
    }
}
