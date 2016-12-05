package george.quizapp;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by georg on 2016-12-05.
 */

public class UserPreffFragment extends PreferenceFragment{
        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.user_pref);


        }
    }


