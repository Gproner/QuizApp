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
                //Intent intent = new Intent(getApplicationContext(), MakeOrTakeActivity.class);//// TODO: 2016-12-07  change this
                //startActivity(intent);//// TODO: 2016-12-07 create and add quiz as parcable
                LaunchSharedPrefFrag();
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

    public void LaunchSharedPrefFrag() {

        FragmentManager fm = getFragmentManager();

        View view = findViewById(R.id.container);

        if (view == null){
            OptionPageFragment optionPageFragment = new OptionPageFragment();





            fm.beginTransaction()
                    .replace(R.id.container, detailFragment, DetailFragment.TAG)
                    .addToBackStack(ListFragment.TAG)
                    .commit();
        } else{
            DetailFragment  df=
                    (DetailFragment) fm.findFragmentByTag(DetailFragment.TAG);

            df.update(bundle);
        }


        }

        FragmentTransaction ft = fm.beginTransaction();
        optionPageFragment = new OptionPageFragment();
        ft.replace(R.id.container, optionPageFragment);
        ft.commit();
    }
}
