package george.quizapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    
    EditText edtUsername, edtPassword;
    Button btnLogIn;
    String username, password;
    SharedPreferences preferences;

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
                username= getUserName();
                password = getPassword();

            }
        });

    }
    public String getUserName(){
        return edtUsername.getText().toString();
    }
    public String getPassword(){
        return edtPassword.getText().toString();
    }
    
    boolean DoesUserExist(String un,String pass){
        return true;
        //// TODO: 2016-11-14 compare password and username to either SQLite database or similar 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent sharedPrefActivity = new Intent(this, SharedPreferencesActivity.class);
        startActivity(sharedPrefActivity);




        return super.onOptionsItemSelected(item);
    }

    public void LaunchOptionFrag() {

        FragmentManager fm = getFragmentManager();

        View view = findViewById(R.id.container);

        if (view == null){
            OptionPageFragment optionPageFragment = new OptionPageFragment();

            fm.beginTransaction()
                    .replace(R.id.container, optionPageFragment)
                    .addToBackStack(optionPageFragment.TAG)
                    .commit();
        } else{
            OptionPageFragment  op=
                    (OptionPageFragment) fm.findFragmentByTag(OptionPageFragment.TAG);
                fm.beginTransaction()
                    .replace(R.id.container, op)
                    .addToBackStack(op.TAG)
                    .commit();


        }


    }

}

