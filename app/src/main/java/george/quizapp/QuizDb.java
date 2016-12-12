package george.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by georg on 2016-12-10.
 */

public class QuizDb {
    //instance members for accessing the database
    private SQLiteDatabase database;
    private SQLiteOpenHelper openHelper;


    //constants for the db
    public static final String DB_NAME = "pronerj_QuizApp";
    public static final int DB_VERSION = 1;


    //constants for the table
    public static final String QUIZ_TABLE = "Quizzes";


    public static final String ID = "_id";
    public static final int ID_COLUMN = 0;

    public static final String QUESTION = "Question";
    public static final int QUESTION_COLUMN = 1;

    public static final String ANSWER = "Answer";
    public static final int ANSWER_COLUMN = 2;


    //Statement for creating the table don't forget spacing!
    public static final String CREATE_QUIZ_TABLE =
            "CREATE TABLE " + QUIZ_TABLE + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + QUESTION + " TEXT, " +
                    ANSWER + " INTEGER" + ")";

    public QuizDb(Context context) {

        //initialize the openhelper
        openHelper = new UserLogicDb.DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    public Quiz GetQuiz() {
        Quiz quiz = new Quiz();

        //get readable database
        database = openHelper.getReadableDatabase();

        //get all records from Login table and order by studentid
        //cursor represents all the records
        Cursor cursor = database.query(QUIZ_TABLE, null, null, null, null, null, null);

        //loop through cursor and popular the array list
        while (cursor.moveToNext()) {

            //get the values
            String question = cursor.getString(cursor.getColumnIndex(QUESTION));
            int answer = cursor.getInt(cursor.getColumnIndex(ANSWER));

            //add a new question to the arraylist
            quiz.AddMCquestion(question, answer);


        }

        cursor.close();
        database.close();


        //return arraylist to caller
        return quiz;
    }
    public long SaveQuiz(Quiz quiz) {
        boolean success = false;

        ContentValues cv = new ContentValues();
        for(int i = 0; i< quiz.questionList.size(); i++) {
            cv.put(QUESTION, quiz.questionList.get(i));
            cv.put(ANSWER, quiz.multipleChoiceAnswerList.get(i));
        }
        database = openHelper.getWritableDatabase();
        long id = database.insert(QUIZ_TABLE, null, cv);

        database.close();
        return id;
    }



    //public ArrayList<Users>;
    //ide makes this abstract class for us
    //DBHelper
    //Using the cursor of db
    private static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context, String dbName, SQLiteDatabase.CursorFactory cf, int dbVersion) {
            super(context, dbName, cf, dbVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //called by database if the database doesn't exist yet
            //runtime creates the db
            db.execSQL(CREATE_QUIZ_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            //drop the table if it is a wrong version and call onCreate() not checking for version
            //info
            db.execSQL("DROP TABLE IF EXISTS" + QUIZ_TABLE);
            onCreate(db);
        }
    }
}
