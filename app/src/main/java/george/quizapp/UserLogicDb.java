package george.quizapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Calvin Lai on 2016-12-06.
 */

public class UserLogicDb {


    //instance members for accessing the database
    private SQLiteDatabase database;
    private SQLiteOpenHelper openHelper;


    //constants for the db
    public static final String DB_NAME = "login.db";
    public static final int DB_VERSION = 1;


    //constants for the table
    public static final String LOGIN_TABLE = "Login";


    public static final String ID = "_id";
    public static final int ID_COLUMN = 0;

    public static final String PASSWORD = "password";
    public static final int PASSWORD_COLUMN = 1;


    //Statement for creating the table don't forget spacing!
    public static final String CREATE_LOGIN_TABLE =
            "CREATE TABLE " + LOGIN_TABLE + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PASSWORD + " TEXT, " + ")";

    public UserLogicDb(Context context) {

        //initialize the openhelper
        openHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    public ArrayList<Users>
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
            db.execSQL(CREATE_LOGIN_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            //drop the table if it is a wrong version and call onCreate() not checking for version
            //info
            db.execSQL("DROP TABLE IF EXISTS" + LOGIN_TABLE);
            onCreate(db);
        }
    }
}