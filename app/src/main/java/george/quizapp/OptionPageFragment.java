package george.quizapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by georg on 2016-11-14.
 */

public class OptionPageFragment extends Fragment {

    Button takeQuiz;
    public static final String TAG = "OptionPageFragment";

    public OptionPageFragment() {
        //Required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_option, container, false);
        takeQuiz = (Button) view.findViewById(R.id.btnTakeQuiz);
        //Intent intent = getActivity().getIntent();
        //Quiz quiz = intent.getParcelableExtra("quiz");
        takeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuizActivity.class);
                //intent.putExtra("quiz", quiz);
                startActivity(intent);
            }
        });
        return view;
    }



}
