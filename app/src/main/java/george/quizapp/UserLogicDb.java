package george.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public static final String NAME = "name";
    public static final int NAME_COLUMN = 1;

    public static final String PASSWORD = "password";
    public static final int PASSWORD_COLUMN = 2;


    //Statement for creating the table don't forget spacing!
    public static final String CREATE_LOGIN_TABLE =
            "CREATE TABLE " + LOGIN_TABLE + " ( " +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NAME + " TEXT, " +
                    PASSWORD + " TEXT " + ")";

    public UserLogicDb(Context context) {

        //initialize the openhelper
        openHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }
    /**
     * Saves the mission to the Database
     * @param user passes in the user to save to the database
     * @return returns the id of the user saved
     */
    public long SaveUser(User user) {
        boolean success = false;

        ContentValues cv = new ContentValues();

        cv.put(user.GetName(), NAME_COLUMN);
        cv.put(user.GetPassword(), PASSWORD_COLUMN);

        database = openHelper.getWritableDatabase();
        long id = database.insert(LOGIN_TABLE, null, cv);

        database.close();
        return id;
    }


    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();

        //get readable database
        database = openHelper.getReadableDatabase();

        //get all records from Login table and order by studentid
        //cursor represents all the records
        Cursor cursor = database.query(LOGIN_TABLE, null, null, null, null, null, ID);

        //loop through cursor and popular the array list
        while (cursor.moveToNext()) {

            //get the values
            long id = cursor.getLong(cursor.getColumnIndex(ID));
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            String password = cursor.getString(cursor.getColumnIndex(PASSWORD));

            //add a new student to the arraylist
            User user = new User(name, password);
            users.add(user);


        }

        cursor.close();
        database.close();


        //return arraylist to caller
        return users;
    }

    //public ArrayList<Users>;
    //ide makes this abstract class for us
    //DBHelper
    //Using the cursor of db
    public static class DBHelper extends SQLiteOpenHelper {
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
