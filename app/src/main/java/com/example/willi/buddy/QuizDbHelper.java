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
                QuizContract.QuestionsTable.COLUMN_IDENTIFIER + " INTEGER," +
                QuizContract.QuestionsTable.COLUMN_TITLE + "TEXT" +
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
        Question q1 = new Question("Suppose your method does not return any value, which of the following keywords can be used as a return type?","A: return","B: void","C: Boolean",2,"https://stackoverflow.com/",1,"using void in methods");
        insertToDB(q1,db);
        Question q2 = new Question("A variable defined inside a method is referred to as ______.","A: a global variable","B: a local variable","C: a method variable",2,"https://stackoverflow.com/",1,"local variables");
        insertToDB(q2,db);
        Question q3 = new Question("What is/are the benefits of using Methods?","A: write a method once and reuse it anywhere","B: information hiding. Hide the implementation from the user","C: All of the above",3,"https://stackoverflow.com/",1,"benefit of using methods");
        insertToDB(q3,db);
        Question q4 = new Question("What is wrong with the following method call? displayValue (double x);","A: There is nothing wrong with the statement","B: displayValue will not accept a parameter","C: Do not include the data type in the method call",3,"https://stackoverflow.com/",1,"invoking methods");
        insertToDB(q4,db);
        Question q5 = new Question("What is the difference between a method and message?","A: A method is a sequence of instructions that a class or object uses to perform a task, and a message is a signal that tells the object to perform a tasks","B: A method is a prototype of how a computer should carry out a task, and a message implements the method","C: A message is a sequence of instructions to tell the object what to do, and a message is a packet of information used to communicate between two users on a network",1,"https://stackoverflow.com/",1,"difference between method and message");
        insertToDB(q5,db);

        Question q6 = new Question("Which of the following, if any, is an invalid array declaration?","A: String[] names = new String[5];","B: String names[] = new String[5];","C: Both are valid",3,"https://stackoverflow.com/",2,"how to declare arrays");
        insertToDB(q6,db);
        Question q7 = new Question("Which of the following statements gets the number of integers in the array that follows? \n" +
                "Int[] customers = new int [55];\n","A: int size = customers.length();","B: int size = customers.length;","C: int size = customers.size(); ",2,"https://stackoverflow.com/",2,"finding the length of an array");
        insertToDB(q7,db);
        Question q8 = new Question("What is the value of names[4] in the following array?\n" +
                "String[] names = {\"Jeff\", \"Dan\", \"Sally\", \"Jill\", \"Allie\"};\n","A: Sally","B: Jill","C: Allie ",3,"https://stackoverflow.com/",2, "indexing with arrays");
        insertToDB(q8,db);
        Question q9 = new Question("What is the highest index value associated with the array that follows?\n" +
                "byte[] values = new byte[x];\n","A: x","B: x - 1","C: x + 1 ",2,"https://stackoverflow.com/",2,"array index values");
        insertToDB(q9,db);

        Question q10 = new Question("Which of the following is correct syntax for defining a new class Jolt based on the superclass SoftDrink?","class Jolt implements SoftDrink{}","class Jolt defines SoftDrink{}",": class Jolt extends SoftDrink{} ",3,"https://stackoverflow.com/",3,"defining a class using inheritance");
        insertToDB(q10,db);
        Question q11 = new Question("Does a subclass inherit both member variables and methods?","A: No, only member variables are inherited","B: No, only methods are inherited","C: Yes, both are inherited ",3,"https://stackoverflow.com/",3,"subclass inheritance");
        insertToDB(q11,db);
        Question q12 = new Question("A class Animal has a subclass Mammal. Which of the following is true?","A: Because of single inheritance, Mammal can have no other parent than Animal.","B: Because of single inheritance, Animal can have only one subclass.","C: Because of single inheritance, Mammal can have no subclass. ",1,"https://stackoverflow.com/",3,"single inheritance");
        insertToDB(q12,db);
        Question q13 = new Question("Which of the following is NOT an advantage to using inheritance?","A: One big superclass can be used instead of many little classes.","B: Similar classes can be made to behave consistently.","C: Enhancements to a base class will automatically be applied to derived classes. ",1,"https://stackoverflow.com/",3,"advantages of using inheritance");
        insertToDB(q13,db);
        Question q14 = new Question("What restriction is there on using the super reference in a constructor?","A:  It can only be used in the parent's constructor.","B: Only one child class can use it.","C:  It must be used in the first statement of the constructor. ",3,"https://stackoverflow.com/",3,"how to use super reference, in heritance");
        insertToDB(q14,db);
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
        cv.put(QuizContract.QuestionsTable.COLUMN_TITLE,question.getTitle());
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
                question.setTitle(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_TITLE)));
                questionList.add(question);
            } while(c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
