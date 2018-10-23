package com.example.willi.buddy;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class quiz_start extends AppCompatActivity {

    TextView ques;
    Button OptA, OptB, OptC, OptD;

    private List<Question> questionList;
    private int questionCounter, questioncountTotal;
    private Question currentQuestion;
    private boolean answered;
    private TextView TxtCounter;

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
        TxtCounter = findViewById(R.id.txtCounter);

        Intent intent = getIntent();
        final int questcat = intent.getIntExtra("quizCat", 0);
        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions(questcat);
        questioncountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        OptA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int answer = 1;
                checkanswer(answer);
                showNextQuestion();

            }
        });
    }

    private void checkanswer(int answer) {
        if(answer == currentQuestion.getAnswerNr()){
            Toast toast = Toast.makeText(getApplicationContext(),"CORRECT!",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
        else
        {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
            mBuilder.setMessage("Your answer is wrong, please check your TO-DO list to learn more").setCancelable(false).setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alert = mBuilder.create();
            alert.show();
        }

    }

    private void showNextQuestion() {
        if (questionCounter < questioncountTotal){
            currentQuestion = questionList.get(questionCounter);
            int qcount = questionCounter + 1;
            TxtCounter.setText("Question no: "+ qcount + " / "+ questioncountTotal);
            ques.setText(currentQuestion.getQuestion());
            OptA.setText(currentQuestion.getOption1());
            OptB.setText(currentQuestion.getOption2());
            OptC.setText(currentQuestion.getOption3());
            OptD.setText(currentQuestion.getOption4());
            questionCounter++;
            answered = false;
        }

    }
}
