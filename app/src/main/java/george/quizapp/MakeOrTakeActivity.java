package george.quizapp;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MakeOrTakeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_or_take);
        LaunchOptionFrag();
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
}
