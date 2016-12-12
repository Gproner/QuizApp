package george.quizapp;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MakeOrTakeActivity extends AppCompatActivity {

    Spinner spinner;
    Button takeQuiz, makeQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_or_take);
        CheckNetwork();
        LaunchOptionFrag();

        spinner = (Spinner) findViewById(R.id.spnQuizSelector);
        takeQuiz  = (Button) findViewById(R.id.btnTakeQuiz);
        takeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    private void onTakeQuiz(){
        Intent takeQuizIntent = new Intent(this, QuizActivity.class);
        startActivity(takeQuizIntent);

    }
    public void LaunchOptionFrag() {

        FragmentManager fm = getFragmentManager();

        View view = findViewById(R.id.mainContainer);

        //if (view == null){
        OptionPageFragment optionPageFragment = new OptionPageFragment();

        fm.beginTransaction()
                .add(R.id.mainContainer, new OptionPageFragment(), OptionPageFragment.TAG)
                .addToBackStack(optionPageFragment.TAG)
                .commit();
        /*} else{
            OptionPageFragment  op=
                    (OptionPageFragment) fm.findFragmentByTag(OptionPageFragment.TAG);
                fm.beginTransaction()
                    .replace(R.id.mainContainer, op)
                    .addToBackStack(op.TAG)
                    .commit();


        }*/


    }
    public void CheckNetwork(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);//get connectivity manager

        NetworkInfo ni = cm.getActiveNetworkInfo(); //get the network infor from teh system

        if (ni == null || !ni.isConnected()){//if there is no network or the network is not connected
            Toast.makeText(MakeOrTakeActivity.this, "Network not connected", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(this, LoadIntentService.class); // if successful create an intent for the background intent service

            startService(intent);//start LoadIntentService (background activity to get the database from online)



        }
    }
}
