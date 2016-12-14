package george.quizapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogIn;
    String username, password;
    UserLogicDb usernameandPassDb;
    SharedPreferences preferences;
    private BroadcastReceiver receiver;

    {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {//recieving the broadcast
                if (intent.getAction().equals(UserNamePasswordService.LOAD_BROADCAST)) {
                    boolean success = intent.getBooleanExtra(UserNamePasswordService.LOAD_SUCCESS, false);//load success, default false

                    if (success) {
                        //Toast.makeText(MainActivity.this, "Successfully loaded profiles", Toast.LENGTH_SHORT).show();//toast pops up saying successfully loaded if LOAD_SUCCESS comes back true

                    }
                    else{
                        Toast.makeText(MainActivity.this, "ERROR with profiles", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (intent.getAction().equals(LoadIntentService.LOAD_BROADCAST)){
                    boolean success = intent.getBooleanExtra(LoadIntentService.LOAD_SUCCESS, false);//load success, default false

                    if (success) {
                        Toast.makeText(MainActivity.this, "Successfully loaded quizzes", Toast.LENGTH_SHORT).show();//toast pops up saying successfully loaded if LOAD_SUCCESS comes back true

                    }
                    else{
                        Toast.makeText(MainActivity.this, "ERROR with quizzes", Toast.LENGTH_SHORT).show();
                    }
                }


            }

        };
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogIn = (Button) findViewById(R.id.btnLogin);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtUsername = (EditText) findViewById(R.id.edtUserName);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickLogin();
            }
        });
        IntentFilter loadFilter = new IntentFilter(); //initializing intentFilter for what intents or broadcasts we wanna receive
        loadFilter.addCategory(Intent.CATEGORY_DEFAULT); //setting loadFilter Category.
        loadFilter.addAction(UserNamePasswordService.LOAD_BROADCAST);//adding filter to the intent filter for what we want to recieve
        loadFilter.addAction(LoadIntentService.LOAD_BROADCAST);
        //loadFilter.addAction(MissionActivity.MISSION_COMPLETED);//we're listening to a broadcast from missionActivity stating that Mission was completed
        registerReceiver(receiver, loadFilter);//registering the reciever with the filter of the params above


        CheckNetwork();
        //Intent getQuizServiceIntent = new Intent(this, LoadIntentService.class);
        //startService(getQuizServiceIntent);


    }

    private void OnClickLogin() {

        username= getUserName();
        password = getPassword();
        usernameandPassDb = new UserLogicDb(getApplicationContext());
        ArrayList<User> users = usernameandPassDb.getAllUsers();
        int size = users.size();
        if(size ==0){
            Toast.makeText(MainActivity.this, "Nothing in DB", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MakeOrTakeActivity.class);
            startActivity(intent);
        }
        for(int i = 0; i < size; i++ ) {
            if (username.equals(users.get(i).GetName()) && password.equals(users.get(i).GetPassword())) {//if user name and password match from db
                Toast toast = Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT);
                toast.show();
                QuizDb quizDb = new QuizDb(this);
                Quiz quiz = quizDb.GetQuiz();
                Intent intent = new Intent(getApplicationContext(), MakeOrTakeActivity.class);
                //intent.putExtra("quiz", quiz);
                startActivity(intent);
                break;
            }
        //Toast toast = Toast.makeText(getApplicationContext(), "Invalid Username and password", Toast.LENGTH_SHORT);
        //toast.show();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public String getUserName(){
        return edtUsername.getText().toString();
    }
    public String getPassword(){
        return edtPassword.getText().toString();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Intent sharedPrefActivity = new Intent(this, SharedPreferencesActivity.class);
        startActivity(sharedPrefActivity);


        return super.onOptionsItemSelected(item);
    }
    public void CheckNetwork(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);//get connectivity manager

        NetworkInfo ni = cm.getActiveNetworkInfo(); //get the network infor from teh system

        if (ni == null || !ni.isConnected()){//if there is no network or the network is not connected
            Toast.makeText(MainActivity.this, "Network not connected", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(this, UserNamePasswordService.class); // if successful create an intent for the background intent service

            startService(intent);//start LoadIntentService (background activity to get the database from online)



        }
    }



}

