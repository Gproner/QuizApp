package george.quizapp;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by georg on 2016-12-11.
 */

public class UserNamePasswordService extends IntentService {
    public UserNamePasswordService() {
        super("LoadIntentService");
    }
    public static String LOAD_COMPLETE = "loadcomplete";//Constant for load complete
    public static String LOAD_BROADCAST = "loadBroadcast";//Constant for load Broadcast
    public static String LOAD_STATUS = "loadStatus"; // constant for load status
    public static String LOAD_SUCCESS = "loadsuccess"; //constant for load success

    /**
     * Handiling the intent that's passed in
     * @param intent - intent from calling activity
     */
    @Override
    protected void onHandleIntent(Intent intent) {


        URL url; //url
        InputStream is = null;//input stream

        try {//Exception handling
            url = new URL("http://pronerj.dev.fast.sheridanc.on.ca/usernameandpass.txt");//URL where the info for DB is stored

            URLConnection conn = url.openConnection();// connecting

            HttpURLConnection httpConn = (HttpURLConnection) conn;

            int responseCode = httpConn.getResponseCode(); // getting the result if URL connected

            if(responseCode == HttpURLConnection.HTTP_OK){ //If the connection is OK use info to create db

                UserLogicDb userDb = new UserLogicDb(this); //make new Database



                is = httpConn.getInputStream(); //getting the text

                Scanner scanner = new Scanner(is);//reading the text
                while (scanner.hasNextLine()){//while there is more lines in text
                    String lineIn = scanner.nextLine(); //string line is the line read from text
                    User user = new User(lineIn);//new mission is created with the string passed in for priority and mission description
                    userDb.SaveUser(user);// call save mission to save the mission to the database
                }

                Intent broadcastIntent = new Intent(); //create broadcast intent
                broadcastIntent.putExtra(LOAD_SUCCESS, true); //adding that the load was successful
                broadcastIntent.setAction(LOAD_BROADCAST); //set action to constant load broadcast

                sendBroadcast(broadcastIntent); // launch the broadcast

            }
        } catch (Exception e) {
            e.printStackTrace();//if at any point there is an exception with the connection

        }finally {
            if(is != null){// if input stream is empty
                try{
                    is.close(); // close the input stream
                }catch(IOException ignored) {}// error handling
            }
        }


    }


}
