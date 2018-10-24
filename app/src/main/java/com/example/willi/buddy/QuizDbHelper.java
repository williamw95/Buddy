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
    private static final String DATABASE_NAME = "buddy.db";
    private static final int DATABASE_VERSION = 1;

    //to-do table
    //private static final String DATABASE_NAME_USER = "user.db";
    //private static final String KEY_TO_DO = "todo";

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
                QuizContract.QuestionsTable.COLUMN_TITLE + " TEXT" +
                " );";

        String SQL_CREATE_USER = "CREATE TABLE IF NOT EXISTS " +
                QuizContract.UserTable.TABLE_UNAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.UserTable.USER_TITLE+ " TEXT, " +
                QuizContract.UserTable.USER_URL+ " TEXT" +
                " );";

        sqLiteDatabase.execSQL(SQL_CREATE_QUIZBANK);
        sqLiteDatabase.execSQL(SQL_CREATE_USER);
        Log.d(TAG, "onCreate: Database created");
                 fillQuizBank(sqLiteDatabase);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP IF TABLE EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    private void fillQuizBank(SQLiteDatabase db) {
        Question q1 = new Question("Suppose your method does not return any value, which of the following keywords can be used as a return type?","A: return","B: void","C: Boolean","D: String",2,"https://www.tutorialspoint.com/java/java_methods.htm",1,"using void in methods");
        insertToDB(q1,db);
        Question q2 = new Question("A variable defined inside a method is referred to as ______.","A: a global variable","B: a local variable","C: a method variable","D: a useful variable",2,"https://www.geeksforgeeks.org/variable-scope-in-java/",1,"local variables");
        insertToDB(q2,db);
        Question q3 = new Question("What is/are the benefits of using Methods?","A: write a method once and reuse it anywhere","B: information hiding. Hide the implementation from the user","C: Allow encapsuation","D: All of the above",4,"https://www.cs.drexel.edu/~introcs/Fa15/notes/06.1_OOP/Advantages.html?CurrentSlide=3",1,"benefit of using methods");
        insertToDB(q3,db);
        Question q4 = new Question("What is wrong with the following method call? displayValue (double x);","A: There is nothing wrong with the statement","B: displayValue will not accept a parameter","C: Do not include the data type in the method call","D: Have a String variable called x" ,3,"https://www.tutorialspoint.com/java/java_methods.htm",1,"invoking methods");
        insertToDB(q4,db);
        Question q5 = new Question("What is the difference between a method and message?","A: A method is a sequence of instructions that a class or object uses to perform a task, and a message is a signal that tells the object to perform a tasks","B: A method is a prototype of how a computer should carry out a task, and a message implements the method","C: A message is a sequence of instructions to tell the object what to do, and a message is a packet of information used to communicate between two users on a network","D: A method and message is the same thing",1,"https://www.allinterview.com/showanswers/237/what-is-the-difference-between-method-and-message.html",1,"difference between method and message");
        insertToDB(q5,db);

        Question q6 = new Question("Which of the following, if any, is an invalid array declaration?","A: String[] names = new String[5];","B: String names[] = new String[5];","C: Double names[] = new Double[5]","D: All are valid",4,"https://stackoverflow.com/questions/1200621/how-do-i-declare-and-initialize-an-array-in-java",2,"how to declare arrays");
        insertToDB(q6,db);
        Question q7 = new Question("Which of the following statements gets the number of integers in the array that follows? \n" +
                "Int[] customers = new int [55];\n","A: int size = customers.length();","B: int size = customers.length;","C: int size = customers.size(); ","D: All are correct",2,"https://stackoverflow.com/questions/8755812/array-length-in-java/",2,"finding the length of an array");
        insertToDB(q7,db);
        Question q8 = new Question("What is the value of names[4] in the following array?\n" +
                "String[] names = {\"Jeff\", \"Dan\", \"Sally\", \"Jill\", \"Allie\"};\n","A: Sally","B: Jill","C: Allie ","D: Dan",3,"https://www.tutorialspoint.com/java/java_arrays.htm",2, "indexing with arrays");
        insertToDB(q8,db);
        Question q9 = new Question("What is the highest index value associated with the array that follows?\n" +
                "byte[] values = new byte[x];\n","A: x","B: x - 1","C: x + 1 ","D: All are highest",2,"https://www.javatpoint.com/array-in-java",2,"array index values");
        insertToDB(q9,db);

        Question q10 = new Question("Which of the following is correct syntax for defining a new class Jolt based on the superclass SoftDrink?","class Jolt implements SoftDrink{}","class Jolt defines SoftDrink{}",": class Jolt extends SoftDrink{} ","D: All are correct",3,"https://www.tutorialspoint.com/java/java_inheritance.htm",3,"defining a class using inheritance");
        insertToDB(q10,db);
        Question q11 = new Question("Does a subclass inherit both member variables and methods?","A: No, only member variables are inherited","B: No, only methods are inherited","C: Yes, both are inherited ","D: No, both are not inherited",3,"https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html",3,"subclass inheritance");
        insertToDB(q11,db);
        Question q12 = new Question("A class Animal has a subclass Mammal. Which of the following is true?","A: Because of single inheritance, Mammal can have no other parent than Animal.","B: Because of single inheritance, Animal can have only one subclass.","C: Because of single inheritance, Mammal can have no subclass. ","None of it are true",1,"https://www.tutorialspoint.com/java/java_inheritance.htm",3,"single inheritance");
        insertToDB(q12,db);
        Question q13 = new Question("Which of the following is NOT an advantage to using inheritance?","A: One big superclass can be used instead of many little classes.","B: Similar classes can be made to behave consistently.","C: Enhancements to a base class will automatically be applied to derived classes. ","None of the above",1,"https://www.quora.com/What-are-all-the-advantages-of-inheritance-in-Java",3,"advantages of using inheritance");
        insertToDB(q13,db);
        Question q14 = new Question("What restriction is there on using the super reference in a constructor?","A:  It can only be used in the parent's constructor.","B: Only one child class can use it.","C:  It must be used in the first statement of the constructor. ","D: None of the above",3,"https://www.youtube.com/watch?v=jvoGw_wwquM",3,"how to use super reference, in heritance"); //"https://stackoverflow.com/questions/4090834/when-do-i-use-super"
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
        Log.d(TAG, "getAllQuestions: extract questions");
        c.close();
        return questionList;
    }

    public Cursor getTODOData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + QuizContract.UserTable.TABLE_UNAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    //return true if record exist
    public boolean checkRowexist(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT *  FROM " + QuizContract.UserTable.TABLE_UNAME +
                " WHERE " + QuizContract.UserTable.USER_TITLE +
                 " = '" + title + "'";
        Cursor cusor = db.rawQuery(query,null);
        if(cusor.getCount() <=0){
            cusor.close();
            return false;
        }
        else
        {
            cusor.close();
            return true;
        }
    }

    //return true if ANY record exist
    public boolean checkAnyRowexist(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT *  FROM " + QuizContract.UserTable.TABLE_UNAME + ";";
        Cursor cusor = db.rawQuery(query,null);
        if(cusor.getCount() <=0){
            cusor.close();
            return false;
        }
        else
        {
            cusor.close();
            return true;
        }
    }

    public void addNewURL(String title, String URL){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(QuizContract.UserTable.USER_TITLE, title);
        contentValues.put(QuizContract.UserTable.USER_URL, URL);
        db.insert(QuizContract.UserTable.TABLE_UNAME, null, contentValues);
        Log.d(TAG, "addNewURL: Title and URL added into User table");
    }

    public Cursor getRowData(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + QuizContract.UserTable.TABLE_UNAME +
                " WHERE " + QuizContract.UserTable.USER_TITLE + " = '" + title + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + QuizContract.UserTable.TABLE_UNAME + " WHERE "
                + QuizContract.UserTable.TITLE_ID + " = '" + id + "'" +
                " AND " + QuizContract.UserTable.USER_TITLE + " = '" + name + "'";

        try{
            db.execSQL(query);
        }catch(Exception e){
            e.printStackTrace();
        }
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");

    }
}
