package com.example.willi.buddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.willi.buddy.QuizContract;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "Quiz.db";
    private static final int DATABASE_VERSION = 1;

    //to-do table
    private static final String TABLE_TO_DO = "todoList";
    private static final String KEY_TO_DO = "todo";

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //this.db = db;
        Log.d(TAG, "onCreate: Database creating");

        String SQL_CREATE_QUIZBANK = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuizContract.QuestionsTable.COLUMN_RESOURCE + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_IDENTIFIER + " INTEGER" +
                " );";


        sqLiteDatabase.execSQL(SQL_CREATE_QUIZBANK);
        Log.d(TAG, "onCreate: Database created");
                 fillQuizBank(sqLiteDatabase);


        final String SQL_CREATE_TODO = "CREATE TABLE " +
                TABLE_TO_DO + " ( " +
                KEY_TO_DO + "TexT " +
                " ) "
                ;
            //db.execSQL(SQL_CREATE_TODO);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP IF TABLE EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    private void fillQuizBank(SQLiteDatabase db) {
        Question q1 = new Question("Hello World","A","B","C","D",1,"https://stackoverflow.com/",1);
        insertToDB(q1,db);
        Question q2 = new Question("Testing","A","B","C","D",2,"https://stackoverflow.com/",2);
        insertToDB(q2,db);
        Question q3 = new Question("Experiment","A","B","C","D",3,"https://stackoverflow.com/",3);
        insertToDB(q3,db);
        Question q4 = new Question("Experiment","A","B","C","D",4,"https://stackoverflow.com/",1);
        insertToDB(q4,db);
    }
    private void insertToDB(Question question, SQLiteDatabase sqLiteDatabase){
        ContentValues cv = new ContentValues();
        Log.d(TAG, "insertToDB: content v created");
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION4,question.getOption4());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR,question.getAnswerNr());
        cv.put(QuizContract.QuestionsTable.COLUMN_RESOURCE,question.getResource());
        cv.put(QuizContract.QuestionsTable.COLUMN_IDENTIFIER,question.getQuizIdentifier());
        Log.d(TAG, "insertToDB: cv.put establish");
        sqLiteDatabase.insert(QuizContract.QuestionsTable.TABLE_NAME,null,cv);
        Log.d(TAG, "insertToDB: db data insert");
    }

    public List<Question> getAllQuestions(int cat) {
        List<Question> questionList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME +
                " WHERE " + QuizContract.QuestionsTable.COLUMN_IDENTIFIER + " = '" + cat + "'",null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION4)));
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
