package george.quizapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * Created by georg on 2016-12-05.
 */

public class SharedPreferencesActivity extends PreferenceActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.xml.user_pref);

        addPreferencesFromResource(R.xml.user_pref);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);


    }

}
