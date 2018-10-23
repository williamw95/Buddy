package com.example.willi.buddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.willi.buddy.QuizContract;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Quiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

<<<<<<< HEAD

=======
    //to-do table
    private static final String TABLE_TO_DO = "todoList";
    private static final String KEY_TO_DO = "todo";
>>>>>>> remotes/origin/withChanges

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

<<<<<<< HEAD
=======


>>>>>>> remotes/origin/withChanges
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = db;

<<<<<<< HEAD
=======


>>>>>>> remotes/origin/withChanges
        final String SQL_CREATE_QUIZBANK = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuizContract.QuestionsTable.COLUMN_RESOURCE + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_IDENTIFIER + " INTEGER" +
                ")"
                ;
                db.execSQL(SQL_CREATE_QUIZBANK);
                 fillQuizBank();

<<<<<<< HEAD
         


=======
        final String SQL_CREATE_TODO = "CREATE TABLE " +
                TABLE_TO_DO + " ( " +
                KEY_TO_DO + "TexT " +
                " ) "
                ;
            db.execSQL(SQL_CREATE_TODO);
>>>>>>> remotes/origin/withChanges
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    private void fillQuizBank() {
        Question q1 = new Question("Hello World","A","B","C",1,"https://stackoverflow.com/",1);
        insertToDB(q1);
        Question q2 = new Question("Testing","A","B","C",2,"https://stackoverflow.com/",2);
        insertToDB(q2);
        Question q3 = new Question("Experiment","A","B","C",3,"https://stackoverflow.com/",3);
        insertToDB(q3);
    }
    private void insertToDB(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR,question.getAnswerNr());
        cv.put(QuizContract.QuestionsTable.COLUMN_RESOURCE,question.getResource());
        cv.put(QuizContract.QuestionsTable.COLUMN_IDENTIFIER,question.getQuizIdentifier());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME,null,cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME,null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                question.setResource(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_RESOURCE)));
                question.setQuizIdentifier(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_IDENTIFIER)));
                questionList.add(question);
            } while(c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
